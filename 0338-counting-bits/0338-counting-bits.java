class Solution {
    public int[] countBits(int n) {
        //Approach 1
        //We do & with n and n-1 and return when n=0 and return count
        //Time with O(n) and O(n)

        //Approach 2 with O(n) and O(1)
        /*Ans for 9= A(n&n-1)+1
                    =A(9&8)+1
                    =A(8)+1
        */

        // ans = {0, 1, 1, 2, 1, 2}
        int[] ans = new int[n + 1];
        ans[0] = 0;

        for(int i = 1; i <= n; i++){
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }
}