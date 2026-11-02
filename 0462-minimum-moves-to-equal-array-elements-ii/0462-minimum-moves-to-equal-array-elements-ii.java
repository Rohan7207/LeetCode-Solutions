class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int midEle = nums[n / 2];
        int moves = 0;

        for(int i = 0; i < n / 2; i++) {
            moves += Math.abs(nums[i] - midEle);
            moves += Math.abs(nums[n - i - 1] - midEle);  // right elements
        }

        return moves;
    }
}


/*
    My solution with sorting = O(nlogn) above uses O(n) by finding median
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int midEle = nums[n / 2];
        int moves = 0;

        for (int num : nums) {
            moves += Math.abs(num - midEle);
        }

        // Above for loop can be optimized as, O(n / 2)
        for(int i = 0; i < n / 2; i++) {
            moves += Math.abs(nums[i] - midEle);
            moves += Math.abs(nums[n - i - 1] - midEle);  // right elements
        }
        
        return moves;
    }
*/

/*
    Optimal Approach: O(n) 
*/