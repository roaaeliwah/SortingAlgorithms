package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SortVisualizer extends JPanel {
    private int[] arr;
    private Set<Integer> redIndices = new HashSet<>();
    private Set<Integer> yellowIndices = new HashSet<>();
    private Set<Integer> greenIndices = new HashSet<>();

    public void setArray(int[] arr) {
        this.arr = arr;
        repaint();
    }

    public void setHighlights(Set<Integer> red, Set<Integer> yellow, Set<Integer> green) {
        this.redIndices = red;
        this.yellowIndices = yellow;
        this.greenIndices = green;
    }

    public void clearHighlights() {
        redIndices.clear();
        yellowIndices.clear();
        greenIndices.clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // clears panel
        super.paintComponent(g);

        if (arr == null || arr.length == 0) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Find max value to scale bar heights relative to the largest value
        int maxValue = 0;
        for (int val : arr) {
            if (val > maxValue) {
                maxValue = val;
            }
        }

        int numBars = arr.length;
        double barWidth = (double) panelWidth / numBars;

        for (int i = 0; i < numBars; i++) {
            int barHeight = (int) (((double) arr[i] / maxValue) * panelHeight);

            // Calculate bar's coords (top left corner)
            int x = (int) (i * barWidth);
            int y = panelHeight - barHeight;

            // Priority: green > yellow > red > default blue
            if (greenIndices.contains(i)) {
                g2d.setColor(new Color(76, 175, 80)); // green
            } else if (yellowIndices.contains(i)) {
                g2d.setColor(new Color(255, 193, 7)); // yellow
            } else if (redIndices.contains(i)) {
                g2d.setColor(new Color(244, 67, 54)); // red
            } else {
                g2d.setColor(new Color(50, 150, 200)); // blue (default)
            }

            g2d.fillRect(x, y, (int) Math.max(1, barWidth - 1), barHeight);
        }
    }
}
