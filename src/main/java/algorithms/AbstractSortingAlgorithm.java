package algorithms;

import utils.SortVisualizer;

import javax.swing.SwingUtilities;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSortingAlgorithm implements SortingAlgorithm {
    long comparisons = 0;
    long interchanges = 0;
    int delayMs = 0;
    Runnable onUpdate;
    protected SortVisualizer visualizer;

    // Highlight sets – algorithms populate these before calling pauseAndRender()
    protected Set<Integer> redIndices = new HashSet<>();
    protected Set<Integer> yellowIndices = new HashSet<>();
    protected Set<Integer> greenIndices = new HashSet<>();

    @Override
    public long getComparisons() {
        return comparisons;
    }

    @Override
    public long getInterchanges() {
        return interchanges;
    }

    @Override
    public abstract void sort(int[] arr);

    @Override
    public void setOnUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    @Override
    // control speed
    public void setStepDelay(int delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public void setVisualizer(SortVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    protected void pauseAndRender() {
        // Push highlight state to visualizer
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

        // ensures it doesn't sleep during comparison and only during visualization
        if (delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
