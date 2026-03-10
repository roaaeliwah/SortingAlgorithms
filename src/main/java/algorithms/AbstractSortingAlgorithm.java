package algorithms;

import gui.SortVisualizer;

import javax.swing.SwingUtilities;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSortingAlgorithm {
    long comparisons = 0;
    long interchanges = 0;
    int delayMs = 0;
    Runnable onUpdate;
    protected SortVisualizer visualizer;

    protected Set<Integer> redIndices = new HashSet<>();
    protected Set<Integer> yellowIndices = new HashSet<>();
    protected Set<Integer> greenIndices = new HashSet<>();

    public long getComparisons() {
        return comparisons;
    }

    public long getInterchanges() {
        return interchanges;
    }

    public abstract void sort(int[] arr);

    public void setOnUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    // control speed
    public void setStepDelay(int delayMs) {
        this.delayMs = delayMs;
    }

    public void setVisualizer(SortVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    protected void pauseAndRender() {
        if (visualizer != null) {
            visualizer.setHighlights(
                    new HashSet<>(redIndices),
                    new HashSet<>(yellowIndices),
                    new HashSet<>(greenIndices));
        }

        if (onUpdate != null) {
            // notifies gui that a change happened - run on EDT for thread-safety
            SwingUtilities.invokeLater(onUpdate);
        }

        if (delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
