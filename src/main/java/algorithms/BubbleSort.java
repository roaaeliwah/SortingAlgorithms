package algorithms;

public class BubbleSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    interchanges++;
                    swapped = true;
                    // notify visualizer and apply step delay
                    if (delayMs > 0)
                        pauseAndRender();
                }
            }
            if(!swapped) break;
        }
    }
}
