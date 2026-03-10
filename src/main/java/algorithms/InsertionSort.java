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
                yellowIndices.clear();
                yellowIndices.add(i);
                pauseAndRender();
            }

            for (; j >= 0; j--) {
                if (delayMs > 0) {
                    redIndices.clear();
                    redIndices.add(j);
                    pauseAndRender();
                }

                comparisons++;
                if (arr[j] > tmp) {
                    interchanges++;
                    arr[j + 1] = arr[j];
                    if (delayMs > 0) {
                        yellowIndices.clear();
                        yellowIndices.add(j);
                        pauseAndRender();
                    }
                } else {
                    break;
                }
            }
            arr[j + 1] = tmp;

            redIndices.clear();
            yellowIndices.clear();
            if (delayMs > 0)
                pauseAndRender();
        }

        if (delayMs > 0) {
            for (int i = 0; i < arr.length; i++)
                greenIndices.add(i);
            pauseAndRender();
        }
    }
}
