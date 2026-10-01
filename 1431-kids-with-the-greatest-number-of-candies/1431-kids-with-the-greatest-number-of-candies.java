class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> ans = new ArrayList<>();
        int maxCandie = 0;

        for (int candie : candies) {
            maxCandie = Math.max(maxCandie, candie);
        }

        for (int candie : candies) {
            ans.add(candie + extraCandies >= maxCandie);
        }

        return ans;
    }
}