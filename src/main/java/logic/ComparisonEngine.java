package logic;

import algorithms.AbstractSortingAlgorithm;
import utils.ArrayGenerator;
import utils.SorterFactory;

import java.io.IOException;

public class ComparisonEngine {
    private final ArrayGenerator generator = new ArrayGenerator();
    private final SorterFactory sorterFactory = new SorterFactory();

    public int[] generateFromFile(String filePath) throws IOException {
        return generator.loadFromFile(filePath);
    }

    public int[] generate(int size, String type) {
        return generator.generate(size, type);
    }

    public SortResult benchmark(String algorithmName, int size, int runs, int[] baseArray) {
        AbstractSortingAlgorithm sorter = sorterFactory.createSorter(algorithmName);
        SortResult result = new SortResult();

        result.algorithmName = algorithmName;
        result.arraySize = baseArray.length;
        result.runs = runs;

        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;

        long totalComparisons = 0;
        long totalInterchanges = 0;

        for (int i = 0; i < runs; i++) {
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

        // convert nanoseconds to milliseconds
        result.averageRunningTime = (totalTime / (double) runs) / 1_000_000.0;
        result.minRunningTime = minTime / 1_000_000.0;
        result.maxRunningTime = maxTime / 1_000_000.0;
        result.comparisons = totalComparisons / runs;
        result.interchanges = totalInterchanges / runs;

        return result;
    }

}
