class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;

        for(int num : nums) {
            min = Math.min(min, num);
        }

        int moves = 0;
        for(int i = 0; i < n; i++) {
            moves += nums[i] - min;
        }

        return moves;
    }
}

/*
    The brute force would be try exactly has mentioned in problem, like try to make max element by taking n - 1 elements and in each step greedily skip the max element once

    Ex: {1, 2, 3} max = 3
        {2, 3, 3} max = 3
        {3, 3, 4} max = 4
        {4, 4, 4} max = 4

        Brute Force: 
        int oper = 0
        while(!allElementsEqual(nums)) {  // O(n)
            1. Find max element idx.       // O(n)
            2. skip max and increment n - 1
            3. oper++
        }

        This will give O(n ^ 2), so it would give TLE bcz 10 ^ 5 is given

    Optimizing the Brute force:
        {1, 2, 3} ->  {2, 3, 3}  -> {3, 3, 4}  -> {4, 4, 4}
        diff=3-1=2    diff=3-2=1

     - We only need count of moves to make array equal so it doesn't that whether we increase element or decrease the element

     ** Instead on increment n - 1 elements in each move which needs to focus on n - 1 elements we can equivalently do decrement of max elements and go on decreasing the gap of max and min to 0

     {1, 2, 3}  ->  {1, 2, 2}  ->  {1, 1, 2}  ->  {1, 1, 1}
     gap = 2        gap = 1         gap = 1         gap = 0

     - Notice that max is changed to min in last step.

     {1, 2, 3}    =>   2 - 1  = 1 move
                  =>   3 - 1  = 2 moves

      {1, 2, 3}  -> {1, 2, 2}  -> {1, 2, 1} = 2 moves and => {1, 2, 1} -> {1, 1, 1} = 1 move total = 3

    - So the approach is find, min in array
    - Iterate, and find no.of operations to convert current element to min element by nums[i] - min and add it in count  which we can solve in O(n) time
*/
