package algorithms;

public class MergeSort extends AbstractSortingAlgo {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        mergeSort(0, arr.length - 1, arr);
    }

    public void mergeSort(int l, int r, int[] arr) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(l, m, arr);
            mergeSort(m + 1, r, arr);
            merge(l, m, r, arr);
        }

    }

    public void merge(int l, int m, int r, int[] arr) {
        int[] left = new int[m - l + 1];
        int[] right = new int[r - m];

        int ctrL = 0;
        for (int i = l; i <= m; i++) {
            left[ctrL++] = arr[i];
        }
        int ctrR = 0;
        for (int i = m + 1; i <= r; i++) {
            right[ctrR++] = arr[i];
        }

        ctrR = 0;
        ctrL = 0;
        int ctr = l;
        while (ctrL < left.length && ctrR < right.length) {
            comparisons++;
            interchanges++;
            if (left[ctrL] <= right[ctrR]) {
                arr[ctr++] = left[ctrL++];
            } else {
                arr[ctr++] = right[ctrR++];
            }
            if(delayMs>0) pauseAndRender();
        }

        while (ctrL < left.length) {
            interchanges++;
            arr[ctr++] = left[ctrL++];
            pauseAndRender();
        }
        while (ctrR < right.length) {
            interchanges++;
            arr[ctr++] = right[ctrR++];
            pauseAndRender();
        }

    }
}
