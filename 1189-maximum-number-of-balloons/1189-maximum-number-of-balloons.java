// Problem: Maximum Number of Balloons
// Link: https://leetcode.com/problems/maximum-number-of-balloons/?envType=daily-question&envId=2026-06-22
// Difficulty: Easy

// Approach:
// We need to find how many times we can form the word "balloon"
// using the characters from the given text.
// The word "balloon" needs:
//     b -> 1 time
//     a -> 1 time
//     l -> 2 times
//     o -> 2 times
//     n -> 1 time
// So we count only these required characters from the text.
// After counting:
//     b tells how many balloons can be supported by character 'b'
//     a tells how many balloons can be supported by character 'a'
//     l / 2 tells how many balloons can be supported by character 'l'
//     o / 2 tells how many balloons can be supported by character 'o'
//     n tells how many balloons can be supported by character 'n'
// Since one missing/less character can stop us from forming more balloons,
// the answer is the minimum among all these values.
// Return:
//     min(b, a, l / 2, o / 2, n)

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxNumberOfBalloons(String text) {
        int b = 0;
        int a = 0;
        int l = 0;
        int o = 0;
        int n = 0;

        for (char ch : text.toCharArray()) {
            if (ch == 'b') b++;
            else if (ch == 'a') a++;
            else if (ch == 'l') l++;
            else if (ch == 'o') o++;
            else if (ch == 'n') n++;
        }

        return Math.min(b, Math.min(a, Math.min(l / 2, Math.min(o / 2, n))));
    }
}
