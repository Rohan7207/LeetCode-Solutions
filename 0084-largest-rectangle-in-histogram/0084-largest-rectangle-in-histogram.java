// Problem: Largest Rectangle in Histogram
// Link: https://leetcode.com/problems/largest-rectangle-in-histogram/
// Difficulty: Hard

// Approach:
// Use a monotonic increasing stack
// to store indices of histogram bars.
// Traverse all bars including one extra
// iteration with height 0 to process
// remaining bars in stack.
// For each bar:
//     - While current height is smaller than
//       the height at stack top,
//       pop the stack and calculate area.
//     - Width is determined using current index
//       and the new stack top after popping.
//     - Update maximum area.
// Push current index into stack.
// Return the maximum rectangle area found.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> s = new Stack<>();

        for(int i = 0; i <= n; i++){
            int currHeight = (i == n) ? 0 : heights[i];

            while(!s.isEmpty() && heights[s.peek()] >= currHeight){
                int h = heights[s.pop()]; 

                int width = s.isEmpty() ? i : i - s.peek() - 1;
                maxArea = Math.max(maxArea, h * width);
            }
            s.push(i);
        }

        return maxArea;
    }
}
