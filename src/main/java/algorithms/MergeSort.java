package algorithms;

public class MergeSort {
    int comparisons = 0;
    int interchanges = 0;
    int[] arr;

    public int getComparisons() {
        return comparisons;
    }
    public  int getInterchanges() {
        return interchanges;
    }

    public MergeSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        mergeSort(0, arr.length - 1);
    }

    public void mergeSort(int l, int r) {
        if(l<r) {
            int m = (l+r)/2;
            mergeSort(l, m);
            mergeSort(m+1, r);
            merge(l, m, r);
        }

    }

    public void merge(int l, int m, int r) {
        int[] left = new int[m-l+1];
        int[] right = new int[r-m];

        int ctrL =0;
        for(int i = l; i <= m; i++) {
            left[ctrL++] = arr[i];
        }
        int ctrR =0;
        for(int i = m+1; i<=r; i++) {
            right[ctrR++] = arr[i];
        }

        ctrR=0;
        ctrL=0;
        int ctr=l;
        while(ctrL<left.length && ctrR<right.length) {
            comparisons++;
            interchanges++;
            if(left[ctrL] <= right[ctrR]) {
                arr[ctr++] = left[ctrL++];
            }
            else  {
                arr[ctr++] = right[ctrR++];
            }
        }

        while(ctrL<left.length) {
            interchanges++;
            arr[ctr++]=left[ctrL++];
        }
        while(ctrR<right.length) {
            interchanges++;
            arr[ctr++]=right[ctrR++];
        }

    }
}
