// Problem: Water Bottles
// Link: https://leetcode.com/problems/water-bottles/
// Difficulty: Easy

// Approach:
//
// 1. Initially, we have `numBottles` full bottles. After drinking
//    them, we get the same number of empty bottles.
//
// 2. We can exchange `numExchange` empty bottles for 1 full bottle.
//    Drinking that bottle gives us 1 empty bottle back.
//
// 3. Therefore, every exchange effectively consumes
//    (numExchange - 1) empty bottles overall.
//
// 4. Instead of simulating every exchange, calculate the maximum
//    number of additional bottles using:
//
//       (numBottles - 1) / (numExchange - 1)
//
// 5. Add these additional bottles to the original bottles.
//
// 6. Return the total number of bottles we can drink.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        return numBottles + ((numBottles - 1) / (numExchange - 1));
    }
}
