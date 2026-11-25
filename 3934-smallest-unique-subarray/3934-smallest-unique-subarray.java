class Solution {

    private int n;
    private static final long MOD = 1_000_000_007L;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        int max = -1;

        for(int num : nums) {
            max = Math.max(max, num);
        }

        int base = max + 1;
        int res = n;

        int left = 1, right = n - 1;

        while(left <= right) {  // O(n)
            int mid = left + (right - left) / 2;

            if(check(nums, mid, base)) {
                res = mid;
                right = mid - 1;  // Try minimum if possible
            } else {
                left = mid + 1;
            }
        }

        return res;  // O(nlogn)
    }

    private boolean check(int[] nums, int size, int base) {
        Map<Long, Integer> map = new HashMap<>();
        long hash = 0;

        long p = findPow(base, size - 1);  // O(log(size))

        int i = 0, j = 0;
        while(j < n) {  // O(n)
            hash = (hash * base + nums[j]) % MOD;

            // Still subarray is not of size length
            if(j - i + 1 < size) {
                j++;
            } else {
                map.put(hash, map.getOrDefault(hash, 0) + 1);

                // Removing left digit from hash and append nums[i] to hash
                hash = (hash - (nums[i] * p) % MOD + MOD) % MOD;

                i++;
                j++;
            }
        }

        // If count of any subarray is 1 in map then it is unique subarray of that size
        for(Integer count : map.values()) {
            if(count == 1) {
                return true;
            }
        }

        return false;
    }

    // Binary Exponentiation
    private long findPow(long a, long exp) {
        // if(base == 0) {
        //     return 1;
        // }

        // long half = findPow(a, base / 2);
        // long result = (half * half) % MOD;

        // if(base % 2 == 1) {
        //     result = (result * a) % MOD;
        // }

        // return result;

        long result = 1;

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * a) % MOD;
            }

            a = (a * a) % MOD;
            exp /= 2;
        }

        return result;
    }
}

