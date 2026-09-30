// Problem: Maximum Nesting Depth of Two Valid Parentheses Strings
// Link: https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/?envType=daily-question&envId=2026-09-30
// Difficulty: Medium

// Approach:
// 1. Maintain `depth` = current nesting depth.
// 2. We want to divide the parentheses between two groups so that
//    neither group gets unnecessarily deep.
// 3. The simplest way is to alternate groups based on the parity of
//    the current depth.
// 4. When '(' is encountered:
//      - Increase depth first.
//      - Assign the parenthesis to group 1 if depth is odd,
//        otherwise group 0.
// 5. When ')' is encountered:
//      - It belongs to the same group as its matching '('.
//      - So use the current depth's parity first.
//      - Then decrease depth.
// 6. Thus nested levels alternate between group 0 and group 1,
//    keeping the maximum depth of both groups balanced.

// Time Complexity: O(n)
// Space Complexity: O(n) for the result array.


class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char ch = seq.charAt(i);

            if (ch == '(') {
                depth++;
                res[i] = (depth % 2 == 0) ? 0 : 1;
            } else {
                res[i] = (depth % 2 == 0) ? 0 : 1;
                depth--;
            }
        }

        return res;
    }
}
