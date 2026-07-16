// Problem: Assign Cookies
// Link: https://leetcode.com/problems/assign-cookies/
// Difficulty: Easy

// Approach:
// Sort both the greed array and the cookie sizes array
// in non-decreasing order.
// Use two pointers:
//     i -> current child (smallest remaining greed)
//     j -> current cookie (smallest remaining cookie)
// Compare the current cookie with the current child's greed.
// If:
//     cookie >= greed
// then the cookie can satisfy the child.
// Assign the cookie, move to the next child,
// and also move to the next cookie.
// Otherwise, the cookie is too small to satisfy even the
// least greedy remaining child. Since all other children
// have equal or greater greed, this cookie cannot satisfy
// anyone. Discard it by moving only the cookie pointer.
// Continue until either all children are satisfied
// or all cookies are used.
// The number of satisfied children is equal to the
// number of children processed (i).

// Time Complexity:
//     O(m log m + n log n)
//     m = number of children
//     n = number of cookies
// Space Complexity:
//     O(1)
//     (Ignoring the sorting algorithm's internal stack)


class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;

        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }
            
            j++;
        }

        return i;
    }
}
