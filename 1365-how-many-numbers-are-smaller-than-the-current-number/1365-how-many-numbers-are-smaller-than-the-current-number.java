// Problem: How Many Numbers Are Smaller Than the Current Number
// Link: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
// Difficulty: Easy

// Approach:
// Use sorting + HashMap to find how many numbers are smaller than
// each element.
//
// 1. Create a copy of the original array and sort it.
//
// 2. In the sorted array, the first occurrence of a value is exactly
//    the number of elements smaller than that value.
//
// 3. Store this information in a HashMap:
//       value → first index in sorted array
//
// 4. Traverse the original array and replace each value with its
//    corresponding count from the HashMap.
//
// 5. Return the resulting array.

// Time Complexity: O(n log n)
//   Sorting takes O(n log n), and the remaining traversals take O(n).
//
// Space Complexity: O(n)
//   Copy array + HashMap.


class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            map.putIfAbsent(copy[i], i);
        }

        for(int i = 0; i < n; i++) {
            copy[i] = map.get(nums[i]);
        }
        
        return copy;
    }
}
