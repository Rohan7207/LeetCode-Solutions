// Problem: Statistics from a Large Sample
// Link: https://leetcode.com/problems/statistics-from-a-large-sample/
// Difficulty: Medium

// Approach:
// The sample is given as a frequency array where count[i] represents
// how many times the value i occurs. Since the possible values are only
// 0 to 255, we can process the frequency array directly without
// constructing or sorting the actual sample.
//
// 1. Traverse count[0...255] and calculate:
//
//    - totalCount → total number of elements.
//    - totalSum   → sum of all elements.
//    - minimum    → first value whose frequency is greater than 0.
//    - maximum    → last value whose frequency is greater than 0.
//    - mode       → value having the highest frequency.
//
// 2. Calculate the mean:
//
//       mean = totalSum / totalCount
//
// 3. Find the two middle positions:
//
//       pos1 = (totalCount - 1) / 2
//       pos2 = totalCount / 2
//
//    This handles both odd and even sample sizes.
//
// 4. Traverse the frequency array again while maintaining a cumulative
//    frequency. The cumulative frequency tells us how many elements
//    have appeared so far in the sorted sample.
//
//    When cumulative > pos1, the current value is the first middle value.
//    When cumulative > pos2, the current value is the second middle value.
//
// 5. Calculate the median:
//
//       median = (middle1 + middle2) / 2.0
//
// 6. Return:
//
//       [minimum, maximum, mean, median, mode]

// Time Complexity: O(256) → O(1)
// Space Complexity: O(1)


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

        // Find the values at the middle positions
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
