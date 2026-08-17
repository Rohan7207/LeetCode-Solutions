class Solution {
    public double[] sampleStats(int[] count) {
        int totalCount = 0;
        long totalSum = 0;

        double maximum = -1;
        double minimum = -1;

        int maxFreq = 0;
        int mode = 0;

        // Find minimum, maximum, mean and mode
        for (int i = 0; i < 256; i++) {
            if (count[i] > 0) {
                totalCount += count[i];
                totalSum += (long) i * count[i];

                // First value with frequency > 0
                if (minimum == -1) {
                    minimum = i;
                }

                // Last value with frequency > 0
                maximum = i;

                // Value with highest frequency
                if (count[i] > maxFreq) {
                    maxFreq = count[i];
                    mode = i;
                }
            }
        }

        double mean = (double) totalSum / totalCount;

        // Positions of the two middle elements
        int pos1 = (totalCount - 1) / 2; // Even
        int pos2 = totalCount / 2; // Odd

        int middle1 = -1;
        int middle2 = -1;
        int cumulative = 0;

        for (int i = 0; i < 256; i++) {
            cumulative += count[i];

            if (middle1 == -1 && cumulative > pos1) {
                middle1 = i;
            }

            if (middle2 == -1 && cumulative > pos2) {
                middle2 = i;
                break;
            }
        }

        double median = (middle1 + middle2) / 2.0;

        return new double[] { minimum, maximum, mean, median, mode };
    }
}