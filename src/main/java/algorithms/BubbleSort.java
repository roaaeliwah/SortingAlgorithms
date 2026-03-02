package algorithms;

public class BubbleSort {
    int comparisons = 0;
    int interchanges = 0;
    int[] arr;

    public int getComparisons() {
        return comparisons;
    }
    public  int getInterchanges() {
        return interchanges;
    }

    public BubbleSort(int[] arr) {
        this.arr = arr;
    }

    public void Sort() {
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-i-1; j++) {
                comparisons++;
                if(arr[j]>arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    interchanges++;
                }
            }
        }
    }
}
