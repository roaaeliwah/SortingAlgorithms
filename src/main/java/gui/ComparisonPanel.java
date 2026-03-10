package gui;

import logic.ComparisonEngine;
import logic.SortResult;
import utils.SizeGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.List;

public class ComparisonPanel extends JPanel {
    private final JComboBox<String> arrayTypeCombo;
    private final JTextField minSizeField;
    private final JTextField maxSizeField;
    private final JTextField runsField;
    private final JButton runButton;
    private final DefaultTableModel tableModel;
    private final JButton chooseFilesButton;
    private final JLabel filesLabel;
    private final JLabel minSizeLabel;
    private final JLabel maxSizeLabel;

    private File[] selectedFiles = null;
    private final String[] ALGORITHMS = { "Bubble", "Selection",
            "Insertion", "Merge", "Quick", "Heap" };

    private final ComparisonEngine engine;
    private final SizeGenerator sizeGenerator = new SizeGenerator();

    public ComparisonPanel() {
        engine = new ComparisonEngine();
        setLayout(new BorderLayout());

        // Top Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        controlPanel.add(new JLabel("Array Type:"));

        arrayTypeCombo = new JComboBox<>(new String[] { "Random", "Sorted", "Reverse", "File" });
        arrayTypeCombo.addActionListener(e -> onArrayTypeChanged());
        controlPanel.add(arrayTypeCombo);

        minSizeLabel = new JLabel("Min Size:");
        controlPanel.add(minSizeLabel);
        minSizeField = new JTextField("100", 6);
        controlPanel.add(minSizeField);

        maxSizeLabel = new JLabel("Max Size (≤10,000):");
        controlPanel.add(maxSizeLabel);
        maxSizeField = new JTextField("5000", 6);
        controlPanel.add(maxSizeField);

        chooseFilesButton = new JButton("Choose Files…");
        chooseFilesButton.addActionListener(e -> chooseFiles());
        controlPanel.add(chooseFilesButton);

        filesLabel = new JLabel("No files selected");
        controlPanel.add(filesLabel);

        controlPanel.add(new JLabel("Runs:"));
        runsField = new JTextField("5", 4);
        controlPanel.add(runsField);

        runButton = new JButton("Run Comparison");
        runButton.addActionListener(e -> runComparison());
        controlPanel.add(runButton);

        JButton exportCsvButton = new JButton("Export CSV");
        exportCsvButton.addActionListener(e -> exportToCSV());
        controlPanel.add(exportCsvButton);

        chooseFilesButton.setVisible(false);
        filesLabel.setVisible(false);

        add(controlPanel, BorderLayout.NORTH);

        String[] columns = {
                "Algorithm", "Size", "Type", "Runs",
                "Avg Time (ms)", "Min Time (ms)", "Max Time (ms)",
                "Comparisons", "Interchanges"
        };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);
        resultsTable.setAutoCreateRowSorter(true);

        JScrollPane tableScroll = new JScrollPane(resultsTable);

        add(tableScroll, BorderLayout.CENTER);
    }

    private void exportToCSV() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "There is no data to export.", "Empty Table",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Export to CSV");
        chooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File dest = chooser.getSelectedFile();
            // Ensure the file ends with .csv
            if (!dest.getName().toLowerCase().endsWith(".csv")) {
                dest = new File(dest.getAbsolutePath() + ".csv");
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(dest))) {
                // Write headers
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    writer.print(tableModel.getColumnName(i));
                    if (i < tableModel.getColumnCount() - 1) {
                        writer.print(",");
                    }
                }
                writer.println();

                // Write data
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 0; col < tableModel.getColumnCount(); col++) {
                        Object val = tableModel.getValueAt(row, col);
                        String valueStr = (val == null) ? "" : val.toString();
                        writer.print(valueStr);
                        if (col < tableModel.getColumnCount() - 1) {
                            writer.print(",");
                        }
                    }
                    writer.println();
                }
                JOptionPane.showMessageDialog(this, "Export successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error writing to file: " + ex.getMessage(), "Export Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onArrayTypeChanged() {
        String selectedType = (String) arrayTypeCombo.getSelectedItem();
        boolean isFileType = "File".equals(selectedType);

        // Show/hide file input controls
        chooseFilesButton.setVisible(isFileType);
        filesLabel.setVisible(isFileType);

        // Enable/disable size input controls
        minSizeLabel.setVisible(!isFileType);
        minSizeField.setVisible(!isFileType);
        maxSizeLabel.setVisible(!isFileType);
        maxSizeField.setVisible(!isFileType);
    }

    private void chooseFiles() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);

        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = chooser.showOpenDialog(this); // this: centers

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFiles = chooser.getSelectedFiles();
            if (selectedFiles.length == 1) {
                filesLabel.setText(selectedFiles[0].getName());
            } else {
                filesLabel.setText(selectedFiles.length + " files selected");
            }
        }
    }

    private void runComparison() {
        String type = (String) arrayTypeCombo.getSelectedItem();
        int runs = Integer.parseInt(runsField.getText());
        if (runs <= 0) {
            JOptionPane.showMessageDialog(this, "Runs must be greater than 0.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if ("File".equals(type)) {
            runFileComparison(runs);
        } else {
            runGeneratedComparison(type, runs);
        }
    }

    private void runFileComparison(int runs) {
        if (selectedFiles == null || selectedFiles.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Please choose at least one file first.",
                    "No Files Selected", JOptionPane.ERROR_MESSAGE);
            return;
        }
        runButton.setEnabled(false);
        tableModel.setRowCount(0);

        SwingWorker<Void, SortResult> worker = new SwingWorker<Void, SortResult>() {
            @Override
            protected Void doInBackground() throws Exception {
                Map<String, int[]> fileArrays = new LinkedHashMap<>();
                for (File f : selectedFiles) {
                    int[] arr = engine.generateFromFile(f.getAbsolutePath());
                    fileArrays.put(f.getName(), arr);
                }

                if (fileArrays.isEmpty()) {
                    return null;
                }

                for (Map.Entry<String, int[]> entry : fileArrays.entrySet()) {
                    String fileName = entry.getKey();
                    int[] baseArray = entry.getValue();

                    for (String algo : ALGORITHMS) {
                        SortResult result = engine.benchmark(algo, baseArray.length, runs, baseArray);
                        result.arrayType = fileName;
                        publish(result);
                    }
                }
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
    }

    private void runGeneratedComparison(String type, int runs) {
        try {
            int minSize = Integer.parseInt(minSizeField.getText());
            int maxSize = Integer.parseInt(maxSizeField.getText());
            int[] sizes = sizeGenerator.generateSizes(minSize, maxSize);

            if (minSize <= 0 || maxSize > 10000 || minSize > maxSize) {
                JOptionPane.showMessageDialog(this,
                        "Min must be ≥ 1, Max must be ≤ 10,000, and Min ≤ Max.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            runButton.setEnabled(false);
            tableModel.setRowCount(0);

            SwingWorker<Void, SortResult> worker = new SwingWorker<Void, SortResult>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // generate base arrays for all sizes
                    Map<Integer, int[]> baseArrays = new LinkedHashMap<>();
                    for (int size : sizes) {
                        baseArrays.put(size, engine.generate(size, type));
                    }

                    // Execute all algorithm x size combinations sequentially
                    for (int size : sizes) {
                        int[] baseArray = baseArrays.get(size);
                        for (String algo : ALGORITHMS) {
                            SortResult result = engine.benchmark(algo, size, runs, baseArray);
                            result.arrayType = type;
                            publish(result);
                        }
                    }
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
