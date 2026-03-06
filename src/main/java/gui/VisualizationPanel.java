package gui;

import algorithms.SortingAlgorithm;
import logic.ComparisonEngine;
import utils.SortVisualizer;
import utils.SorterFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class VisualizationPanel extends JPanel {
    private final JComboBox<String> algoCombo;
    private final JSlider speedSlider;
    private final JButton startButton;
    private final JLabel statsLabel;
    private final JComboBox<String> arrayTypeCombo;
    private final JTextField sizeField;
    private final JLabel sizeLabel;
    private final JButton chooseFileButton;
    private final JLabel fileLabel;
    private Thread sortThread;
    private final ComparisonEngine engine = new ComparisonEngine();
    private int[] array;
    private final SorterFactory sorterFactory = new SorterFactory();
    private SortVisualizer visualizer = new SortVisualizer();
    private File selectedFile;

    public VisualizationPanel() {
        setLayout(new BorderLayout());

        // Top Control Panel - Use a two-row panel to fit all controls comfortably
        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        row1.add(new JLabel("Algorithm:"));
        algoCombo = new JComboBox<>(new String[] { "Bubble", "Selection", "Insertion", "Merge", "Quick", "Heap" });
        row1.add(algoCombo);

        row1.add(new JLabel("Speed:"));
        speedSlider = new JSlider(1, 200, 50); // Delay in ms: 1 is fast, 100 is slow

        // ticks spacing
        speedSlider.setMajorTickSpacing(20);
        speedSlider.setPaintTicks(true);
        row1.add(speedSlider);

        row1.add(new JLabel("Type:"));
        arrayTypeCombo = new JComboBox<>(new String[] { "Random", "Sorted", "Reverse", "File" });
        arrayTypeCombo.addActionListener(e -> onArrayTypeChanged());
        row1.add(arrayTypeCombo);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        sizeLabel = new JLabel("Size (Max 100):");
        row2.add(sizeLabel);
        sizeField = new JTextField("100", 5);
        row2.add(sizeField);

        chooseFileButton = new JButton("Choose File...");
        chooseFileButton.addActionListener(e -> chooseFile());
        row2.add(chooseFileButton);

        fileLabel = new JLabel("No file selected");
        row2.add(fileLabel);

        startButton = new JButton("Start Visualization");
        startButton.addActionListener(e -> startVisualization());
        row2.add(startButton);

        controlPanel.add(row1);
        controlPanel.add(row2);

        add(controlPanel, BorderLayout.NORTH);

        // Center Drawing Panel
        visualizer = new SortVisualizer();
        add(visualizer, BorderLayout.CENTER);

        // Bottom Stats Panel
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsLabel = new JLabel("Comparisons: 0 | Interchanges: 0");
        statsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statsPanel.add(statsLabel);
        add(statsPanel, BorderLayout.SOUTH);

        // File controls are only relevant when "File" input type is selected.
        chooseFileButton.setVisible(false);
        fileLabel.setVisible(false);
    }

    private void onArrayTypeChanged() {
        boolean isFileType = "File".equals(arrayTypeCombo.getSelectedItem());
        sizeLabel.setVisible(!isFileType);
        sizeField.setVisible(!isFileType);
        chooseFileButton.setVisible(isFileType);
        fileLabel.setVisible(isFileType);
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text/CSV Files", "txt", "csv"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileLabel.setText(selectedFile.getName());
        }
    }

    private void startVisualization() {
        // called when the start button is clicked.
        // read the selected algorithm, speed, array type, and size,
        // generate the appropriate array, and then start the visualization.
        if (sortThread != null && sortThread.isAlive()) {
            return; // already running
        }

        String type = (String) arrayTypeCombo.getSelectedItem();

        if ("File".equals(type)) {
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(this, "Please choose a file first.", "No File Selected",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                array = engine.generateFromFile(selectedFile.getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Could not load file: " + ex.getMessage(), "File Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            int size;
            try {
                size = Integer.parseInt(sizeField.getText());
                if (size <= 0 || size > 100) {
                    JOptionPane.showMessageDialog(this, "Size must be between 1 and 100.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer for Size.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            assert type != null;
            array = engine.generate(size, type);
        }

        // Disable controls
        startButton.setEnabled(false);
        algoCombo.setEnabled(false);
        arrayTypeCombo.setEnabled(false);
        speedSlider.setEnabled(false);
        sizeField.setEnabled(false);
        chooseFileButton.setEnabled(false);
        statsLabel.setText("Sorting...");

        // Get choices
        String algoName = (String) algoCombo.getSelectedItem();
        int speed = speedSlider.getValue();
        visualizer.setArray(array);

        // Instantiate the sorter
        assert algoName != null;
        SortingAlgorithm sorter = SorterFactory.createSorter(algoName);
        sorter.setStepDelay(speed);
        sorter.setVisualizer(visualizer); // Hook up the visualizer for color-coding
        sorter.setOnUpdate(() -> {
            visualizer.repaint();
        });

        sortThread = new Thread(() -> {
            sorter.sort(array);

            // Re-enable controls and show final stats.
            // Keep final highlights visible so users can see the finished state.
            SwingUtilities.invokeLater(() -> {
                visualizer.repaint();
                statsLabel.setText(String.format("Comparisons: %d | Interchanges: %d",
                        sorter.getComparisons(), sorter.getInterchanges()));
                startButton.setEnabled(true);
                algoCombo.setEnabled(true);
                arrayTypeCombo.setEnabled(true);
                speedSlider.setEnabled(true);
                sizeField.setEnabled(true);
                chooseFileButton.setEnabled(true);
                onArrayTypeChanged();
            });
        });

        sortThread.start();
    }
}