/*
    nums = {3, 3, 3}

    - Brute force would be to check one-size subarray and find unique subarray, if duplicate subarray found increase size,
        * Size can be 1 to n
        * We store seen subarray of length size to check in future whether we seen this subarray or not for this we can use Map<List<Integer>, Boolean> to store subarray as key, and boolean to tell whether seen or not.
            - Like we do, enter 
                0  {3} -> T
                1  {3} -> F   already present so mark false
                2  {3} -> F

              Now if flag is false, then we have duplicate subarray if not we have min unique subarray
              Do this same for all sizes till n.

            
        * In this we can see that we are trying to fill and find ans for all size values, in which we have remove all subarrays, and we are inserting that subarray in map which is O(n), and to compare whether subarray is unique it aslo takes O(n), so this brute force would goupto > O(n ^ 2)
            for(size = 1; size <= n; size++) {}

    - Notice that we are evaluating for all possible sizes right and we checking it in sorted fashion like 1, 2, 3..., since we are doing this sorted we can use Binary Search on this size and find out efficiently, like 
            l                 r  
            1, 2, 3 .......   n

            mid = l + (r - l) / 2;

            - If we found unique subarray for size mid then we go in left to find min, if not found we will try for bigger size.

    Binary Search on Answer / size
     l = 1, r = n; res = 0;

     while(l <= r) {
        mid = l + (r - l) / 2;   // size
        if(check(nums, mid)) {  // check() says whether there is unique subarray in nums of length size
            res = mid;
            r = mid - 1;
        } else {
            l = mid + 1;
        }
     }


    ** How to check all subarrays of size mid???
        nums = {1, 1, 2, 2, 1} mid = 3

        * Now subarray is {1, 1, 2} to check in future whether we have seen this subarray or not we have to store this subarray in map only we can't do anything for this. and for next window we shift window and compare subarray with existing if not same we will add this subarray else we will mark it as F. So this also takes more time bcz we need to add and compare with all lists present in map which takes more space and time.
        * So we need anything such that we don't have to store subarray like {1, 2, 1} instead we need to represent it in different way and should be identified easily to compare.

    ****
    - Problems in whihc we need to check the unique subarray from all subarray or in string to find uniquenness substring from all substrings, Bcz we can't store all subarrays in map which can take more time and space. and TLE and MLE
    - To solve this in which to represent subarray/substring to small value which should be unique we need to use hash which also called Rolling Hash.

    Rolling Hash: nums {1, 1, 2, 2, 1} mid = 3
     * We need convert a subarray to value which should be unique, for ex
        {1, 1, 2} -> We need to represent as a number so we make it as 112 which becomes integer value in map instead of list of integers, through which we can easily add and compare bcz it is integer, see we had problem with storing list of integers and comparing each list, since now each subarray is converted into a Number integer we can easily add and compare, and we can't avoid storing of subarray number bcz we need to know in future whether we have seen it or not.

        index = 0-2: {1, 1, 2} = 112
            (0 * 10) + 1 = 1
            (1 * 10) + 1 = 11
            (11 * 10) + 2 = 112   // 11 is previous digit no.

        Store in map: 112 - T

        How to maintain sliding window in this no. subarray

       index = 1-3: We have 112 represents {1, 1, 2} next window is {1, 2, 2} now we need hash as 122 to do this,
            - Notice that we need find mid size subarray which 3, since subarray size is fixed the we can remove place value hash number which is 
                1 (100's) 1 (10's) 2 (1's)
              Now find that mid size digit value is 1(100's) form right to left, we will do 
                1 * 10 ^ 3 - 1 = 1 * 10 ^ 2  = 100
              - Substract above 1(mid size digit) * 10 ^ mid - 1 from actual or current window hash
                1 1 2
              - 1 0 0
                0 1 2

              - Now we can form 122 by using 12 form substraction and do (12 * 10) + 2(curr num) = 122 and check in map and update if found as F or add it
               map:
                    112 - T
                    122 - T

        index = 2-4:                

                1 * 10 ^ 3 - 1 = 100
                122 
              - 100
                022

                - (22 * 10) + 1 = 221

            map:
                112 - T
                122 - T
                221 - T
            * We can also keep count instead of boolean, now if we find any subarray of count as 1 then it is valid unique subarray and try in left using binary search.

        * We have problem in this above appraoch
            - Assume that we have subarray {12, 3} = 
               h = (12 * 10) + 3  hash is 123
            - We have another subarray in future {1, 2, 3} = 
                h = (1 * 10) + 2 = 12, (12 * 10) + 3 = 123 hash is 123

            * This is not correct bcz {12, 3} is not same as {1, 2, 3}, it behaves like this bcz of multiplier 10, our above method was valid until numbers < 10 but when it is > 10 we face this issue.
        **** * Till now we were doing hash of 10 (hash)10 which was valid upto < 10 so this says that we can't take 10 as base for every value, to avoid this we must take base value such that it should create a problem for any subarray to do this we can take max element and base = max + 1
            Ex : {4, 1, 3, 2}  => max = 4 base = 4 + 1 = 5 so we will do (hash)5 means we will multiply by 5 wile converting and this results in unique hash values over all subarrays

        For above wrong method ex:
            {12, 3, 0} = max = 12 base = 13 = (0 * 13) + 12 = 12, (12 * 13) + 3 = 159

            {1, 2, 3} // we need to remove it by same base bcz we will find max form whole array to get unqiue hash values
                (0 * 13) + 1 = 1, (1 * 13) + 2 = 15, (15 * 13) + 3 = 198


        *  But since we have seen that we need take base as max + 1, the above approach of moving window form existing hash value be changed
            previously = 112 =>  1 * 10 ^ size - 1 = 100 => 112 - 100 => 12, (12 * 10) + 2 = 122
            Now, 112 => 1 * base ^ size - 1 => rest is same

            hash = hash - (nums[i] * base ^ size - 1); // Here size base ^ size - 1 constant we remove it once and use it.


    Points:
        1. Base = max(nums array) + 1
        2. (hash)10
        3. Calculate once base = max + 1
        4. hash = hash - (nums[i] * base ^ size - 1);  base ^ size - 1 = do it by Binary Expotentian which gives it effieiently

        base = max(nums) + 1;
        boolean check(nums, size, base) {
            Map<Integer (hash), Integer (count)> map;
            hash = 0;

            p = binyExp(base, size - 1);

            int i = 0, j = 0;
            while(j < n) {
                hash = (hash * base + nums[i]) % MOD;  // form digit

                if(j - i + 1 < size) {
                    j++;
                } else {
                    map.put(hash, count + 1);

                    // Remove left from hash
                    hash = (hash - (nums[i] * p % MOD) + MOD) % MOD; // handle modulo
                    i++;
                    j++;
                }
            }

            // Check if we habe any hash in map having frequency == 1 return true else false.
        }

    key Technique:
        1. Binary Search on Answer
        2. Sliding Window
        3. Rolling Hash
*/