package utils;

import javax.swing.*;
import java.awt.*;

public class SortVisualizer extends JPanel {
        private int[] arr;

        public void setArray(int[] arr) {
            this.arr = arr;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            //This clears the panel before drawing again
            super.paintComponent(g);
            if (arr == null || arr.length == 0)
                return;

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

            // Prevents 0 division
            if (maxValue == 0)
                maxValue = 1;

            int numBars = arr.length;
            double barWidth = (double) panelWidth / numBars;

            for (int i = 0; i < numBars; i++) {
                int barHeight = (int) (((double) arr[i] / maxValue) * panelHeight);

                // Calculate bar's coords
                int x = (int) (i * barWidth);
                int y = panelHeight - barHeight;

                g2d.setColor(new Color(50, 150, 200));
                g2d.fillRect(x, y, (int) Math.max(1, barWidth - 1), barHeight);
            }
        }
}
