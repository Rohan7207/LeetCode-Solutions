// Problem: Check If a String Contains All Binary Codes of Size K
// Link: https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
// Difficulty: Medium

// Approach:
// 1. There are exactly 2^k possible binary codes of length k.
// 2. Instead of generating all codes and searching for them,
//    examine every length-k window directly in s.
// 3. Store each distinct window in a HashSet.
// 4. If the number of unique windows becomes 2^k, every possible
//    binary code must have appeared → return true.
// 5. The current solution creates a new String for every window,
//    so it takes O(k) work per window.
// 6. To optimize further, represent each binary window as an integer.
// 7. Maintain the current k-bit value using a sliding window:
//      - shift the current value left by 1
//      - add the new bit
//      - keep only the last k bits using a mask.
// 8. Then each window can be processed in O(1), giving O(n) time.

// Time Complexity: O(n)
// Space Complexity: O(2^k)


class Solution {
    public boolean hasAllCodes(String s, int k) {
        int count = 0;
        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - k; i++) {
            String str = s.substring(i, i + k);

            if (set.add(str)) {
                count++;
            }
        }

        return count == Math.pow(2, k);
    }
}
