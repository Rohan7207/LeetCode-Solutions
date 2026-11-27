class Solution {

    private int cheapestPrice = 1501;
    private int[][] dp;
    private int[] copies;

    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        dp = new int[1501][1501];
        copies = new int[n];  // copies[i] = count of copies if I buy ith item

        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        for(int i = 0; i < n; i++) {
            cheapestPrice = Math.min(cheapestPrice, items[i][1]);

            int copy = 0;
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }

                if(items[j][0] % items[i][0] == 0) {
                    copy++;
                }
            }

            copies[i] = copy;
        }

        return solve(n, budget, items);
    }

    private int solve(int n, int budget, int[][] items) {
        if(n == 0) {
            return budget / cheapestPrice;
        }

        if(dp[n][budget] != -1) {
            return dp[n][budget];
        }

        int take = 0;
        int price = items[n - 1][1];

        // Take item
        if(price <= budget) {
            take = 1 + copies[n - 1] + solve(n - 1, budget - price, items);
        }

        int skip = solve(n - 1, budget, items);

        return dp[n][budget] = Math.max(take, skip);
    }
}

/*
    1. factorj % factori == 0
    2. Buying multiple copies of the same item i does not give additional free copies through item i
        means if we took item i -> and copies j and j1 and if we buy same item i once again we don't get free item this time
    3. The same item j can be received multiple times for free if it is received from purchases of different item types.

        means i -> j, j1   i -> getting j and j1 again is not possible according to problem
        but if we buy i1 -> then getting same j and j1 is possible if it satisfies factorj % factori == 0

    Thougth Process:
      ith item -> first time  -> we can get copies only first time (factorj % factori == 0)
            \>  sec time -> can't get copies for second and not in future 

      - Since if we buy an item and we get copies for first time only we can store the count of copies for each item in copies[] and use it calculate answer.

        // Fill copies array which is O(n ^ 2) but it is fine bcz constraint are small.
        for(i = 0; i < n; i++) {
            int copy = 0;

            for(j = 0; j < n; j++) {
                if(i == j) continue;  // Skip same item

                if(items[j][0] % items[i][0] == 0) {
                    copy++;
                }
            }

            copies[i] = copy;
        }

        Ex: items = [[6,2],[2,6],[3,4]], budget = 9

        - By seeing example we should be able see that it similar to knapsack problem where we have total budget instead of total weight, and in items we have items avialabel where each items have price or weight and we can buy each item any no.of times, which is like Unbounded Knapsack but different thing is about one extra thing i.e. copies which is based on factors.

        - So it is similar to knapsack problem where we have choice that we can take an item or leave it and we will use same knapsack type code.

        Knapsack Problem:

            There are two ways to solve knapsack type problems like:
                Ex: items = [[6,2],[2,6],[3,4]], budget = 9

                1. solve(i = 0, budget, items)
                        To access item, items[i]

                2. solve(n, budget, items)
                    To access item, items[n - 1]

            - Though they are same in working format like writing recusrion code but prefer to write second one using n bcz we can easily convert to bottom-up appraoch.

        Ex: items = [[6,2],[2,6],[3,4]], budget = 9

        Suppose we have taken item 
                    -> First  time  1 (itself) + copies[i]
            (3, 4) /
                    -> We would not buy it second time imediately bcz we can't explore other possible itmes giving copies, like suppose budget is 9 and item3price = 4 and copies=3 we have res=4, and if we buy same item once agian whose res=5, budget left would be only 1 and if price={2, 3, 4} then we would not explore other possible giving item copies so first explore all items.
            Explore (2, 6) -> res += 1 + copies[i]

        Knapsack tree diagram for problem
            Ex: items = [[6,2],[2,6],[3,4]], budget = 9, n = 3

                            n = 3, b = 9, res = 0
            1+copies[n - 1] /           \ skip
                           /
    solve(n-1,b-items[i]=4=>5, items)
                    n = 2, b = 5, res=1+1=2
                take /        \skip
            can't take          n=1, b=5, res=2   
            bcz price is       /take                \ skip
            b=5 and price=6   /res+=1+copies[n-1]    \     
                           n=0, b=3, res=3+0=3 
                           /(We have reached n=0, but budget is left, and problem asks to take as 
                          / items as much as possible. So, since we took all possible copies as we
                         / reached n=0, means we have took all possible copies, means now we can take
                        / only item not copies, to take as much maximum items within budget we would   definetly try to buy the item with cheapest one to maximum no.of copies within allowed budget)

                    take only one cheapest item as many times
                        3 / 2 (since it is cheapest) = 1 means we can buy one item with b = 3 and we can't buy any item with b = 1
                So, return budget b / cheapestPrice; (base case) and don't add copies to it, there be case such that we are not buying this cheapest price item in this recursion branch, but since we are doing recursion there could be scenario where we buy copies of cheapestItem first and reach to base case which would be optimal maximum copies branch.

    Psuedo Code:
            solve(n, budget, items);
            also store cheapestPrice and copies as global variable

        solve(int n, int budget, int[][] items) {
            if(n == 0) {
                return budget / cheapestPrice;
            }

            int take = 0;
            int skip = 0;
            int price = items[items[n-1][1]];

            // take item
            if(price <= budget) {
                take = 1 + copies[n - 1] + solve(n - 1, budget - price, items);
            }

            skip = solve(n - 1, budget, items);

            return max(take, skip);  // also write memoization, here only n and budget is changing so take 2D DP.
        }


*/