class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /*At any point if sum is < target then we can go ahead,if sum=target
        then return that combination into list and if sum > target then return
        there is no point to go ahead
        time = O(n^t) n= no.of candidates t=total amount like if there is 2
        we can use 2 until target is achieved*/

        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, candidates, target, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] candidates, int target, List<Integer> current, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(current));
        } else if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(res, candidates, target - candidates[i], current, i);
            current.remove(current.size() - 1);
        }
    }
}