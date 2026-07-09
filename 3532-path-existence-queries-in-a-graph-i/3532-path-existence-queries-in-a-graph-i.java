class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        boolean[] ans = new boolean[queries.length];
        int[] component = new int[nums.length];
        int currComponent = 0;
        component[0] = currComponent;

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) > maxDiff) {
                currComponent++;
            }

            component[i] = currComponent;
        }

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (component[u] == component[v]) {
                ans[i] = true;
            } else {
                ans[i] = false;
            }
        }

        return ans;
    }
}

/*
    Since nums is sorted, do you really need to compare every pair of indices?

Look at this:

nums = [1,2,3,4,10,11,12,20]

maxDiff = 2

Adjacent differences:

1 ✓
1 ✓
1 ✓
6 ✗
1 ✓
1 ✓
8 ✗

Notice something?

Every time you encounter

nums[i] - nums[i-1] > maxDiff

a new connected component starts.

Otherwise,

the current element belongs to the same component as the previous one.
*/