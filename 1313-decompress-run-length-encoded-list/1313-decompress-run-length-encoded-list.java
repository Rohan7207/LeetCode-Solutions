// Problem: Decompress Run-Length Encoded List
// Link: https://leetcode.com/problems/decompress-run-length-encoded-list/
// Difficulty: Easy

 // Approach:
 // Use Two-Pass Traversal + Frequency Expansion.
 //
 // 1. The array is given in pairs:
 //      [frequency, value, frequency, value, ...]
 //
 // 2. First pass:
 //      - Visit every frequency.
 //      - Calculate the total size needed for the result array.
 //
 // 3. Create the result array using that calculated size.
 //
 // 4. Second pass:
 //      - Read each frequency-value pair.
 //      - Add the value to the result exactly `freq` times.
 //      - Maintain `idx` to track the next available position.
 //
 // 5. Return the decompressed array.
 
 // Time Complexity: O(n + k)
 // Space Complexity: O(k)
 //      where k is the size of the decompressed array.


class Solution {
    public int[] decompressRLElist(int[] nums) {
        int size = 0;
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            size += nums[2 * i];
        }

        int[] ans = new int[size];
        int idx = 0;
        for (int i = 0; i < n / 2; i++) {
            int freq = nums[2 * i];
            int val = nums[2 * i + 1];

            for (int j = 0; j < freq; j++) {
                ans[idx++] = val;
            }
        }

        return ans;
    }
}
