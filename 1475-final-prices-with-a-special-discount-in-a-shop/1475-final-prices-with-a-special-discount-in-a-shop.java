class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] st = new int[n];
        int top = -1;

        for(int i = 0; i < n; i++) {
            while(top >= 0 && prices[st[top]] >= prices[i]) {
                prices[st[top--]] -= prices[i];
            }

            st[++top] = i;
        }

        return prices;
    }
}

/*
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];

        for(int i = 0; i < n; i++) {
            int discount = 0;

            for(int j = i + 1; j < n; j++) {
                if(prices[j] <= prices[i]) {
                    discount = prices[j];
                    break;
                }
            }

            ans[i] = prices[i] - discount;
        }

        return ans;
    }
*/

// Stop searching as soon as you find the first qualifying element. Don't necessarily stop at i + 1; search from i + 1 up to n - 1.