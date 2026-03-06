package algorithms;

import utils.SortVisualizer;

public interface SortingAlgorithm {
    void sort(int[] arr);

    long getComparisons();

    long getInterchanges();

    /**
     * Sets a callback for visualization updates.
     * The callback should be called whenever the array state changes (e.g., after a
     * swap or set).
     */
    void setOnUpdate(Runnable onUpdate);

    /**
     * Sets the delay in milliseconds between visualization steps.
     */
    void setStepDelay(int delayMs);

    /**
     * Sets the visualizer for color-coded highlighting.
     */
    void setVisualizer(SortVisualizer visualizer);
}
