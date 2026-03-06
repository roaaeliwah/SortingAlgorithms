package gui;

import logic.ComparisonEngine;
import logic.SortResult;
import org.jfree.chart.ChartPanel;
import utils.SizeGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class ComparisonPanel extends JPanel {
    private JComboBox<String> arrayTypeCombo;
    private JTextField minSizeField;
    private JTextField maxSizeField;
    private JTextField runsField;
    private JButton runButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
//    private ChartPanel chartPanel;

    private ComparisonEngine engine;
    private SizeGenerator sizeGenerator = new SizeGenerator();
    private final String[] ALGORITHMS = {"Bubble", "Selection",
            "Insertion", "Merge", "Quick", "Heap"};

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
        runButton.addActionListener(e -> runComparison());
        controlPanel.add(runButton);

        add(controlPanel, BorderLayout.NORTH);


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

    private void runComparison() {
        try {
            int minSize = Integer.parseInt(minSizeField.getText());
            int maxSize = Integer.parseInt(maxSizeField.getText());
            int runs = Integer.parseInt(runsField.getText());
            String type = (String) arrayTypeCombo.getSelectedItem();
            int[] sizes = sizeGenerator.generateSizes(minSize, maxSize);

            if (minSize <= 0 || maxSize > 10000 || minSize > maxSize) {
                JOptionPane.showMessageDialog(this,
                        "Min must be ≥ 1, Max must be ≤ 10,000, and Min ≤ Max.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (runs <= 0) {
                JOptionPane.showMessageDialog(this, "Runs must be greater than 0.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            runButton.setEnabled(false); // prevents input change when sorting is running
            tableModel.setRowCount(0); // Clear previous results

            // Runs in a separate thread to keep UI responsive
            SwingWorker<Void, SortResult> worker = new SwingWorker<Void, SortResult>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Pre-generate base arrays for all sizes
                    Map<Integer, int[]> baseArrays = new LinkedHashMap<>();
                    for (int size : sizes) {
                        baseArrays.put(size, engine.generate(size, type));
                    }

                    // Submit all algorithm x size combinations in parallel
                    ExecutorService executor = Executors.newFixedThreadPool(
                            Runtime.getRuntime().availableProcessors());
                    for (int size : sizes) {
                        int[] baseArray = baseArrays.get(size);
                        for (String algo : ALGORITHMS) {
                            executor.submit(() -> {
                                SortResult result = engine.benchmark(algo, size, runs, baseArray);
                                result.arrayType = type;

                                publish(result);
                            });
                        }
                    }
                    executor.shutdown();
                    executor.awaitTermination(1, TimeUnit.HOURS);
                    return null;
                }

                @Override
                protected void process(List<SortResult> chunks) {
                    for (SortResult result : chunks) {
                        Object[] rowData = {
                                result.algorithmName,
                                result.arraySize,
                                result.arrayType,
                                result.runs,
                                String.format("%.3f", result.averageRunningTime),
                                String.format("%.3f", result.minRunningTime),
                                String.format("%.3f", result.maxRunningTime),
                                result.comparisons,
                                result.interchanges
                        };
                        tableModel.addRow(rowData);
                    }
                }

                @Override
                protected void done() {

                    runButton.setEnabled(true);
                }
            };
            worker.execute();


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid integers for Min Size, Max Size, and Runs.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
