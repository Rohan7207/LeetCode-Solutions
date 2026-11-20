class Solution {
    public int maxSelectedElements(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[][] dp = new int[n + 1][2];

        int res = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int inc = 0; inc < 2; inc++) {
                int currVal = nums[i] + inc;
                int len = 1;

                // Option 1: Find currVal + 1 directly
                int j = findMethod(nums, i + 1, currVal + 1);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][0]);
                }

                // Option 2: Find currVal and then inc +1
                j = findMethod(nums, i + 1, currVal);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][1]);
                }

                dp[i][inc] = len;
                res = Math.max(res, dp[i][inc]);
            }
        }

        return res;
    }

    private int findMethod(int[] nums, int start, int target) {
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                ans = mid; // Store found index
                end = mid - 1; // Keep looking left for the first occurrence
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return ans;
    }
}

/*
    Recursion with Memoization: 6.06% 
    class Solution {

    private int n;
    private int[][] dp;

    public int maxSelectedElements(int[] nums) {
        n = nums.length;
        Arrays.sort(nums);

        dp = new int[n + 1][2];

        for(int[] row : dp) {
            row[0] = -1;
            row[1] = -1;
        }

        int res = 1;
        for(int i = 0; i < n; i++) {
            res = Math.max(res, Math.max(solve(i, 0, nums), solve(i, 1, nums)));
        }

        return res;
    }

    private int solve(int i, int inc, int[] nums) {
        if(dp[i][inc] != -1) {
            return dp[i][inc];
        }

        int currVal = nums[i] + inc;  // inc = 0/1
        int len = 1;

        // Option 1: Find currVal + 1 directly
        int j = findMethod(nums, i + 1, currVal + 1);
        if(j != -1) {
            len = Math.max(len, 1 + solve(j, 0, nums));
        }

        // Option 2: Find currVal and then inc +1
        j = findMethod(nums, i + 1, currVal);
        if(j != -1) {
            len = Math.max(len, 1 + solve(j, 1, nums));
        }

        return dp[i][inc] = len;
    }

    private int findMethod(int[] nums, int start, int target) {
        int end = n - 1;
        int ans = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(nums[mid] == target) {
                ans = mid;     // Store found index
                end = mid - 1;   // Keep looking left for the first occurrence
            } else if(nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return ans;
    }
    }
*/

