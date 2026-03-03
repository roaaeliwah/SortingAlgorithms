package algorithms;

public class QuickSort {
    int comparisons = 0;
    int interchanges = 0;
    int[] arr;

    public int getComparisons() {
        return comparisons;
    }
    public  int getInterchanges() {
        return interchanges;
    }

    public QuickSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        quickSort(0, arr.length-1);
    }

    public void quickSort(int low, int high) {
        if(low < high) {
            int pi = partition(low, high);
            quickSort(low, pi-1);
            quickSort(pi+1, high);
        }
    }

    public int partition(int low, int high) {
        int pivot = arr[high];
        int i = low-1;

        for(int j=low; j<high; j++) {
            comparisons++;
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                interchanges++;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        interchanges++;

        return i+1;
    }
}
