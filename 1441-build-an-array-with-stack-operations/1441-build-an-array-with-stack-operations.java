class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();

        int take = 1;
        int idx = 0;

        while(take <= n) {
            ans.add("Push");

            if(take != target[idx]) {
                ans.add("Pop");
                take++;
                continue;
            }

            if(idx == target.length - 1) {
                return ans;
            }

            idx++;
            take++;
        }

        return ans;
    }
}