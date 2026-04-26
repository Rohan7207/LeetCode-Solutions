class Solution {
    public int jump(int[] nums) {

        int jump = 0, currMax = 0, currEnd = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            currMax = Math.max(currMax, i + nums[i]);

            if (i == currEnd) {
                jump++;
                currEnd = currMax;
            }
        }

        return jump;
    }
}

/*
    We use greedy algorithm
    We use four pointers index,jumps,current max and current end
    If next index is greater then current max then update currnt max as next 
    index ,if index value and current index which is not end is same so update
    jumps to jumps++ and update current end as value of current max
    for ex consider 1 3 1 3 1 1 1 2 with their index
    1.in=0,j=0,cm=0,ce=0 -> bcz 0th index as 1 which as potential to reach 1st
    index where 1>0 so cm=1 and bcz in and ce are same j=1 and ce=cm=1(only if 
    ce==in)
    =>in=1,j=1,cm=1,ce=1
    2.in=1,j=1,cm=1,ce=1-> 4>1 cm=4 ce=in j=2 ce=cm=4 =>in=2,j=2,cm=4,ce=4
    3.in=2,j=2,cm=4,ce=4-> 3<4 so cm=4 ce!=in j=2 ce=4 =>in=3,j=2,cm=4,ce=4
    4.in=3,j=2,cm=4,ce=4-> 6>4 cm=6 ce!=in j=2 ce=4 =>in=4,j=2,cm=6,ce=4
    5.in=4,j=2,cm=6,ce=4-> 5<6 so cm=6 ce=in j=3 ce=6 =>in=5,j=3,cm=6,ce=6
    6.in=5,j=3,cm=6,ce=6-> 6<6 so cm=6 ce!=in j=3 ce=6 =>in=6,j=3,cm=6,ce=6
    7.in=6,j=3,cm-6,ce=6-> 7>6 cm=7 ce=in j=4 ce=7 =>in=7,j=4,cm=7,ce=7
    Since ce=7 which last element so we return the jumps value
*/