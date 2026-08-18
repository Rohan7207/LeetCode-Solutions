// Problem: Unique Number of Occurrences
// Link: https://leetcode.com/problems/unique-number-of-occurrences/
// Difficulty: Easy

// Approach:
// Use a HashMap to count how many times each distinct number occurs,
// then use a HashSet to ensure that all occurrence counts are unique.
//
// 1. Traverse the array and store the frequency of every number in
//    the HashMap.
//
// 2. Traverse the frequencies stored in the HashMap.
//
// 3. Add each frequency to a HashSet.
//
// 4. If a frequency already exists in the Set, two different numbers
//    have the same occurrence count, so return false.
//
// 5. If all frequencies are unique, return true.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();
        for (int val : freq.values()) {
            if (!set.add(val)) {
                return false;
            }
        }

        return true;
    }
}
