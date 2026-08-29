// Problem: Number of Burgers with No Waste of Ingredients
// Link: https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/
// Difficulty: Medium

// Approach:
// Use Algebra + Equation Solving.
//
// 1. Let:
//      x = number of Jumbo Burgers
//      y = number of Small Burgers
//
// 2. A Jumbo Burger uses 4 tomato slices and 1 cheese slice.
//    A Small Burger uses 2 tomato slices and 1 cheese slice.
//
// 3. Therefore:
//
//      4x + 2y = tomatoSlices
//      x + y = cheeseSlices
//
// 4. From the cheese equation:
//
//      y = cheeseSlices - x
//
// 5. Substitute into the tomato equation:
//
//      4x + 2(cheeseSlices - x) = tomatoSlices
//
//      4x + 2cheeseSlices - 2x = tomatoSlices
//
//      2x = tomatoSlices - 2cheeseSlices
//
//      x = (tomatoSlices - 2cheeseSlices) / 2
//
// 6. Similarly:
//
//      y = (4cheeseSlices - tomatoSlices) / 2
//
// 7. Therefore:
//
//      val1 = tomatoSlices - 2 * cheeseSlices
//      val2 = 4 * cheeseSlices - tomatoSlices
//
// 8. Both values must be:
//      - non-negative
//      - even
//
//    Otherwise, a valid combination is impossible.
//
// 9. Return:
//
//      [number of Jumbo Burgers, number of Small Burgers]

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> ans = new ArrayList<>();

        int val1 = tomatoSlices - 2 * cheeseSlices;
        int val2 = 4 * cheeseSlices - tomatoSlices;

        if (val1 < 0 || val1 % 2 != 0 || val2 < 0 || val2 % 2 != 0) {
            return ans;
        }

        ans.add(val1 / 2);
        ans.add(val2 / 2);

        return ans;
    }
}
