class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> ans = new ArrayList<>();
        int max = 0;

        for(int candie : candies) {
            max = Math.max(max, candie);
        }

        for(int i = 0; i < candies.length; i++) {
            ans.add(candies[i] + extraCandies >= max);
        }

        return ans;
    }
}