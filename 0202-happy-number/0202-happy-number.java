// Problem: Happy Number
// Link: https://leetcode.com/problems/happy-number/
// Difficulty: Easy

// Approach:
// Repeatedly replace the number with
// the sum of squares of its digits.
// Use Floyd’s Cycle Detection algorithm
// with slow and fast pointers:
//     - slow moves one transformation step
//     - fast moves two transformation steps
// If the number becomes 1,
// it is a happy number.
// If slow and fast meet at a number
// other than 1, a cycle exists,
// so the number is not happy.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
      
        do {
            slow = square(slow);
            fast = square(square(fast));
        } while (slow != fast);

        return slow == 1;
    }
    
    public int square(int num) {
        
        int ans = 0;
        
        while(num > 0) {
            int remainder = num % 10;
            ans += remainder * remainder;
            num /= 10;
        }
        
        return ans;
    }
}
