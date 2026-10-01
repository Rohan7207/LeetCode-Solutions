// Problem: Kids With the Greatest Number of Candies
// Link: https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
// Difficulty: Easy

// Approach:
// 1. First find the maximum number of candies any kid currently has.
//
// 2. For each kid, give them the extraCandies conceptually.
//
// 3. If candies[i] + extraCandies >= maxCandie,
//    then that kid can have the greatest number of candies.
//
// 4. Store this condition directly in the answer list.

// Time Complexity: O(n)
// Space Complexity: O(n) — for the output list


class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> ans = new ArrayList<>();
        int maxCandie = 0;

        for (int candie : candies) {
            maxCandie = Math.max(maxCandie, candie);
        }

        for (int candie : candies) {
            ans.add(candie + extraCandies >= maxCandie);
        }

        return ans;
    }
}
