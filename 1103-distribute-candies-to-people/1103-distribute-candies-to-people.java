// Problem: Distribute Candies to People
// Link: https://leetcode.com/problems/distribute-candies-to-people/
// Difficulty: Easy

// Approach:
// Simulate the distribution of candies one gift at a time.
//
// 1. `give` stores the number of candies to give next.
//    It starts from 1 and increases by 1 after every turn.
//
// 2. `person` stores the current person's index.
//
// 3. Give `min(give, candies)` candies to the current person.
//    Using `min()` handles the final turn when fewer candies remain
//    than the required gift amount.
//
// 4. Subtract the actual amount given from `candies`.
//
// 5. Move to the next person using:
//
//       person = (person + 1) % num_people
//
//    This makes the distribution cycle back to the first person.
//
// 6. Continue until all candies are distributed.

// Time Complexity: O(sqrt(candies)) approximately
// Space Complexity: O(num_people)


class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int give = 1;
        int person = 0;

        while (candies > 0) {
            int amount = Math.min(give, candies);
            ans[person] += amount;
            candies -= amount;

            give++;
            person = (person + 1) % num_people;
        }

        return ans;
    }
}
