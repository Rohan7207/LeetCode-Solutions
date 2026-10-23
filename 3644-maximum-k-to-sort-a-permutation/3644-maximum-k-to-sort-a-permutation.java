class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = -1;   // We have take k whose all values are set "111111", -1 contains such bits

        for(int i = 0; i < n; i++) {
            if(nums[i] != i) {
                k = (k & nums[i]);
            }
        }

        return k == -1 ? 0 : k;
    }
}

/*
    Given that nums is permutation of numbers in range 0..n
    - We can swap elements only if nums[i] & nums[j] == k, where k is non-negative integer,
    - Now we need find max value of k such that array is in increasing order sorted.

    Ex: nums = {0, 3, 2, 1}

    - The array to be sorted since it is permutation of numbers from 0..n so nums[i] value should be eqaul to i value then only sorted if not those indexes need to be swapped 
    - Above we choose 3 and 1 present in 1 and 3 where 1 & 3 is 1 so k = 1 is required ans to sort.


    Brute Force:
        nums = {a, b, c, d}

        - We don't know what could be value of k but we have given that nums[i] & nums[j] == k, and & operator always answer like either ans would be equal to nums[i] or nums[j] or it will be always less than value of nums[i], nums[j]

        A & B = res would always will be either equal or less than a smaller no. btw two no.s
        For ex: 10 & 12 == 8

      - So the maximum value of k will always be n - 1 and it will btw nums values o..n - 1
      **  So we initialize k = n - 1 to be maximum, and then we check whether by keeping k = n - 1 the nums will be sorted or not.

      k = n - 1   nums = {a, b, c, d}
        We need to try out all pairs and the pair is valid if that pair bitwise & gives k. and reduce k when  pair groups will fail.

    Optimal Solution:
        a AND b = equal or less than min(a, b)

        nums = {0, 3, 2, 1}
    We can swap when nums[i] & nums[j] == k

    - For ex lets take value of k in binary as:

        k = _ _ _ _
        nums[i] = 0 1 0 1
        nums[j] = 1 1 1 0
                  0 1 0 0 

    - Now if nums[i] & nums[j] to be equal to k the 2nd set bit of ans should be present in k also right so k = _ 1 _ _
    - If above is true then vice-versa is also true, like if 2nd bit from last is set bit then 2nd bit in nums[i] and nums[j] should also be set bit right. If any number has not same bit as k then 
        nums[i] & nums[j] != k
  ** - So it gives a simple intution that nums[i] & nums[j] to be equal to k, the set bit present in k should also be present in both nums[i] and nums[j] then only they are equal.

  ** nums[i] != i   => Not in correct position, so this type no.s set bit must be equal to k set bits

    nums = {0, 3, 2, 1}
    k = 1 1 1 1

    1. nums[0] == 0, yes 
    2. nums[1] == 1, no, we need to swap 3 with other no., but we need to ensure that before swapping the
        set bits of 3 must be as same in pos as k,

        3 = 0 0 1 1
        k = 1 1 1 1
    ans k = 0 0 1 1
        - but first we has assumed k to me n - 1,now to make k as same as 3 we need to unset the set bit so k is same as 3 so we can swap, so we take & operation btw 3 and k and whatever is ans that is value of k. so now k = 3
    3. nums[2] == 2, yes
    4. nums[3] == 3, no

      1 =  0 0 0 1
      k =  0 0 1 1
  ans k =  0 0 0 1

    - So, now if we compare the final ans of k the set bits are present in both 1 and 3 at 0th pos so we can swap both no.s to get sorted order.

    k = 0 0 0 1     3 ->  0 0 1 1
                    1 ->  0 0 0 1
                  ans ->  0 0 0 1   => same as k so we can swap   

   ** - Imp the set bit can be destroyed means can become 0 but 0 cannot become 1.   

   *** So in end to get k as maximum value, we just have to take AND of all misplaced numbers.
*/