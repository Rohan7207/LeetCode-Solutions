class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;

        int prev1 = 1;
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2; 
            prev2 = prev1; 
            prev1 = current; 
        }

        return prev1;
    }
}

/*
    Initialize two variables to represent no.of  ways to reach step 0 and step 1
    //Iterate from step 2 to n
    //no.of ways to reach current step
    //update prev2 to previous step
    //Update prev1 to current step

    // answer is no.of ways to reach nth step
*/