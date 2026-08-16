class Solution {
    public boolean stoneGameIX(int[] stones) {
        int count0 = 0, count1 = 0, count2 = 0;

        for(int stone : stones) {
            int type = stone % 3;

            if(type == 0) {
                count0++;
            } else if(type == 1) {
                count1++;
            } else {
                count2++;
            }
        }

        if(count0 % 2 == 0) {
            return count1 > 0 && count2 > 0;
        }

        return Math.abs(count1 - count2) > 2;
    }
}

/*
    The intuition to remember

Don't memorize the formulas as magic.

Think:

0 → doesn't change the sum
1 ↔ 2 → determine the game

And:

Even number of 0s

Both remainder types need to exist:

b > 0 && c > 0
Odd number of 0s

The balance between 1s and 2s matters:

|b - c| > 2

So the final formula comes from analyzing who is forced to make the first bad move, not from a normal greedy choice.
*/