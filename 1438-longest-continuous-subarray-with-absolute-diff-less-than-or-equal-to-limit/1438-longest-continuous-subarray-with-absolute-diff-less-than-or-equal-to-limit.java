class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int ans = 0;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        for (int right = 0; right < n; right++) {
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
                maxDeque.pollLast();
            }

            maxDeque.offerLast(nums[right]);

            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
                minDeque.pollLast();
            }

            minDeque.offerLast(nums[right]);

            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (maxDeque.peekFirst() == nums[left]) {
                    maxDeque.pollFirst();
                }

                if (minDeque.peekFirst() == nums[left]) {
                    minDeque.pollFirst();
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}

/*
    Got tle but above is same sliding window technique since in this we have to scan window for new max and min repeatedly to tackle we maintain deque structure where maxdeque keeps max  and mindeque keeps min and when window in not valid if first element(always max in window) of window is nums[left] then we remove it and same goes for minDeque
    class Solution { 
    public int longestSubarray(int[] nums, int limit) { 
        int n = nums.length; 
        int ans = 0; 
 
        int max = Integer.MIN_VALUE; 
        int min = Integer.MAX_VALUE; 
 
        int left = 0; 
        for (int right = 0; right < n; right++) { 
            max = Math.max(max, nums[right]); 
            min = Math.min(min, nums[right]); 
 
            while (left < n && max - min > limit) { 
                if (max == nums[left]) { 
                    max = Integer.MIN_VALUE; 
                } else if (min == nums[left]) { 
                    min = Integer.MAX_VALUE; 
                } 
 
                left++; 
                int temp = left; 
 
                while (temp <= right) { 
                    max = Math.max(max, nums[temp]); 
                    min = Math.min(min, nums[temp]); 
 
                    temp++; 
                } 
            } 
 
            if (max - min <= limit) { 
                ans = Math.max(ans, right - left + 1); 
            } 
        } 
 
        return ans; 
    } 
} 
*/
