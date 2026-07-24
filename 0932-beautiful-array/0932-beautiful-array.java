class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> result = helper(n);

        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private List<Integer> helper(int n) {
        if(n == 1) {
            List<Integer> base = new ArrayList<>();
            base.add(1);
            return base;
        }

        List<Integer> oldPart = helper((n + 1) / 2);
        List<Integer> evenPart = helper(n / 2);

        List<Integer> ans = new ArrayList<>();

        for(int x : oldPart) {
            ans.add(2 * x - 1);
        }

        for(int x : evenPart) {
            ans.add(2 * x);
        }

        return ans;
    }
}