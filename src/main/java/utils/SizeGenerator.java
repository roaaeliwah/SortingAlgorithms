package utils;

public class SizeGenerator {
    public int[] generateSizes(int min, int max) {
        if (min == max) {
            return new int[] { min };
        }

        //minimum of 8 sample sizes or the range
        int count = Math.min(8, max - min + 1);
        int[] sizes = new int[count];
        //evenly spaced sample sizes between min and max
        for (int i = 0; i < count; i++) {
            sizes[i] = min + (int) Math.round((double) i * (max - min) / (count - 1));
        }
        return sizes;
    }
}
