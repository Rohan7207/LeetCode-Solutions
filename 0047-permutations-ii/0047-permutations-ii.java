class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteHelper(nums, 0, res);
        return res;
    }

    private void permuteHelper(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            res.add(permutation);
            return;
        }

        Set<Integer> seen = new HashSet<>();

        for (int j = i; j < nums.length; j++) {
            if (seen.contains(nums[j]))
                continue;

            seen.add(nums[j]);

            swap(nums, i, j);
            permuteHelper(nums, i + 1, res);
            swap(nums, i, j); // Helps to backtrack
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
    Another approach:
        List<List<Integer>> res  = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), used, nums);
        return res;
    

    private void backtrack(List<List<Integer>> res, List<Integer> curr, boolean[] used, int[] nums){
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            //!used[i - 1]=the previous element was not included while built of combination
            //and used[i]=if we've already used this specific element in our current path 
            if((i > 0 && nums[i] == nums[i - 1]) && !used[i - 1] || used[i]) continue;  
            curr.add(nums[i]);
            used[i] = true;
            backtrack(res, curr, used, nums);
            used[i] = false;
            curr.remove(curr.size() - 1);
        }
    }

*/