class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;

        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        int[] freq = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            freq[nums[i]]++;
        }

        long[] gcdCount = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            int count = 0;

            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            long pairs = (long) count * (count - 1) / 2;

            for (int multiple = 2 * g; multiple <= max; multiple += g) {
                pairs -= gcdCount[multiple];
            }

            gcdCount[g] = pairs;
        }

        long[] prefixCount = new long[max + 1];
        long total = 0;

        for (int g = 1; g <= max; g++) {
            total += gcdCount[g];
            prefixCount[g] = total;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = 1;
            int right = max;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (prefixCount[mid] > queries[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            ans[i] = left;
        }

        return ans;
    }
}