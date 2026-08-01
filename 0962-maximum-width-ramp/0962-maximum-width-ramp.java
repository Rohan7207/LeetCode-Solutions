class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] stack = new int[n];
        int top = -1;

        // Fill the stack array with indices in decreasing order of their values
        for(int i = 0; i < n; i++) {
            if(top == -1 || nums[i] < nums[stack[top]]) {
                stack[++top] = i;
            }
        }

        int ans = 0;
        for(int j = n - 1; j >= 0; j--) {
            while(top >= 0 && nums[stack[top]] <= nums[j]) {
                ans = Math.max(ans, j - stack[top--]);
            }
        }

        return ans;
    }
}

/*
     Stack<Integer> s = new Stack<>();
        int n = nums.length;

        // Fill the stack with indices in decreasing order of their values
        for(int i = 0; i < n; i++) {
            if(s.isEmpty() || nums[i] < nums[s.peek()]) {
                s.push(i);
            }
        }

        int ans = 0;

        for(int j = n - 1; j >= 0; j--) {
            while(!s.isEmpty() && nums[s.peek()] <= nums[j]) {
                ans = Math.max(ans, j - s.peek());
                // Because this is the largest possible j for that left index. No future j can give a larger width.
                s.pop();
            }
        }

        return ans;
*/