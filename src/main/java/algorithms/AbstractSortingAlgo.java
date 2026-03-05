package algorithms;

public abstract class AbstractSortingAlgo implements  SortingAlgorithm {
    long comparisons = 0;
    long interchanges = 0;
    int delayMs = 0;
    Runnable onUpdate;


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

    protected void pauseAndRender() {
        if(onUpdate != null) {
            // notifies gui that a change happened
            onUpdate.run();
        }

        // ensures it doesn't sleep during comparison and only during visualization
        if(delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
