class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Greedy + Counting Sort

        int maxCost = 0;

        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        int[] freq = new int[maxCost + 1];

        for (int cost : costs) {
            freq[cost]++;
        }

        int bars = 0;

        for (int cost = 0; cost <= maxCost; cost++) {
            if (freq[cost] == 0) continue;

            int canBuy = coins / cost;

            int buy = Math.min(freq[cost], canBuy);

            bars += buy;

            coins -= buy * cost;
        }

        return bars;
    }
}