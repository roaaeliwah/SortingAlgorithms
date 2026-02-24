package algorithms;

public class SelectionSort {
    int comparisons = 0;
    int interchanges = 0;
    int[] arr;
    public SelectionSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        for(int i=0; i<arr.length-1; i++) {
            int min = i;

            //to find minimum's index
            for(int j = i+1; j<arr.length; j++) {
                comparisons++;
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }

            //to swap
            if(min != i) {
                interchanges++;
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
    }
}
