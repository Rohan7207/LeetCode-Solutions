class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); //Sort array to handle duplicates
        backtrack(res, candidates, target, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] candidates, int target, List<Integer> currentCom, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(currentCom));
        }

        for (int i = start; i < candidates.length; i++) {
            //Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //Terminate if remaining sum becomes negative
            if (target - candidates[i] < 0) {
                break;
            }
            currentCom.add(candidates[i]);
            backtrack(res, candidates, target - candidates[i], currentCom, i + 1);
            currentCom.remove(currentCom.size() - 1);
        }
    }
}