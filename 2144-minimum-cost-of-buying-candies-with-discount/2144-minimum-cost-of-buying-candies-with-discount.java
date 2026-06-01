class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int min = 0;
       
        for(int i = cost.length - 1; i >= 0; i--) {
            if((cost.length - 1 - i) % 3 != 2) {
                min += cost[i];
            }
        }

        return min;
    }
}