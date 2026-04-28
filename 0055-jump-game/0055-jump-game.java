class Solution {
    public boolean canJump(int[] nums) {
        int finall = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= finall) {
                finall = i;
            }
        }

        return finall == 0 ? true : false;
    }
}


//By using dp we end with O(n^2)
//Use greedy algorithm = O(n) and O(1)
/*
In this we use final which is set to last element
and while traversing from back if sum of its value and
index is >= to final index then set that particular index as final and
reverse backward for ex consider  2 3 1 0 2 5 with their index values
1.final = 5 and sum of 4+2>=5 where 4 is index and 2 is 4th index so final=4
2.final=4 and sum of 0+3<4 so no update needed
3.final=4 and sum of 2+1<4 ...
4.final=4 and sum of 1+3>=4 so final=1
5.final=1 and sum of 0+2>=1 so final=0
    if final=0 return true 
*/
