class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[k];
        long[] prevCount = new long[k];

        for (int i = 0; i < n; i++) {
            // Total no.of subarray remainder count ending at point i
            long[] currCount = new long[k];

            int currEleRem = nums[i] % k;
            currCount[currEleRem]++;

            for (int oldRem = 0; oldRem < k; oldRem++) {
                int newRem = (int) ((long) oldRem * nums[i] % k) % k;

                currCount[newRem] += prevCount[oldRem];
            }

            prevCount = currCount;

            for (int x = 0; x < k; x++) {
                res[x] += prevCount[x];
            }
        }

        return res;
    }
}

/*
    Improved Brute Force With Recursion Precompute + Memoization: O(n ^ 2) and O(n ^ 3) in worst case
    int[][] memo;

    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        int n = nums.length;
        memo = new int[n][n];

        for(int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int x = solve(i, j, nums, k);

                res[x]++;
            }
        }

        return res;
    }

    int solve(int i, int j, int[] nums, int k) {
        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        if(i == j) {
            return memo[i][j] = nums[j] % k;
        }

        int prev = solve(i, j - 1, nums, k);

        return memo[i][j] = (int) ((long) prev * nums[j] % k) % k;
    }
*/

/*
    Brute Force: O(n ^ 3) with TLE
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int x = solve(i, j, nums, k);

                res[x]++;
            }
        }

        return res;
    }

    private int solve(int i, int j, int[] nums, int k) {
        long prod = 1;

        for(int t = i; t <= j; t++) {
            prod = (prod * nums[t]) % k;
        }

        return (int) prod;
    }
*/

/* 
    - If we have array of size 5 and k = 3. since prefix and suffix can be empty, We have to find result of size k where result[x] = count of ways for x-value(remainder count product % k)
    result[0] = 0 counts, result[1] = 1 counts and result[2] = 2 counts
    - We have to take product such that if we choose first element has prefix 0 and last two as suffix 3, 4 then we should count the remainder of product of remaining elements at position 1 and 2. We can choose prefix and suffix however such that there should be atleast one element and return count of remainder of subarray

    x-value = (no.of doing operation) => no.of possible subarrays

    Total ways of finding subarray: (Product of subarrya) % k = x, res[x]++;

    Brute Force:  O(n ^ 3)
        for(int i = 0; i < n; j++) {
            for(int j = 0; j < n; j++) {
                int x = solve(i, j, nums, k);
                res[x]++;
            }
        }

        return res;

    solve(i, j, nums, K) {
        long prod = 1;

        for(int k = i; k <= j; k++) {
            prod = (prod * nums[k]) % K;
        }

        return (int) prod;
    }

    Here in above we are performing product from i to j to many times to avoid this we need to store the product of i to j - 1 and can calculate product from i to j as prod[j - 1] * nums[j]
        Ex: {1, 2, 3, 4, 5}
        0..2 = (1 * 2 * 3) % k
        0..3 = (1 * 2 * 3 * 4) % k 

        So instead of doing this we can store previous product and get in O(1) time
        i.e. (stored[i][j - 1] * 4 % k) % k and we can use previous(it could be used bcz modulo k doesn't effect the ans bcz (a * b) % k = (a % k * b % k) % k)

    Improved Brute Force With Recusrsion + Memoization :  O(n ^ 3) (in worst case) but it is efficient caalculating product
        for(int i = 0; i < n; j++) {
            for(int j = 0; j < n; j++) {
                int x = solve(i, j, nums, k);
                res[x]++;
            }
        }

        return res;

    solve(i, j, nums, k) {
        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        if(i == j) {
            return memo[i][j] = nums[j] % k;
        }

        // (i...j) % k
        int prev = solve(i, j - 1);
        return memo[i][j] = (int) ((long) prev * nums[j] % k) % k;
    }

    Here suppose i = 1 and j = 4 is passed then recursion will ask product of 1...3 and 1...2 and 1...1(base case return that ele)

    Optimal Way: We acutaly don't need which subarray gives us the remainder atlast we only need remainder count no matter from which subarray right. So we keep on  updating res and ask that at this endPoint of i we just ask what is count of each remainder till previous index and based on that we will compute for curr index.

    nums = {1, 2, 3, 4, 5} k = 3
    index 1: Possible subarrays
                [2] = 2 % 3 = 2
                [1, 2] = 2 % 3 = 2
            Then we store count of each remainder, 
            count_1[0] = 0, count_1[1] = 0, count_1[2] = 2

    index 2: Possible subarrays, only that value is appended at each subarray
                [2, 3] = 6 % 3 = 0
                [1, 2, 3] = 6 % 3 = 0
            - Observe one thing, we need to use value of previous counts and there is one thing
                in recursion we are asking to find the previous product % k and then multiply with 
                nums[j] right like 
                prev = solve(i, j - 1);  (prev * nums[j] % k)
                Here also we will take prev non-zero values like 
                count[2] = 2, newRem = (2 (prevRem) * 3 % 3) % k = 0 and that's what we need.
                count_2[0] = 2

                int newRem = (oldRem  * nums[i] % k) % k;
                count[newRem] += count[oldRem];

            // Overall O(n * k)
            for(int i = 0; i < n; i++) {  O(n)
                long[] currCount = new long[k];

                // Curr element rem
                int currEleRem = nums[i] % k;
                currCount[currEleRem]++;
                for(int oldRem = 0; oldRem < k; oldRem++) {   O(k)
                    int newRem = (oldRem * nums[i] % k) % k;
                    count[newRem] += prevCount[oldRem];
                }

                prevCount = count;

                
                for(x = 0; x < k; x++) {   O(k)
                    res[x] += prev[x];
                }
            }
            return res;
*/