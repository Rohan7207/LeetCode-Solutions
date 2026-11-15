class Solution {

    private int MOD = 1000000007;

    public int numOfSubarrays(int[] arr) {
        int n = arr.length;

        int[] prefixSum = new int[n + 1];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int count = 0;
        int evenCount = 1, oddCount = 0;

        for (int i = 0; i < n; i++) {
            if (prefixSum[i] % 2 == 0) {
                count = (count + oddCount) % MOD;
                evenCount++;
            } else {
                count = (count + evenCount) % MOD;
                oddCount++;
            }
        }

        return count;
    }
}

/*
    Worst Brute force:
        public int numOfSubarrays(int[] arr) {
            int n = arr.length;
            int count = 0;

            for(int i = 0; i < n; i++) {
                for(int j = i; j < n; j++) {
                    int sum = 0;

                    for(int k = i; k <= j; k++) {
                        sum += arr[k];
                    }

                    if(sum % 2 == 1) {
                        count++;
                    }
                }
            }

            return count;  // O(n ^ 3)
        }

    Brute force: Would br try every possible subarray
        public int numOfSubarrays(int[] arr) {
            int n = arr.length;
            int count = 0;

            for(int i = 0; i < n; i++) {
                int sum = 0;
                for(int j = i; j < n; j++) {
                    sum += arr[j];

                    if(sum % 2 == 1) {
                        count++;
                    }
                }
            }

            return count;  // O(n ^ 2)
        }

    Optimal Approach:
      * Same problems as this:
            - Count of subarray having sum = 0
            - Count of subarray having sum = k or sum % k

        * In this type problem, we would use prefix sum concept,
            Like take first problem of finding sum = 0
            Ex: {a, b, c, d, e, f}   
                - Consider a subarray {c, d, e} sum = 0, now we need find this subarray the optimal way would be to use prefix sum (not sliding window bcz it needs shrinking logic)
                - We have sum of {a, b, c, d, e} = x and {a, b} = y. 
                    a + b + c + d + e = x where c + d + e = 0, so a + b = x
                    a + b = y

                    => x == y
                 ** This means for ex if sum of a + b = 10 then it confirm that c + d + e is 0 so we get sum of a..e as 10. So we just check whether there is same cumulative sum seen before, then check where it seen before. then c, d, e sum would be 0.

        Ex: arr = {2, 2, 5, 6, 2} ans = 9
            prefix = {2, 4, 9, 15, 17}

            Case 1: even + odd = odd
            - In previous there was relation like if same sum was found then definetly sum = 0, in this problem, we need use this formula even + odd = odd

                prefix = {0, 2, 4, 9, 15, 17}
                          e, e  e  o   o   o  

             ** at idx = 3 => it is odd means for sum that for some even no. odd no, has been added which is 5, means the 5 idx no. can make a sum of odd before all its even sum no.s {2, 5},
             {2, 2, 5} bcz 5 is odd and it can make sum odd by adding with even. So, as soon we seen odd sum in prefix we count no.of even sum before it which is 2, so ans = 2, we keep track of evenCount and add ans = evenCount,
                - But we need to also add {5} to our since it is also valid odd sum subarray to do that, 
                    first when we got odd sum in prefix we tried to pair it with even sum and add it to res, now to add 5 to our res, wkt prefix starts from 0 so we keep this also in evenCount track so at oddsum = 5 there are 3 even sum = {0, 2, 2} so we add **res = 3 (evenCount) means we are not pairing 5 with anyone to get desired output.

            ** at idx = 4, sum 15, we would add evenCount to res,
                so res = 3 + 3 => 6
             - Those are, {2, 5, 6}, {2, 2, 5, 6}, {5, 6} (0 case means we take two odd)

            ** at idx = 5, sum = 17, we would do same, res = 6 + 3 = 9.
                - Those are, {2, 5, 6, 2}, {2, 2, 5, 6, 2}, {5, 6, 2}


           Case 2 : odd + even = odd
           Ex: {2, 1, 3}
             prefix = {0, 2, 3, 6}
                       e, e, o, e 

            at sum = 6, as soon as we see even sum we need to keep count of odd sum to add them to res as previous case. So we need to keep track of evenCount and oddCount.
                evenCount = 1  (bcz prefix starting is 0)
                oddCount = 0
            
            idx    sum      evenCount    oddCount    res
            1       2         1+1 = 2       0          0 (bcz no oddCount)
            2       3          2            1          2 (there is 2 evenCount= {2, 1}, {1}) 
            3       6          3            1          3  (there is 1 oddCount= {3})


    T.C = O(n)          
    S.C = O(n) {prefixsum, but can do in O(1) by keeping cumulative sum} 
*/