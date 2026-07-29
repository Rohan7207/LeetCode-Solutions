// Problem: Largest Time for Given Digits
// Link: https://leetcode.com/problems/largest-time-for-given-digits/
// Difficulty: Medium

// Approach:
// Since there are only 4 digits, the total number of possible arrangements
// is just 4! = 24. Therefore, we can brute-force every possible ordering.
// Treat every arrangement as:
//      H1 H2 : M1 M2
// where:
// H1,H2 -> Hour
// M1,M2 -> Minute
// Generate all permutations using four nested loops while ensuring
// that each index is used exactly once.
// For every arrangement:
// 1. Form the hour:
//        hour = H1 * 10 + H2
// 2. Form the minute:
//        minute = M1 * 10 + M2
// 3. Check if the time is valid:
//        hour < 24
//        minute < 60
// 4. Convert the valid time into total minutes:
//        total = hour * 60 + minute
//    Comparing total minutes automatically compares complete times,
//    so the largest total minutes corresponds to the latest time.
// 5. Keep track of the maximum valid time found.
// After checking all 24 permutations:
// - If no valid time exists, return an empty string.
// - Otherwise, convert the stored total minutes back into
//   hours and minutes and format it as "HH:MM".

// Time Complexity: O(4!) ≈ O(1)
// Space Complexity: O(1)


class Solution {
    public String largestTimeFromDigits(int[] arr) {
        int maxTime = -1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i)
                    continue;

                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j)
                        continue;

                    for (int l = 0; l < 4; l++) {
                        if (l == i || l == j || l == k)
                            continue;

                        int hour = arr[i] * 10 + arr[j];

                        int minute = arr[k] * 10 + arr[l];

                        if (hour < 24 && minute < 60) {
                            int total = hour * 60 + minute;

                            if (total > maxTime) {
                                maxTime = total;
                            }
                        }
                    }
                }
            }
        }

        if (maxTime == -1) {
            return "";
        }

        int hrs = maxTime / 60;
        int mins = maxTime % 60;
        return String.format("%02d:%02d", hrs, mins);
    }
}
