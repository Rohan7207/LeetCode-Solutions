class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> ans = new ArrayList<>();
        int lessThanTargetCount = 0;
        int targetCount = 0;

        for (int num : nums) {
            if(num < target) {
                lessThanTargetCount++;
            } else if (num == target) {
                targetCount++;
            }
        }

        for(int i = 0; i < targetCount; i++) {
            ans.add(lessThanTargetCount + i);
        }

        return ans;
    }
}

/*
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
            }
        }

        return ans;
    }
*/