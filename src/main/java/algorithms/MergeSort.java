package algorithms;

public class MergeSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        if (delayMs > 0) {
            greenIndices.clear();
            yellowIndices.clear();
            redIndices.clear();
        }
        mergeSort(0, arr.length - 1, arr);

        // Mark all as green when done
        if (delayMs > 0) {
            redIndices.clear();
            yellowIndices.clear();
            for (int i = 0; i < arr.length; i++)
                greenIndices.add(i);
            pauseAndRender();
        }
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

        if (delayMs > 0) {
            // Current merge window is being processed.
            yellowIndices.clear();
            for (int i = l; i <= r; i++)
                yellowIndices.add(i);
        }

        while (ctrL < left.length && ctrR < right.length) {
            comparisons++;
            interchanges++;

            if (delayMs > 0) {
                // Target write position in the merge window.
                redIndices.clear();
                redIndices.add(ctr);
            }

            if (left[ctrL] <= right[ctrR]) {
                arr[ctr++] = left[ctrL++];
            } else {
                arr[ctr++] = right[ctrR++];
            }
            // Repaint after copy back to main array
            if (delayMs > 0)
                pauseAndRender();
        }

        while (ctrL < left.length) {
            interchanges++;
            redIndices.clear();
            redIndices.add(ctr);
            arr[ctr++] = left[ctrL++];
            if (delayMs > 0)
                pauseAndRender();
        }
        while (ctrR < right.length) {
            interchanges++;
            redIndices.clear();
            redIndices.add(ctr);
            arr[ctr++] = right[ctrR++];
            if (delayMs > 0)
                pauseAndRender();
        }
        if (delayMs > 0) {
            // Merge for this window is complete.
            redIndices.clear();
            yellowIndices.clear();
            pauseAndRender();
        }
    }
}
