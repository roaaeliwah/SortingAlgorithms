package main;

import algorithms.SelectionSort;
import utils.ArrayGenerator;

public class Main {
    public static void main(String[] args) {
        int[] arr = new ArrayGenerator().generateRandom(10);

        // 2. Print original array
        System.out.println("Original array:");
        printArray(arr);

        // 3. Run SelectionSort
        SelectionSort sorter = new SelectionSort(arr);
        sorter.sort();

        // 4. Print sorted array
        System.out.println("Sorted array:");
        printArray(arr);

        // 5. Print metrics
//        System.out.println("Comparisons: " + sorter.comparisons);
//        System.out.println("Swaps: " + sorter.interchanges);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}