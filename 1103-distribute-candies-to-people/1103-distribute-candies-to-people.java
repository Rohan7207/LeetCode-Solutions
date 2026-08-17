class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int give = 1;
        int person = 0;

        while(candies > 0) {
            ans[person] += Math.min(give, candies);
            candies -= give;
            give++;

            person = (person + 1) % num_people;
        }

        return ans;
    }
}