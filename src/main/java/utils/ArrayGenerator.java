package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

public class ArrayGenerator {
    public int[] loadFromFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath))).trim();
        return Arrays.stream(content.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public int[] generate(int size, String type) {
        switch (type.toLowerCase()) {
            case "sorted":
                return generateSorted(size);
            case "reverse":
                return generateReverse(size);
            case "random":
                return generateRandom(size);
            default:
                throw new IllegalArgumentException("Invalid array type: " + type);
        }
    }

    public int[] generateSorted(int size) {
        int[] sorted = new int[size];
        for (int i = 1; i <= size; i++)
            sorted[i - 1] = i;
        return sorted;
    }

    public int[] generateReverse(int size) {
        int[] reverse = new int[size];
        for (int i = 0; i < size; i++) {
            reverse[i] = size - i;
        }
        return reverse;
    }

    public int[] generateRandom(int size) {
        Random rand = new Random();
        int[] random = new int[size];
        for (int i = 0; i < size; i++) {
            random[i] = rand.nextInt(size) + 1;
        }
        return random;
    }
}