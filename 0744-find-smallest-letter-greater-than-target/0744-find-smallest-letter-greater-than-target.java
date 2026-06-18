// Problem: Find Smallest Letter Greater Than Target
// Link: https://leetcode.com/problems/find-smallest-letter-greater-than-target/
// Difficulty: Easy

// Approach:
// We need to find the smallest letter that is
// strictly greater than target.
// The letters array is sorted.
// Use Binary Search to find the
// first letter greater than target.
// Maintain:
//     start -> possible answer position
//     end   -> end of search space
// For every mid:
// Case 1:
//     letters[mid] > target
//     This letter can be the answer.
//     But there may be another smaller valid letter
//     on the left side.
//     So move left:
//         end = mid - 1
// Case 2:
//     letters[mid] <= target
//     This letter cannot be the answer because
//     we need a strictly greater letter.
//     So move right:
//         start = mid + 1
// After the loop:
//     start points to the first letter
//     strictly greater than target.
// If no greater letter exists:
//     start becomes letters.length
// Since letters are circular, return first letter:
//     letters[start % letters.length]

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target < letters[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return letters[start % letters.length];
    }
}

/*
    // Another Solution with O(n)
        for (char ch : letters) {
        if (ch > target)
            return ch;
        }

        return letters[0];
*/
