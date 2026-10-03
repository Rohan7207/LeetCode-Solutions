class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int take = 1;
        int idx = 0;

        while (idx < target.length && take <= n) {
            ans.add("Push");

            if (take == target[idx]) {
                idx++;
            } else {
                ans.add("Pop");
            }

            take++;
        }

        return ans;
    }
}