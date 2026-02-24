package utils;

import java.util.Random;

public class ArrayGenerator {
    public int[] generateSorted(int size) {
        int[] sorted = new int[size];
        for(int i=1; i<=size; i++) sorted[i-1] = i;
        return sorted;
    }

    public int[] generateReverse(int size) {
        int[] reverse = new int[size];
        for(int i=size; i>0; i--) reverse[i-1] = i;
        return reverse;
    }

    public int[] generateRandom(int size) {
        Random rand = new Random();
        int[] random = new int[size];
        for(int i=0; i<size; i++) {
            random[i] = rand.nextInt(size) + 1;
        }
        return random;
    }
}