class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // Sort array to handle duplicates
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); 
        helper(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void helper(int start, int[] nums, List<Integer> current, List<List<Integer>> res) {
        res.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            //Skip the duplicate
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);
            helper(i + 1, nums, current, res);
            current.remove(current.size() - 1);
        }
    }
}