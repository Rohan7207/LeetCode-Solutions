class Solution {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            set.add(num);
        }

        int ans = 0;
        for(int i = k; i < 201; i += k) {
            if(!set.contains(i)) {
                ans = i;
                break;
            }
        }

        return ans;
    }
}