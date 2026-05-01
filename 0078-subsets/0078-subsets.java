class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void helper(int[] nums, int idx, List<Integer> current, List<List<Integer>> res) {
        res.add(new ArrayList<>(current)); 

        for (int i = idx; i < nums.length; i++) {
            current.add(nums[i]);
            helper(nums, i + 1, current, res);
            current.remove(current.size() - 1); 
        }
    }
}