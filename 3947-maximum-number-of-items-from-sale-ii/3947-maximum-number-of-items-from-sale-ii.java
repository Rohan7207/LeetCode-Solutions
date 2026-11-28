class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Step 1: Count occurrences of each factor
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Calculate the number of eligible free item types
        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0)
                continue;

            int count = 0;

            // Count items whose factors are multiples of f
            for (int j = f; j <= n; j += f) {
                count += freq[j];
            }

            // Exclude the item itself
            gain[f] = count - 1;
        }

        // Step 3: Sort items by increasing price
        int[][] sorted = items.clone();

        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        long ans = 0;

        // Step 4: Purchase useful boosted copies first
        for (int[] item : sorted) {
            int factor = item[0];
            int price = item[1];

            // Boosted purchase is not better than normal purchase
            if (price > 2L * minPrice) {
                break;
            }

            int available = gain[factor];

            // Maximum boosted purchases possible
            int take = (int) Math.min(
                    (long) available,
                    budget / price);

            // Each boosted purchase gives 2 copies
            ans += 2L * take;

            budget -= take * price;
        }

        // Step 5: Spend remaining budget on cheapest item
        ans += budget / minPrice;


        return (int) ans;
    }
}

/*
    Recursion with Memoization
    class Solution {

    private int n;
    private int[] copies;
    private int[][] dp;

    public int maximumSaleItems(int[][] items, int budget) {
        n = items.length;
        dp = new int[n + 1][budget + 1];

        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        copies = new int[n];

        for(int i = 0; i < n; i++) {
            int count = 0;

            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }

                if(items[j][0] % items[i][0] == 0) {
                    count++;
                }
            }

            copies[i] = count;
        }

        return solve(0, budget, items);
    }

    private int solve(int i, int budget, int[][] items) {
        if(i == n || budget == 0) {
            return 0;
        }

        if(dp[i][budget] != -1) {
            return dp[i][budget];
        }

        int skip = solve(i + 1, budget, items);

        int take = 0;
        int price = items[i][1];

        for(int x = 1; x * price <= budget; x++) {
            int cost = x * price;
            int freeCopies = Math.min(x, copies[i]);

            int totalCopies = x + freeCopies;

            take = Math.max(take, totalCopies + solve(i + 1, budget - cost, items));
        }

        return dp[i][budget] = Math.max(take, skip);
    }
}
*/

/*
    Bottom-Up approach of above recusrion but since there are n * (budget + 1) states, and budget value can be to high upto 10^9, so instead of 2D dp array above is space-optimized bottom-up DP with two 1D arrays.

    dp[b] = maximum copies obtainable using the already processed items
        with budget b.
    class Solution {
        public int maximumSaleItems(int[][] items, int budget) {
            int n = items.length;
            int[][] dp = new int[n + 1][budget + 1];

            int[] copies = new int[n];

            for (int i = 0; i < n; i++) {
                int count = 0;

                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (items[j][0] % items[i][0] == 0) {
                        count++;
                    }
                }

                copies[i] = count;
            }

            // Base case:
            dp[n][0] = 0;

            for (int i = n - 1; i >= 0; i--) {
                for (int b = 0; b <= budget; b++) {
                    int skip = dp[i + 1][b];
                    int take = 0;
                    int price = items[i][1];

                    for (int x = 1; x * price <= b; x++) {
                        int cost = x * price;
                        int freeCopies = Math.min(x, copies[i]);

                        int totalCopies = x + freeCopies;

                        take = Math.max(take, totalCopies + dp[i + 1][b - cost]);
                    }

                    dp[i][b] = Math.max(take, skip);
                }
            }

            // return solve(0, budget, items);
            return dp[0][budget];
        }
    }
*/

/*  
    // Even when 2d dp was converted to 1d Dp still got MLE, then understood that it was not dp based problem, it is greedy + sorting problem
    class Solution {
        public int maximumSaleItems(int[][] items, int budget) {
            int n = items.length;
            int[] copies = new int[n];

            for (int i = 0; i < n; i++) {
                int count = 0;

                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (items[j][0] % items[i][0] == 0) {
                        count++;
                    }
                }

                copies[i] = count;
            }

            int[] prev = new int[budget + 1];
            int[] curr = new int[budget + 1];

            for (int i = n - 1; i >= 0; i--) {

                for (int b = 0; b <= budget; b++) {

                    // int skip = dp[i + 1][b];
                    int best = prev[b]; // skip

                    int price = items[i][1];

                    for (int x = 1; x * price <= b; x++) {
                        int cost = x * price;
                        int freeCopies = Math.min(x, copies[i]);

                        // take = Math.max(take, totalCopies + dp[i + 1][b - cost]);
                        int take = x + freeCopies + prev[b - cost];

                        best = Math.max(best, take);
                    }

                    // dp[i][b] = Math.max(take, skip);
                    curr[b] = best;
                }

                int[] temp = prev;
                prev = curr;
                curr = temp;
            }

            // return dp[0][budget];
            return prev[budget];
        }
        }

*/

/*
        1. First, understand the choices
    For every item type i, we have two possibilities:
    - Skip this item type entirely.
    - Purchase x copies, where x >= 1 and the total cost stays within budget.
    Suppose item A has:
    - Price = 3
    - Three eligible free items: B, C, D.
    Copies purchased	Purchased copies	Free copies	Total
    0	0	0	0
    1	1	1	2
    2	2	2	4
    3	3	3	6
    4	4	3	7
    5	5	3	8

    Notice that after purchasing three copies, additional purchases give no more free items.

    So the contribution from purchasing x copies of item i is:
    \[
    x+\min(x,c_i)
    \]

    where \(c_i\) is the number of eligible item types.
*/

/*
    General checklist to convert 2D DP into 1D DP
    Whenever you have a working 2D bottom-up solution, follow these steps:
    1. Identify the state dimensions.
    
    Example: dp[i][j] where i is index and j is capacity.
    2. Inspect the recurrence.
    
    Check whether the current state depends on:
    - Previous row only?
    - Current row?
    - Previous column?
    - Multiple earlier rows?
    3. Determine the minimum storage needed.
    - Previous row only → two arrays.
    - Previous row with safe in-place updates → one array.
    - Multiple previous rows → keep the required number of rows.
    4. Replace the 2D references.
    
    For two arrays:
    - dp[i][j] → curr[j]
    - dp[i-1][j] → prev[j]
    5. Determine iteration direction.
    
    If using one array, check whether forward or backward iteration preserves the old values required by the recurrence.
    6. Check initialization and final answer.
    
    Make sure your initial row and return statement still represent the same states.
*/