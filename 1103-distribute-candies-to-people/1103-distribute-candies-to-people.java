class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int give = 1;
        int person = 0;

        while (candies > 0) {
            int amount = Math.min(give, candies);
            ans[person] += amount;
            candies -= amount;

            give++;
            person = (person + 1) % num_people;
        }

        return ans;
    }
}