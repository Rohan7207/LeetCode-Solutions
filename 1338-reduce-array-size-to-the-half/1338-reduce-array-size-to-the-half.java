// Problem: Reduce Array Size to The Half
// Link: https://leetcode.com/problems/reduce-array-size-to-the-half/
// Difficulty: Medium

// Approach:
// Use Frequency Counting + Greedy.
//
// 1. Count how many times each distinct element appears in the array.
//
// 2. To reduce the array by at least half, we should remove the
//    elements that occur most frequently first.
//
// 3. Extract all frequencies and sort them in descending order.
//
// 4. Keep selecting the largest frequency and add it to
//    removedElements.
//
// 5. Count how many distinct values (sets) we selected.
//
// 6. Stop as soon as at least half of the original array has been
//    removed.

// Time Complexity: O(n + d log d)
// Space Complexity: O(d)
// where d = number of distinct elements.


class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Extract frequencies and sort them in descending order
        List<Integer> frequencies = new ArrayList<>(freq.values());
        frequencies.sort(Collections.reverseOrder());

        int target = arr.length / 2;
        int removedElements = 0;
        int setCounts = 0;
        
        for (int count : frequencies) {
            removedElements += count;
            setCounts++;

            // As soon as we've removed at least half the array, we are done
            if (removedElements >= target) {
                break;
            }
        }

        return setCounts;
    }
}
