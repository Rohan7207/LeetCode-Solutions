// Problem: Jewels and Stones
// Link: https://leetcode.com/problems/jewels-and-stones/
// Difficulty: Easy

// Approach:
// We need to count how many stones are also jewels.
// The string jewels contains all characters that are considered jewels.
// The string stones contains the stones we have.
// To check quickly whether a stone is a jewel,
// store all characters from jewels in a HashSet.
// Then scan every character in stones.
// If the character exists in the HashSet,
// it means that stone is a jewel, so increase count.
// Finally, return count.

// Time Complexity: O(j + s)
//     j = length of jewels
//     s = length of stones
// Space Complexity: O(j)
//     HashSet stores jewel characters


class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < jewels.length(); i++) {
            char ch = jewels.charAt(i);

            set.add(ch);
        }

        int count = 0;

        for (int i = 0; i < stones.length(); i++) {
            char ch = stones.charAt(i);

            if (set.contains(ch)) {
                count++;
            }
        }

        return count;
    }
}
