package algorithms;

public class HeapSort {
    int comparisons = 0;
    int interchanges = 0;
    int[] arr;

    public int getComparisons() {
        return comparisons;
    }
    public  int getInterchanges() {
        return interchanges;
    }

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

}
