// Problem: Guess Number Higher or Lower
// Link: https://leetcode.com/problems/guess-number-higher-or-lower/
// Difficulty: Easy

// Approach:
// Use Binary Search on the range [1, n].
// For every middle number:
//     guess(mid)
// returns:
//     0  -> correct number found
//    -1  -> picked number is smaller
//           than mid, search left half
//     1  -> picked number is larger
//           than mid, search right half
// Continue until the number is found.

// Time Complexity: O(log n)
// Space Complexity: O(1)


/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);

            if(res == 0) return mid;
            else if(res == -1) high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }
}
