package algorithms;

public class BubbleSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        if (delayMs > 0)
            greenIndices.clear();

        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (delayMs > 0) {
                    // Highlight the two elements being swapped in red
                    redIndices.clear();
                    redIndices.add(j);
                    redIndices.add(j + 1);
                    pauseAndRender();
                }

                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    interchanges++;
                    swapped = true;
                    // Repaint after swap
                    if (delayMs > 0)
                        pauseAndRender();
                }
            }
            if (delayMs > 0) {
                // After each pass, last element is sorted (green)
                greenIndices.add(arr.length - i - 1);
                redIndices.clear();
                pauseAndRender();
            }

            if (!swapped)
                break;
        }
        // Mark all as green when done
        redIndices.clear();
        for (int i = 0; i < arr.length; i++)
            greenIndices.add(i);
        if (delayMs > 0)
            pauseAndRender();
    }
}