/*
    nums = {2, 1, 5, 1, 1, 6, 5, 7}

    * The problems asks us to increase value of any element atmost by 1 (we can't leave ele as it is also).

    Suppose for above ex we pick this elements and update by 1
    Thought Process:
        nums(updated by 1) = {3, 1, 5, 2, 1, 7, 6, 8}

        The possible sorted consecutive elements are
         ->  {1, 2, 3}  = 3
         ->  {5, 6, 7, 8} = 4 (this is better ans) 

        * Note that while picking elements order doesn't matter, so in end we need sorted consecutive order, so we can sort the initial nums array which helps us to find consecutive orders and solve the problem using recursion where we increment curr ele by 1 or leave it as it is.

        sorted = {1, 1, 1, 2, 3, 5, 6, 7}
          possibilities => nums[i] + 0 = nums[i]
                           nums[i] + 1 = nums[i] + 1

           For i = 0;               |- (we can do this in two ways either 1 + 1 = 2 or find 2)

                                                /- Find a 1 and increment it to +1    
                nums[0] + 0 = 1 - (next need 2)
                                                \- Find 2 directly (using binary search)

                                                /- find 2 and +1 = 3
                nums[0] + 1 = 2 - (next need 3)
                                                \- Find 3 directly

        Points by above thought process:
            1. We can start from any index i to get maximum consecutive order
                    currVal = nums[i] + 0  -> check
                    currVal = nums[i] + 1  -> check
                                                 /- Find currVal and add +1
            2. currVal  --->  need(currVal + 1) -
                                                 \- Find (currVal + 1) (It becomes optimal since we use binary search)

            3. Find best lengths among all.

        * Since we have options and explore we will use recursion:
            Arrays.sort(nums);
            res = 1;
            // Explore from every index from an array since any element can give max consecutive order
            for(i = 0; i < n; i++) {
                // Options
                res = Math.max(max, solve(i, 0 (inc), nums),
                solve(i, 1 (inc), nums));
            }

            return res;

        int solve(int i, int inc, int[] nums) {
            int len = 1;  // curr val
            currVal = nums[i] + inc;

            // need currVal + 1
            // 1st option: Find(currVal + 1) directly
            int j = findMethod(i + 1, currVal + 1, nums);  // search currVal + 1 in nums from i + 1 to end
            if(j != -1) {  // We have got currVal + 1 
                // Found currVal + 1 at j index now find next consecutive order from there i.e. j and don't increment bcz we have already got currVal + 1
                Suppose nums = [1, 3, 2, 3, 4] and we start at index i = 0 with inc = 0.
                ***** Explanation
                // Call 1: solve(0, 0, nums)
                // • i = 0, inc = 0
                // • currVal = nums[0] + 0 = 1
                // • Goal: Find currVal + 1 → 2 starting from index i + 1 (1).
                // • j = findMethod(1, 2, nums): Looks through [3, 2, 3, 4] for 2.
                // • Result: Found 2 at index j = 2.
                // • Since j != -1, we make the recursive call:
                // 1 + solve(2, 0, nums)
                // (We add 1 to count the current number 1, move to index 2, and pass inc = 0 because nums[2] is already 2—no increment needed).

                // Call 2: solve(2, 0, nums)

                // • i = 2, inc = 0
                // • currVal = nums[2] + 0 = 2
                // • Goal: Find currVal + 1 → 3 starting from index 3.
                // • j = findMethod(3, 3, nums): Looks through [3, 4] for 3.
                // • Result: Found 3 at index j = 3.
                // • Recursive call:
                // 1 + solve(3, 0, nums)

                // Call 3: solve(3, 0, nums)

                // • i = 3, inc = 0
                // • currVal = nums[3] + 0 = 3
                // • Goal: Find currVal + 1 → 4 starting from index 4.
                // • j = findMethod(4, 4, nums): Looks through [4] for 4.
                // • Result: Found 4 at index j = 4.
                // • Recursive call:
                // 1 + solve(4, 0, nums)

                // Call 4: solve(4, 0, nums) (Base Case)

                // • i = 4, inc = 0
                // • currVal = nums[4] + 0 = 4
                // • Goal: Find 5 starting from index 5 (out of bounds).
                // • j = findMethod(5, 5, nums) → returns -1 (not found).
                // • The if (j != -1) block is skipped.
                // • Returns len = 1 (just the number 4 itself).
                *****

                len = max(len, 1(taken currVal) + solve(j, 0, nums));
            }

            // 2nd option: Find currVal and then increment +1
            j = findMethod(i + 1, currVal, nums); // search currVal in nums from i + 1 to end
            if(j != -1) {
                // 1 + solve() bcz we need to take currVal and inc +1 bcz we have found currVal but we need currVal + 1 so call for currVal + 1
                len = max(len, 1 + solve(j, 1, nums));
            }

            return len;  // To memoize it, only two parameters will change j and inc where inc is only 2  vals 0/1  so we take dp[n + 1][2]
        }   

    Now lets convert above recursion into bottom-up approach:

        dp[n + 1][2] = need to fill this
        int res = 1; 
        for(int i = n - 1; i >= 0; i--) {
            for(int inc = 0; inc < 2; inc++) {
                int currVal = nums[i] + inc;
                int len = 1;  // took currVal

                // Option 1: find currVal + 1 directly
                int j = findMethod(i + 1, currVal + 1, nums);  // We need ans of future ans so rev-loop
                if(j != -1) {
                    // len = max(len, 1(taken currVal) + solve(j, 0, nums));
                    len = max(len, 1 + dp[j][0]);
                }

                // Option 2: find currVal and then inc +1;
                int j = findMethod(i + 1, currVal, nums);
                if(j != -1) {
                    // len = max(len, 1(taken currVal) + solve(j, 1, nums));
                    len = max(len, 1 + dp[j][1]);
                }

                dp[i][c] = len;
                res = max(res, dp[i][inc]);  // We can do this by doing one more for loop like recursion
            }
        }

        return res;
*/