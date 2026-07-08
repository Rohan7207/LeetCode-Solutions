class Solution {
    private int MOD = 1000000007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int len = s.length();

        // Precomputed arrays
        long[] numPrefix = new long[len + 1];
        long[] sumPrefix = new long[len + 1];
        int[] nonZeroCount = new int[len + 1];

        // Precompute powers of 10 modulo MOD: powerOf10[k] = (10^k) % MOD
        long[] powerOf10 = new long[len + 1];
        powerOf10[0] = 1;
        for (int i = 1; i <= len; i++) {
            powerOf10[i] = (powerOf10[i - 1] * 10) % MOD;
        }

        // Step 1: Preprocess the string
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                int digit = c - '0';
                numPrefix[i + 1] = ((numPrefix[i] * 10) + digit) % MOD;
                sumPrefix[i + 1] = sumPrefix[i] + digit;
                nonZeroCount[i + 1] = nonZeroCount[i] + 1;
            } else {
                numPrefix[i + 1] = numPrefix[i];
                sumPrefix[i + 1] = sumPrefix[i];
                nonZeroCount[i + 1] = nonZeroCount[i];
            }
        }

        int qLen = queries.length;
        int[] ans = new int[qLen];

        // Step 2: Answer each query 
        for (int i = 0; i < qLen; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // Get total non-zero digits in this range
            int digitInRange = nonZeroCount[r + 1] - nonZeroCount[l];

            if (digitInRange == 0) {
                ans[i] = 0;
                continue;
            }

            // calculation of the digit sum in range
            long currentSum = sumPrefix[r + 1] - sumPrefix[l];

            // calculation of the zero-free number in range
            long currentNum = (numPrefix[r + 1] - (numPrefix[l] * powerOf10[digitInRange]) % MOD + MOD) % MOD;

            ans[i] = (int) ((currentNum * currentSum) % MOD);
        }
        
        return ans;
    }
}

/*
     String subStr = s.substring(l, r);
        if(subStr.isEmpty()) {
            return 0;
        }

        long num = Long.parseLong(subStr);
        int res = 0;
        int sum = 0;
        int placeValue = 1;

        while(num > 0) {
            int digit = (int) (num % 10);

            if(digit != 0) {
                sum += digit;

                res += (digit * placeValue);
                placeValue *= 10;
            }

            num /= 10;
        }

     int n = queries.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            ans[i] = build(s, l, r);
        }

        return ans;
    }

    private int build(String s, int l, int r) {
        if (l > r || l < 0 || r >= s.length()) {
            return 0;
        }

        long res = 0;
        long sum = 0;

        // Process characters directly from left to right
        for (int i = l; i <= r; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                int digit = c - '0';
                
                sum += digit;
                
                // Build the number rolling left-to-right safely with modulo
                res = ((res * 10) + digit) % MOD;
            }
        }

        // If the entire interval was filled with '0's
        if (sum == 0) return 0;

        return (int) ((res * sum) % MOD);
    }
*/