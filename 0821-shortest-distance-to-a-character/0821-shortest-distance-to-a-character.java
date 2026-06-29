// Problem: Shortest Distance to a Character
// Link: https://leetcode.com/problems/shortest-distance-to-a-character/
// Difficulty: Easy

// Approach:
// For every index, the shortest distance to character c can come from:
//     1. Nearest c on the left side
//     2. Nearest c on the right side
// We solve this using two traversals.
// Step 1: Left to right traversal
// Maintain:
//     prev = previous index where c appeared
// For every index:
//     If current character is c:
//          update prev
//     Distance from left side:
//          i - prev
//     Store it in ans array.
// Step 2: Right to left traversal
// Maintain:
//     next = next index where c appeared
// For every index:
//     If current character is c:
//          update next
//     Distance from right side:
//          next - i
//     Update:
//          ans[i] = minimum(left distance, right distance)
// Finally return ans.

// Time Complexity: O(n)
// Space Complexity: O(1) (excluding output array)


class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        int prev = Integer.MIN_VALUE / 2;

        // left closest c
        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == c) {
                prev = i;
            }

            ans[i] = i - prev;
        }

        int next = Integer.MAX_VALUE / 2;

        // right closest c
        for (int i = n - 1; i >= 0; i--) {

            if (s.charAt(i) == c) {
                next = i;
            }

            ans[i] = Math.min(ans[i], next - i);
        }

        return ans;
    }
}

/*
    List<Integer> list = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                list.add(i);
            }
        }

        int[] ans = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            int min = Integer.MAX_VALUE;
            for(int idx : list) {
                min = Math.min(min, Math.abs(idx - i));
            }

            ans[i] = min;
        }

        return ans;
*/
