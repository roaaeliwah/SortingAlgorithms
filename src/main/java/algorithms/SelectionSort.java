package algorithms;

public class SelectionSort implements SortingAlgorithm{
    long comparisons = 0;
    long interchanges = 0;

    @Override
    public long getComparisons() {
        return comparisons;
    }

    @Override
    public long getInterchanges() {
        return interchanges;
    }

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // to find minimum's index
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            // to swap
            if (min != i) {
                interchanges++;
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
    }
}
