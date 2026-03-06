package algorithms;

public class InsertionSort extends  AbstractSortingAlgo {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                comparisons++;
                if (arr[j] > tmp) {
                    interchanges++;
                    arr[j + 1] = arr[j];
                    pauseAndRender();
                } else {
                    break;
                }
            }
            arr[j + 1] = tmp;
            pauseAndRender();
        }
    }
}
