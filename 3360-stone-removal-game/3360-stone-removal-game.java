// Problem: Stone Removal Game
// Link: https://leetcode.com/problems/stone-removal-game/?envType=daily-question&envId=2026-11-25
// Difficulty: Easy

// Approach:
// 1. Alice starts by removing 10 stones.
// 2. Bob removes 9 stones.
// 3. The number of stones removed decreases by 1
//    after every turn.
// 4. Continue while enough stones are available.
// 5. If the remaining stones are insufficient for the
//    current removal, the current player loses.
// 6. Count the number of successful removals.
// 7. If the number of successful removals is odd,
//    Alice made the last successful move and wins.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public boolean canAliceWin(int n) {
        int stones = 10;
        int count = 0;

        if (n < 10) {
            return false;
        }

        while (n >= 0) {
            if (n - stones < 0) {
                break;
            }

            n -= stones;
            stones--;
            count++;
        }

        return count % 2 == 1;
    }
}
