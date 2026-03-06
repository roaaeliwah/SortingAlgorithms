package algorithms;

public class BubbleSort extends AbstractSortingAlgo {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    interchanges++;
                    // notify visualizer and apply step delay
                    pauseAndRender();
                }
            }
        }
    }
}
