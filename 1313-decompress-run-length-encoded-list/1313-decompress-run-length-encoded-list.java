class Solution {
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < nums.length - 1; i++) {
            if(i % 2 == 0) {
                for(int j = 0; j < nums[i]; j++) {
                    list.add(nums[i + 1]);
                }
            }
        }

        int len = list.size();
        int[] ans = new int[len];
        for(int i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}