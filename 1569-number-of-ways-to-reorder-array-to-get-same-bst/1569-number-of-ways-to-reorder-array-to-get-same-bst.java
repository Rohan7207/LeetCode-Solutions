class Solution {

    private int MOD = 1000000007;
    long[][] pascalTable;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        List<Integer> numsList = new ArrayList<>();
        pascalTable = new long[n + 1][n + 1];

        for (int num : nums) {
            numsList.add(num);
        }

        for (int i = 0; i <= n; i++) {
            pascalTable[i][0] = 1;
            pascalTable[i][i] = 1;

            for (int j = 1; j < i; j++) {
                pascalTable[i][j] = (pascalTable[i - 1][j] + pascalTable[i - 1][j - 1]) % MOD;
            }
        }

        long ans = (solve(numsList)) % MOD;

        return (int) ((ans - 1 + MOD) % MOD);
    }

    private long solve(List<Integer> nums) {
        int n = nums.size();

        if (n < 3) {
            return 1;
        }

        List<Integer> leftArray = new ArrayList<>();
        List<Integer> rightArray = new ArrayList<>();

        int root = nums.get(0);

        for (int i = 1; i < n; i++) {
            int val = nums.get(i);
            if (val < root) {
                leftArray.add(val);
            } else {
                rightArray.add(val);
            }
        }

        long x = solve(leftArray) % MOD;
        long y = solve(rightArray) % MOD;

        // ((n - 1) C (leftArray.size()))
        long z = pascalTable[n - 1][leftArray.size()];


        return (((x * y) % MOD) * z) % MOD;
    }
}

/*
    nums = {2, 1, 3}            2
                              /     \
                             1        3
    All possible permutations:
    2, 3, 1    1, 3, 2      1, 2, 3        3, 1, 2             3, 2, 1
       2         1             1              3                   3  
      / \          \            \            /                   /
     1   3          3            2          1                   2 
                   /              \          \                 / 
    Yes(equal)     2                3          2              1
                  No           No            No                 No
              (not equal to given nums)     

    So there is one way to reorder nums of given BST

    Ex: nums = {3, 4, 5, 1, 2}  Original:          3
                                                 /   \ 
                                                1     4
                                                 \      \
                                                  2       5
    All posible
    4, 5, 1, 3, 2   \  We can't choose both bcz root must be starting to be root node only,
    5, 4, 1, 2, 3   /

    3, 5, 4, 1, 2           3, 1, 2, 4, 5             3, 2, 1, 4, 5
         3                        3                         3
       /   \                     /  \                      /     
      1     5(invalid)          1    4                    2  (here it is invalid)  
      \     /                    \    \  
       2    4                     2    5 
      No                         Yes this is valid
    This wrong one
  when we put 5 bcz
  there it should 4
  so return 0    

  - Wkt root nodes left values will be less than its value and reorder bst to be equal to given bst the relative ordering of all left values must be same as given nums means in above the left order in nums was 1 -> 2, now the case which results true as also 1 -> 2 so it gives equal bst and also we should check same for the right values greater than root val.
  Other cases where it will be same is:
   3, 1, 4, 5, 2  where relative ordering is 1 -> 2 and 4 -> 5 

   nums = {3, 4, 5, 1, 2}   So one position of root is fixed
    There could more numbers down the subtree so we will make sure that each subtree matches itself with original, x subtree must be identical with leftsubtree of original and sam e for y

                 3
              /     \
           x...xi    y...yi

     ways =  x * y

            solve(nums) z ways
      x ways /      \ y ways
         left       right
    solve({1, 2})   solve({4, 5})  

    - Means we can reorder left part in x ways and right subtree in y ways, and there could more ways for curr nums i.e. is z ways,  then
    (x*y) + (x*y) + (x*y) + .... z
  => z * (x * y) - 1

  where x * y are different ways of left and right subtree of each possible way which get same bst as original that are z times so z*(x*y),  - 1 bcz while calling recursively it would build the original bst also so we reduce final ans by 1.

  - How to calculate z:
    {3, 4, 5, 1, 2} n = 5  where 3 is fixed so we need to fill 4 positions and suffle each other and also we should make sure of relative ordering
    * Root is fixed
        3, _, _, _, _
    * Now left values there are 2 values to be filled in array and we can do that using combination formula i.e. 4C2 = 6 and right values 4C2 = 6 and if there were more values of right values then it would be,
    => z = (n - 1)C(no.of left or right no.s) we can either find left or right

    =>   (n - 1)C(L1) * x * y


    pseudo code:

        solve(nums) {
            root = nums[0];

            for(int i = 1; i < nums.length; i++) {
                if(nums[i] < root) {
                    leftArray[i] = nums[i];
                } else {
                    rightArray[i] = nums[i];
                }
            }

            x = leftAns = solve(leftArray);
            y = rightAns = solve(rightArray);
            z = (n - 1) C (leftAns.length);

            return ((x * y) * z);
        }

    ans = solve(nums);
    return ans - 1;

    We need optimize nCr operation bcz if we go on calculating on each call then it would become to slow
        r = N!  / (N - r)! * r!

    To remove nCr optimally, using Pascal's Triangle:
            1
           1 1  
          1 2 1
         1 3 3 1
        1 4 6 4 1
    Convert above to 2D array
            
        0   1
        1   1  1
        2   1  2  1
        3   1  3  3  1    
        4   1  4  6  4  1
    
    ith row contains i + 1 elements in it.
    - for 2nd row first fill everything with 1, then lets start filling, which done like first and last col must be for middle elements it is up ele + left diagonal ele, i.e. for [2, 1] = [1, 1] + [1, 0]

    To remove 4C2 = [4, 2] = 6, we should directly check 2D array indice for 4th row and 2nd col
    and 3C2 = 3.
    - So we will calculate Pascal triangle and then calculate nCr easily

    Edge Case:
    n == 1 && n == 2, return 1;  and our function will return 1 - 1 = 0
*/