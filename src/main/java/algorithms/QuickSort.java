package algorithms;

public class QuickSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        if (delayMs > 0)
            greenIndices.clear();
        quickSort(0, arr.length - 1, arr);

        // Mark all as green when done
        if (delayMs > 0) {
            redIndices.clear();
            yellowIndices.clear();
            for (int i = 0; i < arr.length; i++)
                greenIndices.add(i);
            pauseAndRender();
        }
    }

    public void quickSort(int low, int high, int[] arr) {
        if (low < high) {
            int pi = partition(low, high, arr);
            quickSort(low, pi - 1, arr);
            quickSort(pi + 1, high, arr);
        } else if (low == high) {
            // Single element is sorted
            greenIndices.add(low);
            if (delayMs > 0)
                pauseAndRender();
        }
    }

    public int partition(int low, int high, int[] arr) {
        int pivot = arr[high];
        int i = low - 1;

        if (delayMs > 0) {
            // Pivot -> yellow
            yellowIndices.clear();
            yellowIndices.add(high);
        }

        for (int j = low; j < high; j++) {
            comparisons++;

            if (delayMs > 0) {
                // Element being compared -> red
                redIndices.clear();
                redIndices.add(j);
                pauseAndRender();
            }

            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                interchanges++;
                // Repaint after swap
                if (delayMs > 0)
                    pauseAndRender();
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        interchanges++;

        if (delayMs > 0) {
            // When partition finished -> pivot's final position is green
            redIndices.clear();
            yellowIndices.clear();
            greenIndices.add(i + 1);
            pauseAndRender();
        }

        return i + 1;
    }
}
