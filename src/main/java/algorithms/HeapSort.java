package algorithms;

public class HeapSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        if (delayMs > 0)
            greenIndices.clear();
        int n = arr.length;

        // Build heap (rearrange array)
        // n/2 nodes
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i, arr);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            interchanges++;

            if (delayMs > 0) {
                greenIndices.add(i);
                redIndices.clear();
                yellowIndices.clear();
                pauseAndRender();
            }

            heapify(i, 0, arr);
        }

        if (delayMs > 0) {
            greenIndices.add(0);
            redIndices.clear();
            yellowIndices.clear();
            pauseAndRender();
        }
    }

    // find largest among root, left child and right child and swap with root
    // call recursively for the affected sub-tree
    // n -> heap size
    public void heapify(int n, int i, int[] arr) {
        int largest = i; // Initialize root
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (delayMs > 0) {
            yellowIndices.clear();
            yellowIndices.add(i);
        }

        // If left child is larger than root
        // check if l exists in heap
        if (l < n) {
            if (delayMs > 0) {
                redIndices.clear();
                redIndices.add(l);
                pauseAndRender();
            }

            comparisons++;
            if (arr[l] > arr[largest]) {
                largest = l;
            }
        }

        // If right child is larger than largest so far
        if (r < n) {
            comparisons++;
            redIndices.clear();
            redIndices.add(r);
            if (delayMs > 0)
                pauseAndRender();

            if (arr[r] > arr[largest]) {
                largest = r;
            }
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            interchanges++;

            if (delayMs > 0) {
                redIndices.clear();
                pauseAndRender();
            }

            // Recursively heapify the affected sub-tree
            heapify(n, largest, arr);
        }
    }

}
