class Solution {
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < nums.length; i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];

            for(int j = 0; j < freq; j++) {
                list.add(val);
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