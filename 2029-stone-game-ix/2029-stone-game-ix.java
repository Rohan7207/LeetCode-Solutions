class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        for(int stone : stones) {
            count[stone % 3]++;
        }

        if(count[0] % 2 == 0) {
            return count[1] > 0 && count[2] > 0;
        }

        return Math.abs(count[1] - count[2]) > 2;
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