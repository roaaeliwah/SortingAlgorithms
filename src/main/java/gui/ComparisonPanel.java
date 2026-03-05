package gui;

import logic.ComparisonEngine;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ComparisonPanel extends JPanel {
    private JComboBox<String> arrayTypeCombo;
    private JTextField minSizeField;
    private JTextField maxSizeField;
    private JTextField runsField;
    private JButton runButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private ChartPanel chartPanel;

    private ComparisonEngine engine;
    private final String[] ALGORITHMS = {"Bubble", "Selection",
            "Insertion", "Merge", "Quick", "Heap"};
    private static final int NUM_SAMPLE_SIZES = 8;

    public ComparisonPanel() {
        engine = new ComparisonEngine();
        setLayout(new BorderLayout());

        // Top Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));

        controlPanel.add(new JLabel("Array Type:"));

        // dropdown menu
        arrayTypeCombo = new JComboBox<>(new String[] { "Random", "Sorted", "Reverse" });
        controlPanel.add(arrayTypeCombo);

        controlPanel.add(new JLabel("Min Size:"));
        minSizeField = new JTextField("100", 6);
        controlPanel.add(minSizeField);

        controlPanel.add(new JLabel("Max Size (≤10,000):"));
        maxSizeField = new JTextField("5000", 6);
        controlPanel.add(maxSizeField);

        controlPanel.add(new JLabel("Runs:"));
        // 4 -> box width
        runsField = new JTextField("5", 4);
        controlPanel.add(runsField);

        runButton = new JButton("Run Comparison");

        controlPanel.add(runButton);

        add(controlPanel, BorderLayout.NORTH);

        // Center: split between table and chart
        // Results Table
        String[] columns = {
                "Algorithm", "Size", "Type", "Runs",
                "Avg Time (ms)", "Min Time (ms)", "Max Time (ms)",
                "Comparisons", "Interchanges"
        };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // read-only table
            }
        };
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);
        resultsTable.setAutoCreateRowSorter(true);

        JScrollPane tableScroll = new JScrollPane(resultsTable);

        add(tableScroll, BorderLayout.CENTER);
    }
}
