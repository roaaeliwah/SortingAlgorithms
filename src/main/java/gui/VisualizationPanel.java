package gui;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    private JComboBox<String> algoCombo;
    private JSlider speedSlider;
    private JButton startButton;
    private JLabel statsLabel;
    private JComboBox<String> arrayTypeCombo;
    private JTextField sizeField;

    public VisualizationPanel() {
        setLayout(new BorderLayout());

        // Top Control Panel - Use a two-row panel to fit all controls comfortably
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));

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
//        startButton.addActionListener(e -> startVisualization());
        row2.add(startButton);

        topPanel.add(row1);
        topPanel.add(row2);

        add(topPanel, BorderLayout.NORTH);

        // Center Drawing Panel
//        visualizer = new SortVisualizer();
//        add(visualizer, BorderLayout.CENTER);

        // Bottom Stats Panel
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsLabel = new JLabel("Comparisons: 0 | Interchanges: 0");
        statsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statsPanel.add(statsLabel);
        add(statsPanel, BorderLayout.SOUTH);

        // Initialize with default array
    }
}
