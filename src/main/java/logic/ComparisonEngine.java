package logic;

import algorithms.SortingAlgorithm;
import utils.ArrayGenerator;

public class ComparisonEngine {
    private final ArrayGenerator generator = new ArrayGenerator();

    public int[] generate(int size, String type) {
        switch(type.toLowerCase()) {
            case "sorted":
                return generator.generateSorted(size);
            case "reverse":
                return generator.generateReverse(size);
            case "random":
                return generator.generateRandom(size);
            default:
                throw new IllegalArgumentException("Invalid array type: " + type);
        }
    }

    private SortingAlgorithm  createSortingAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "bubble":
                return new algorithms.BubbleSort();
            case "insertion":
                return new algorithms.InsertionSort();
            case "selection":
                return new algorithms.SelectionSort();
            case "quick":
                return new algorithms.QuickSort();
            case "merge":
                return new algorithms.MergeSort();
            case "heap":
                return new algorithms.HeapSort();
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + name);
        }
    }

    public SortResult benchmark(String algorithmName, int size, int runs, int[] baseArray) {
        SortingAlgorithm sorter = createSortingAlgorithm(algorithmName);
        SortResult result = new SortResult();

        result.algorithmName = algorithmName;
        result.arraySize = baseArray.length;
        result.runs = runs;

        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;

        long totalComparisons = 0;
        long totalInterchanges = 0;

        for(int i=0; i<runs; i++) {
            int[] arrayCopy = baseArray.clone();
            long startTime = System.nanoTime();
            sorter.sort(arrayCopy);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            totalTime += duration;
            minTime = Math.min(minTime, duration);
            maxTime = Math.max(maxTime, duration);

            totalComparisons += sorter.getComparisons();
            totalInterchanges += sorter.getInterchanges();
        }

        result.averageRunningTime = totalTime / runs;
        result.minRunningTime = minTime;
        result.maxRunningTime = maxTime;
        result.comparisons = totalComparisons / runs;
        result.interchanges = totalInterchanges / runs;

        return result;
    }

}
