package algorithms;

public abstract class AbstractSortingAlgo {
    long comparisons = 0;
    long interchanges = 0;
    int delayMs = 0;
    Runnable onUpdate;


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

    public void setStepDelay(int delayMs) {
        this.delayMs = delayMs;
    }

    public void pauseAndRender() {
        if(onUpdate != null) {
            onUpdate.run();
        }

        if(delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
