package algorithms;

public class InsertionSort {
    int comparisons = 0;
    int interchanges = 0;
    int[] arr;

    public int getComparisons() {
        return comparisons;
    }
    public  int getInterchanges() {
        return interchanges;
    }

    public InsertionSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        for(int i=1; i<arr.length; i++) {
            int tmp = arr[i];
            int j = i-1;
            for(; j>=0; j--) {
                comparisons++;
                if(arr[j] > tmp) {
                    interchanges++;
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = tmp;
        }
    }
}
