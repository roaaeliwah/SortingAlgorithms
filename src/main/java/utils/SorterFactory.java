package utils;

import algorithms.*;

public class SorterFactory {
        public static AbstractSortingAlgorithm createSorter(String algorithmName) {
            switch (algorithmName.toLowerCase()) {
                case "bubble":
                    return new BubbleSort();
                case "insertion":
                    return new InsertionSort();
                case "selection":
                    return new SelectionSort();
                case "quick":
                    return new QuickSort();
                case "merge":
                    return new MergeSort();
                case "heap":
                    return new HeapSort();
                default:
                    throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithmName);
            }
        }
}
