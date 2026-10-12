class Solution {

    private int MOD = 1000000007;

    public int maxSum(List<Integer> nums, int k) {
        int[] count = new int[32];
        int res = 0;

        for(int num : nums) {
            for(int bit = 0; bit < 32; bit++) {
                if((num & (1 << bit)) != 0) {      // If 1 is present at this bit position then increase
                    count[bit]++;
                }
            }
        }

        while(k > 0) {
            int curr = 0;

            for(int bit = 0; bit < 32; bit++) {
                if(count[bit] > 0) {
                    count[bit]--;

                    curr = (curr | (1 << bit));   // Assigns 1 bit value to curr position and left shift
                }
            }

            res = (int) ((res + (long) curr * curr) % MOD);
            k--;
        }

        return res;
    }
}

/*
    nums = {2, 6, 5, 8}   k = 2     sum = 21
           2&8      2|8

        => {0, 6, 5, 10}        sum = 21
                5&10 5|10 

        => {0, 6, 0, 15}        sum = 21

    Here we one thing we can notice is that when we chose 2 and 8 then answer was 0 and 10 where 2 is transfering its bits to 8 means 8 + 2 = 10 and 2 becomes 0, and same is when happening when  5 and 10 were chosen which gave 0 and 15


    nums[i] AND nums[j]
    nums[i] OR nums[j]

    2 -> 0 0 1 0            2 -> 0 0 1 0 
And                     Or
    8 -> 1 0 0 0            8 -> 1 0 0 0 
         0 0 0 0                 1 0 1 0

    Before = {2, 8}     2      +    8    = 10
                       (1*2^1)     (1*2^3) 

    After = {0, 10}     0   +   10      = 10
                              (1*2^1 + 1*2^3)  

    Only bit is transfering from 2 to 8 so we get 10, We are showing that sum are becoming constants

        nums = {2, 6, 5, 8}     k = 2
        3 2 1 0th          1st Oper         2nd Oper
    2   0 0 1 0         0   0 0 0 0       0   0 0 0 0   
    6   0 1 1 0         6   0 1 1 0       6   0 1 1 0   
    5   0 1 0 1         5   0 1 0 1       0   0 0 0 0  
    8   1 0 0 0         10  1 0 1 0       15  1 1 1 1
count-> 1 2 2 1             1 2 2 1           1 2 2 1  

    - In 1st operation see properly that 2's 1st bit 1 is transfered at same position in 8's 1st bit which led to 10. The transfer is happening in its corresponding number only
    - In second operation if you see the same position 1's are transfered to same position of operation applied like 10.
        3 2 1 0         3 2 1 0     If we see same position bit are transfered from 5 to 10 which ledto 15
    5 ->0 1 0 1      0->0 0 0 0  
    10->1 0 1 0     15->1 1 1 1
        Old          New 

    - And we can also obeseve that no matter how many times we apply operation the no.of 1st bit in each bit position like 3 2 1 0 will never change
    - Now ultimately we want maximum no. in one place and since no.of 1's bit is constant then we can frely move all 1's to one no. which would lead to maximum number like 3rd operation passing all 1's bit from 15 for example
    So, we are greedily moving all 1's bit to maximize a no. and it is also reducing a no. whose bit are being transfered

    Proof for reducing no. would give better maximum ans
        nums = {x, y},   x < y  we are making x more smaller and y more larger and we should get sum large

        Without operations, sum = x^2 + y^2     - 1st eqn
        With operation,     sum = {x-d, y+d} => x + y
                            sum = (x^2 + d^2 - 2dx) + (y^2 + d^2 + 2dy)     - 2nd eqn

        eqn 2 - eqn 1, to check whether overall sum is greater or not
        {(x^2 + d^2 - 2dx) + (y^2 + d^2 + 2dy)} - (x^2 + y^2)
        {(x^2 + d^2 - 2dx) + (y^2 + d^2 + 2dy)} - x^2 - y^2

        2d^2 - 2dx + 2dy
    =>  2d(d + y - x)     

        - We had assumed y > x, so y - x is positive and d is also positive so, overall the sum increases 
        which proves our greedy approach so we get better answer only by making larger no. more larger  by doing operation and small to more smaller.

        Initial
            3 2 1 0                 3 2 1 0
        2   0 0 1 0               ? _ _ _ _
        6   0 1 1 0               ? _ _ _ _  
        5   0 1 0 1               ? _ _ _ _
       10   1 0 1 0               ? _ _ _ _
count=>     1 2 2 1                 1 2 2 1

    - Now we know that we should fill every bit with 1 and we should know that whether 0th position contains 1bit or not so keep count array to store total 1's bit at every position

        1st Operation:
            3 2 1 0                 3 2 1 0
        2   0 0 1 0               ? _ _ _ _
        6   0 1 1 0               ? _ _ _ _  
        5   0 1 0 1              15 1 1 1 1
       10   1 0 1 0               6 0 1 1 0
count=>     1 2 2 1                 1 2 2 1
            0 1 1 0  
            0 0 0 0

    - When  we get 1 complete no. reduce k, now we have got 15 so k = 1, and when we get 6 reduce k to 0
    and now we have got maximum k no.s which would definetly give maximum required ans.

    - We need 
    count = no.of 1st bit at each bit position of 32 size
    curr = 0 0 0 0 

    for(int num : nums) {    // O(n)
        for(bit = 0; bit < 32; bit++) {   // O(32)
            if(num & (1 << bit)) {      // If 1 is present at that position then increase
                count[bit]++;
            }
        }
    }

    while(k--) {        // O(k)
        curr = 0;

        for(int bit = 0; bit < 32; bit++) {     O(32)
            if(count[bit] > 0) {
                count[bit]--;
                curr = (curr | (1 << bit));    // Assigns 1 bit value to last position and doesleft shift
            }
        }

        res += (curr * curr) % MOD;
    }

    return res;     O(k) and O(32)
*/