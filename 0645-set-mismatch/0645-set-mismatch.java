class Solution {
    public int[] findErrorNums(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int[] ans = new int[2];
        for (int i = 1; i <= nums.length; i++) {
            if (freq.containsKey(i)) {
                if (freq.get(i) == 2) {
                    ans[0] = i;
                }
            } else {
                ans[1] = i;
            }
        }

        return ans;
    }
}