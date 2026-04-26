class Solution {
    public List<List<Integer>> permute(int[] nums) {
        //Use backtracking
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), nums, used);
        return res;
    }

    private void backtrack(List<List<Integer>> res , List<Integer> current, int [] nums, boolean[] used){
        if(current.size() == nums.length){   //when current size == to nums add it to result list
            res.add(new ArrayList<>(current));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(!used[i]){
                current.add(nums[i]);
                used[i] = true;
                backtrack(res, current, nums, used);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }
}

 /* we use current to add the element of nums and keep track of used ele
    Consider [1,2,3] firstly we use 1 and mark it as true in used array
    and take 2 and 3 i.e. [1,2,3] whose length is equal to nums length then
    we should add it to res
*/