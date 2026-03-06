package gui;

import algorithms.SortingAlgorithm;
import logic.ComparisonEngine;
import utils.SortVisualizer;
import utils.SorterFactory;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    private JComboBox<String> algoCombo;
    private JSlider speedSlider;
    private JButton startButton;
    private JLabel statsLabel;
    private JComboBox<String> arrayTypeCombo;
    private JTextField sizeField;
    private Thread sortThread;
    private ComparisonEngine engine = new ComparisonEngine();
    private int[] array;
    private SorterFactory sorterFactory = new SorterFactory();
    private SortVisualizer visualizer = new SortVisualizer();

    public VisualizationPanel() {
        setLayout(new BorderLayout());

        // Top Control Panel - Use a two-row panel to fit all controls comfortably
        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        row1.add(new JLabel("Algorithm:"));
        algoCombo = new JComboBox<>(new String[] { "Bubble", "Selection", "Insertion", "Merge", "Quick", "Heap" });
        row1.add(algoCombo);

        row1.add(new JLabel("Speed:"));
        speedSlider = new JSlider(1, 100, 50); // Delay in ms: 1 is fast, 100 is slow

        // ticks spacing
        speedSlider.setMajorTickSpacing(20);
        speedSlider.setPaintTicks(true);
        row1.add(speedSlider);

        row1.add(new JLabel("Type:"));
        arrayTypeCombo = new JComboBox<>(new String[] { "Random", "Sorted", "Reverse" });
        row1.add(arrayTypeCombo);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        row2.add(new JLabel("Size (Max 100):"));
        sizeField = new JTextField("100", 5);
        row2.add(sizeField);

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

        // Initialize with default array
    }

    private void startVisualization() {
        // This method will be called when the start button is clicked.
        // It should read the selected algorithm, speed, array type, and size,
        // generate the appropriate array, and then start the visualization.
        if (sortThread != null && sortThread.isAlive()) {
            return; // already running
        }

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

        // Disable controls
        startButton.setEnabled(false);
        algoCombo.setEnabled(false);
        arrayTypeCombo.setEnabled(false);
        statsLabel.setText("Sorting...");

        // Get choices
        String algoName = (String) algoCombo.getSelectedItem();
        int speed = speedSlider.getValue();
        String type = (String) arrayTypeCombo.getSelectedItem();

        // Create fresh array based on user inputs
        array = engine.generate(size, type);
        visualizer.setArray(array);

        // Instantiate the sorter
        SortingAlgorithm sorter = sorterFactory.createSorter(algoName);
        sorter.setStepDelay(speed);
        sorter.setOnUpdate(() -> {
            visualizer.repaint();
        });

        sortThread = new Thread(() -> {
            sorter.sort(array);

            // Re-enable and show stats
            SwingUtilities.invokeLater(() -> {
                visualizer.repaint();
                statsLabel.setText(String.format("Comparisons: %d | Interchanges: %d",
                        sorter.getComparisons(), sorter.getInterchanges()));
                startButton.setEnabled(true);
                algoCombo.setEnabled(true);
                arrayTypeCombo.setEnabled(true);
            });
        });

        sortThread.start();
    }
}
