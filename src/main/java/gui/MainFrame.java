package gui;

import javax.swing.*;

public class MainFrame extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane();

    public MainFrame() {
        setTitle("Sorting Algorithms Comparison & Visualization");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane.addTab("Comparison", new ComparisonPanel());
        tabbedPane.addTab("Visualization", new VisualizationPanel());

        add(tabbedPane);
    }

}
