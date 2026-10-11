// Problem: Least Number of Unique Integers after K Removals
// Link: https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
// Difficulty: Medium

// Approach:
// 1. Count the frequency of every integer using a HashMap.
// 2. Create a bucket array where bucket[f] stores the number of distinct integers
//    appearing exactly f times.
// 3. Initialize uniqueElements with the number of distinct integers.
// 4. Traverse frequencies from 1 to n, because removing less frequent integers
//    first costs fewer deletions.
// 5. For each frequency f, calculate how many complete integers can be removed:
//    elementToRemove = min(bucket[f], k / f).
// 6. Decrease k by elementToRemove * f and decrease uniqueElements accordingly.
// 7. If k < f, stop because there are not enough deletions to remove another
//    integer with that frequency.
// 8. Return uniqueElements.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Count the frequency of each number
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // bucket[i] stores how many unique integers have a frequency of exactly 'i'
        int[] bucket = new int[n + 1];
        for (int count : freqMap.values()) {
            bucket[count]++;
        }

        int uniqueElements = freqMap.size();

        for (int freq = 1; freq <= n; freq++) {
            if (bucket[freq] > 0) {
                // Calculate how many elements of this frequency we can completely remove
                int elementToRemove = Math.min(bucket[freq], k / freq);

                k -= elementToRemove * freq;
                uniqueElements -= elementToRemove;

                // If k is exhausted or not enough to clear even 1 element of this frequency
                if (k < freq) {
                    break;
                }
            }
        }

        return uniqueElements;
    }
}
