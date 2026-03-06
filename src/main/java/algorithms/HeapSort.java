package algorithms;

public class HeapSort extends AbstractSortingAlgorithm {

    @Override
    public void sort(int[] arr) {
        comparisons = 0;
        interchanges = 0;
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i, arr);

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            interchanges++;
            if (delayMs > 0)
                pauseAndRender();

            // call max heapify on the reduced heap
            heapify(i, 0, arr);
        }
    }

    public void heapify(int n, int i, int[] arr) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root and node has children (node's index is less
        // than heap size)
        if (l < n) {
            comparisons++;
            if (arr[l] > arr[largest]) {
                largest = l;
            }
        }

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest]) {
            comparisons++;
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            interchanges++;
            if(delayMs>0) pauseAndRender();

            // Recursively heapify the affected sub-tree
            heapify(n, largest, arr);
        }
    }

}
