import java.util.*;

class Solution {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int k = n - 2; k >= 1; k--) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int l = k + 1; l < n; l++) {
                map.put(nums[l] - nums[k], map.getOrDefault(nums[l] - nums[k], 0) + 1);
            }

            for (int i = 0; i < k; i++) {
                for (int j = i + 1; j < k; j++) {
                    count += map.getOrDefault(nums[i] + nums[j], 0);
                }
            }
        }

        return count;
    }
}