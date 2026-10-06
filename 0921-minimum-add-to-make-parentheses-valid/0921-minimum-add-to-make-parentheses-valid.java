// Problem: Minimum Add To Make Parentheses Valid
// Link: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/?envType=daily-question&envId=2026-10-05
// Difficulty: Medium

// Approach:
// 1. We need every ')' to have a matching '(' before it.
// 2. `open` stores the number of currently unmatched '('.
// 3. When we see '(' → increase `open`.
// 4. When we see ')' and an unmatched '(' exists → match them by decreasing `open`.
// 5. When we see ')' but `open == 0` → this ')' has no matching '('.
//    So we need one '(' to fix it; increment `missingOpen`.
// 6. After traversing the string, any remaining `open` parentheses need a ')' each.
// 7. Therefore, total additions = unmatched '(' + missing '('.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int missingOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    missingOpen++;
                }
            }
        }
        
        return open + missingOpen;
    }
}
