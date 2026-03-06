package algorithms;

public class InsertionSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        if (delayMs > 0) {
            greenIndices.clear();
        }

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;

            if (delayMs > 0) {
                // Current key element being inserted -> red
                redIndices.clear();
                redIndices.add(i);
                yellowIndices.clear();
                pauseAndRender();
            }

            for (; j >= 0; j--) {
                comparisons++;

                if (delayMs > 0) {
                    // Element being compared/shifted -> red
                    redIndices.clear();
                    redIndices.add(j);
                    pauseAndRender();
                }

                if (arr[j] > tmp) {
                    interchanges++;
                    arr[j + 1] = arr[j];
                    // Repaint after shift
                    if (delayMs > 0)
                        pauseAndRender();
                } else {
                    break;
                }
            }
            arr[j + 1] = tmp;

            // Keep active highlights only during insertion steps.
            // We color green only when the full array is sorted.
            redIndices.clear();
            yellowIndices.clear();
            if (delayMs > 0)
                pauseAndRender();
        }

        if (delayMs > 0) {
            // Mark all as green when done
            for (int i = 0; i < arr.length; i++)
                greenIndices.add(i);
            pauseAndRender();
        }
    }
}
