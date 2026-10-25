// Problem: Find the Winner of an Array Game
// Link: https://leetcode.com/problems/find-the-winner-of-an-array-game/
// Difficulty: Medium

// Approach:
// 1. Initialize val = arr[0] as the current winner and count = 0.
// 2. Traverse the array from index 1.
// 3. Compare the current winner val with arr[i]:
//    a. If val > arr[i], the current winner wins again; increment count.
//    b. Otherwise, arr[i] becomes the new winner; update val and reset count = 1.
// 4. If count == k, return val because it has won k consecutive rounds.
// 5. If the loop ends, return val. The maximum element eventually becomes
//    the permanent winner, even if k consecutive wins weren't reached.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;

        int count = 0;
        int val = arr[0];
        for (int i = 1; i < n; i++) {
            if (val > arr[i]) {
                count++;
            } else {
                val = arr[i];
                count = 1;
            }

            if (count == k) {
                return val;
            }
        }

        return val;
    }
}
