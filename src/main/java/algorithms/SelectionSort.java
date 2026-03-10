package algorithms;

public class SelectionSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        if (delayMs > 0)
            greenIndices.clear();

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // to find minimum's index
            for (int j = i + 1; j < arr.length; j++) {
                if (delayMs > 0) {
                    yellowIndices.clear();
                    yellowIndices.add(min);
                    redIndices.clear();
                    redIndices.add(j);
                    pauseAndRender();
                }

                comparisons++;
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            // Move minimum element to its correct position
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            interchanges++;

            if (delayMs > 0) {
                greenIndices.add(i);
                yellowIndices.clear();
                redIndices.clear();
                pauseAndRender();
            }
        }

        if (delayMs > 0) {
            greenIndices.add(arr.length - 1);
            pauseAndRender();
        }
    }
}
