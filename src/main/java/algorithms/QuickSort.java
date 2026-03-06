package algorithms;

public class QuickSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        quickSort(0, arr.length - 1, arr);
    }

    public void quickSort(int low, int high, int[] arr) {
        if (low < high) {
            int pi = partition(low, high, arr);
            quickSort(low, pi - 1, arr);
            quickSort(pi + 1, high, arr);
        }
    }

    public int partition(int low, int high, int[] arr) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                interchanges++;
                if (delayMs > 0)
                    pauseAndRender();
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        interchanges++;
        if (delayMs > 0)
            pauseAndRender();

        return i + 1;
    }
}
