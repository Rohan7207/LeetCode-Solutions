class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        int[] prefixSum = new int[n + 1];
        prefixSum[0] = nums.get(0);

        for(int i = 1; i < n; i++) {  // O(n)
            prefixSum[i] = prefixSum[i - 1] + nums.get(i);
        }

        for(int i = 0; i < n; i++) {  // O(n)
            for(int len = l; len <= r && i + len - 1 < n; len++) {  // O(r - l + 1)
                int j = i + len - 1;  // valid start of j 
                sum = prefixSum[j] - (i == 0 ? 0 : prefixSum[i - 1]);  // this gives sum of i to j

                if(sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans; 
    }
}

/*
    Brute Force: O(n * r) and O(1)
     public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {   // O(n)
            int sum = 0;
            for(int j = i; j < n && (j - i + 1 <= r); j++) {  // O(r)
                sum += nums.get(j);

                if(j - i + 1 >= l && sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans; // O(n * r) and O(1)
    }
*/

/*
    Brute Force would be use try every subarray:
        for(int i = 0; i < n; i++) {   // O(n)
            int sum = 0;
            for(int j = i; j < n && (j - i + 1 <= r); j++) {  // O(r)
                sum += nums.get(j);

                if(j - i + 1 >= l && sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans; // O(n * r) and O(1)

    Optimal, Approach 2: 

        nums = {3, -2, 1, 4}  l = 3 r = 4

        - Instead of starting j at i which gives 1 size subarray though we need l size subarray we are wasting time of checking invalid size subarray so instead start j from valid l size subarray, so j should start from j = i + l - 1;  // i = 0 j = 0 + 3 - 1 = 2,
        - Now we need sum from i to j, in previous we were calculting it in for loop but now since we are starting j at valid l index indicating valid subarray we need a sum from i to j but we cannot apply loop to calculate this which again makes same complexity as previous brute force approach so to get sum from i to j in costant time we can use ****prefix sum approach.

        int[] prefixSum = new int[n + 1];
        prefixSum[0] = nums.get(0);
        for(int i = 1; i < n; i++) {  // O(n)
            prefixSum[i] = prefixSum[i - 1] + nums.get(i);
        }

        for(int i = 0; i < n; i++) {  // O(n)
            for(len = l; len <= r && i + len - 1 < n; len++) {  // O(r - l + 1)
                j = i + len - 1;  // valid start of j 
                sum = prefixSum[j] - prefixSum[i - 1];  // this gives sum of i to j

                if(sum > 0) {
                    ans = min(ans, sum);
                }
            }

            sum += nums.get(i);
        }

        return ans;  // O(n * (r - l + 1)) and O(n)
*/