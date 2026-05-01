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


//One pass approach single-pass version, you eliminate the need for left and right arrays by calculating areas immediately as you pop elements from the stack
/*
    line 7: Use n+1 iterations (i <= n) to process a "dummy" 0-height bar at the end    
    line 8: Treat the index at n as height 0 to force a flush of the stack
    line 11: The height of the rectangle we are calculating
    ;ine 13: Width calculation without precomputed arrays:
            Right boundary is the current index 'i'
            Left boundary is the NEW top of the stack (the nearest smaller element to the left)

    Note: i = 6
        Special case:
        i == n
        So:
        currHeight = 0
        This forces all remaining bars to process.
*/

/*
    int n = heights.length;
    int[] left = new int[n]; //left nearest value
    int [] right = new int[n]; //right nearest value
    Stack<Integer> s = new Stack<>();

    The first loop uses a Monotonic Stack helps to find the index of the first bar to the right that is shorter than the current bar. If none exists, it defaults to n 
    for(int i = n - 1; i >= 0; i--){
        while(s.size() > 0 && heights[s.peek()] >= heights[i]){
            s.pop();
        }
        right[i] = s.isEmpty() ? n : s.peek();
        s.push(i);
    }

    while(!s.isEmpty()){
        s.pop();
    }

    The second loop uses a Monotonic Stack to find the index of the first bar to the left side that is shorter than the current bar. If none exists, it defaults to -1 
    for(int i = 0; i < n; i++){
        while(s.size() > 0 && heights[s.peek()] >= heights[i]){
            s.pop();
        }
        left[i] = s.isEmpty() ? -1 : s.peek();
        s.push(i);
    }

    right = 1 6 4 4 6 6   left = -1 -1 1 2 1 4

    int ans = 0;
    for(int i = 0; i < n; i++){
        int width = right[i] - left[i] - 1;   //Calculating width is main problem 
        int currarea = heights[i] * width;
        ans = Math.max(ans, currarea);
    }
    return ans;
*/