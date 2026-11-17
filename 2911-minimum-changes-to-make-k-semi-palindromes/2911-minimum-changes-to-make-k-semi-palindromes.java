class Solution {

    private int n;
    private String str;
    private int[][] costMemo;
    private int[][] memo;

    public int minimumChanges(String s, int k) {
        n = s.length();
        str = s;

        costMemo = new int[n + 1][n + 1];
        for (int[] row : costMemo) {
            Arrays.fill(row, -1);
        }

        memo = new int[n + 1][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return solve(0, k);
    }

    private int solve(int start, int k) {
        if (k == 0) {
            if (start == n) {
                return 0;
            }

            return Integer.MAX_VALUE;
        }

        // Early return
        if (start + 2 * k > n) {
            return Integer.MAX_VALUE;
        }

        if (memo[start][k] != -1) {
            return memo[start][k];
        }

        int res = Integer.MAX_VALUE;
        for (int end = start + 1; end < n; end++) {
            // start...end -> 1 substring
            int remainingCost = solve(end + 1, k - 1);

            if (remainingCost == Integer.MAX_VALUE) {
                continue; // Invalid partition
            }

            res = Math.min(res, getCost(start, end) + remainingCost);
        }

        return memo[start][k] = res;
    }

    private int getCost(int i, int j) {
        int len = j - i + 1;

        if (costMemo[i][j] != -1) {
            return costMemo[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for (int d = 1; d < len; d++) {
            // Not a divisor
            if (len % d != 0) {
                continue;
            }

            int cost = 0;
            for (int g = 0; g < d; g++) {
                int l = i + g;
                int r = l + (len / d - 1) * d;

                while (l < r) {
                    if (str.charAt(l) != str.charAt(r)) {
                        cost++;
                    }

                    l += d;
                    r -= d;
                }
            }

            minCost = Math.min(minCost, cost);
        }

        return costMemo[i][j] = minCost;
    }
}

/*
    In this problem, we have divide s into k substrings such that letter changes needed to make each substring semi-palindrome must be minimized. And return min no.of letters changes required.

     s = "awbcacd"
     here if we choose aw, bca, and cd as 3 substrings then to make each semi-palindrome we need 1 oper for aw = aa, 1 for bca = bcb, 1 for cd = cc. Total 3 operations.

    - A semi-palindrome is a special type of string that can be divided into palindromes based on a repeating pattern.
    - To check if string is a semi-palindrome:
        * Choose a positive divisor d of the string's length. d can range from 1 up to, but not including, the string's length, means if length is l then we have to choose d such that l % d == 0 and d = 1 to l - 1.
        *  For a string of length 1, it does not have a valid divisor as per this definition, since the only divisor is its length, which is not allowed. Yes it is true since d = 1 to n - 1.
        * For a given divisor d, divide the string into groups where each group contains characters from the string that follow a repeating pattern of length d. Specifically, the first group consists of characters at positions 1, 1 + d, 1 + 2d, and so on; the second group includes characters at positions 2, 2 + d, 2 + 2d, etc.
            Ex: Consider the string "abcabc":

                The length of "abcabc" is 6. Valid divisors are 1, 2, and 3.
                For d = 1: The entire string "abcabc" forms one group. Not a palindrome.
                    If group starts at i = 0, 
                        a     b         c           a      b       c  => abcabc not a palindrome
                       i=0  i+d=0+1  i+2d=0+2(1)   i+3d   i+4d    i+5d  
                For d = 2:
                Group 1 (positions 1, 3, 5): "acb"
                Group 2 (positions 2, 4, 6): "bac"

                    Group = 1
                        a      b       c         a      b          c  =>  acb (not a palindrome)
                       i=0    skip    i+d=0+2   skip  i+2d=0+4     skip     operations=1

                    Group = 2
                        a      b       c       a      b          c   =>  bac  (not a palindrome) oper=1
                       skip   i=1    skip   i+d=1+2  skip     i+2d=1+4=5

                       Overall = 2
                    
                Neither group forms a palindrome.

                Note: Notice there are d no.of groups in s

                For d = 3:
                Group 1 (positions 1, 4): "aa"
                Group 2 (positions 2, 5): "bb"
                Group 3 (positions 3, 6): "cc"
                All groups form palindromes. Therefore, "abcabc" is a semi-palindrome.
                
        * The string is considered a semi-palindrome if each of these groups forms a palindrome.

    Ex: s = "abcac", k = 2
      * Here we have to split s into 2 substrings like maybe abc, ac or ab, cac or abca, c. So we need to try every possible splits and check whether can form semi palindorme and keep the min one.

      * As constraints are small so we can try every possibilities.

    **** Goal - We need to split the given s every possible k substrings and find out no.of operations to make each substring semi-palindrome and add each oper which gives total cost to make entire k substrings semipalindrome and we need keep the min of this cost to get required ans.


    - We have to explore all possibilites of dividing(partitioning) s into k pieces, we can easily use recursion to achieve it. And we need to keep subsrtings greater than 1 length.

        Recursion = solve(start = 0, k);

        solve(int start, int k) {
            minCost = max;

            for(end = start + 1(bcz length must be 2 as min); end < n; end++) {
                substring = [start..end]
                solve(s, end + 1, k - 1);

                // We need to know
                cost[start..end] + remainingCost (from recusion call above)
            }
        }

    * We can add early optimizing base case like if a string s cannot be grouped into k groups though we have try to make using 2 length substring then we can return early indicating not possible.
        if(i + 2 * k > n) {  // not possible to divide s into k substrings
            return max;
        }

    Recursion Pseudo code:
        solve(0, k);

        solve(int start, int k) {
            // Base case: We need to check start that it should be be at means if s = abcdefgh  and k = 3 and if selected substrings are ab, cd, deg, but this is not valid grouping bcz we need group in such way that full string s must be covered in groups.
            if(k == 0) {
                if(start == n) {
                    return 0;
                } else {
                    return max;  // invalid, taken or made wrong groups
                }
            }

            if(i + 2 * k > n) {  // not possible to divide s into k substrings
                return max; // early return 
            }

            minCost = max;

            for(end = start + 1; end < n; end++) {
                int remainingCost = solve(end + 1, k - 1);
                // substring = [start...end]  // 1 group/piece

                minCost = min(minCost, getCost(start, end) + remainingCost); // where getCost(start, end) tells cost to make a substring start..end semi-palindrome
            }

            return minCost;
        }

    * Now we need to write getCost logic which defined in problem:
        getCost(start, end)

      - Total length, l = end - start + 1;
      - We need to choose d which are divisible by l such that l % d == 0.
      - Make group according d as start, start + d, start + 2d ........
      - If d = 2 then there must be 2 groups and if d = 3 then 3 groups
      - Now we need to check oper to make each group a semi-palindrome.
      - We need not to make seperate group strings everytime we can just use s.

      Ex: Lets consider one substring
            substr = "a b c a b d"  n = 6, d = 2 means two groups
                      st        end

            2 Groups:
                     start+0 is first ch of G0, then increment d to get next chs
                G0 = "acb"   

                    start+1 is first ch of G1
                G1 = "bad"
        * leftch = 0   and rightch = 4 and to traverse increment d so we didn't need to make this group string as we can directly check using two pointers by keeping left at start and right at end and check each charcaters whether they make palindrome and if not keep count.  
            For G0 = a b c a b d         
                     l         r
                    
                Check: s[l] == s[r], No, means changeRequired so count++ = 1 then increment and decrement left and right by d
                    
                    a b c a b d
                        l,r
                    
                    check: s[l] == s[r], Yes, update which hits base case left <= right

            ** How to remove right:
                a   b   c   a   b   d       n = 6   d = 2
                st                  end

                letters per group = l/d = 6/2 = 3 (G0 = acb)
                G0: 
                    start + 0 => first ch of group0

                    Remaining ch = 3 - 1 = 2 (need 2 chars to get last character which can get thought jump)

                    Every jump moves by d = 2 positions
                            first   Need this to reach end
                        (start + G0) + (l / d - 1)   * d  (To get each ch of group need to do d pos jump)
                                left
                    right = (start + G0) + (l / d - 1) * d
                    

            * Same do for G1.
            * For d, there could be G0, G1....G(d - 1), find oper chnage required for every group anc sum as cost and do this for every possible d and keep min and return it.
            * To find start and end to give to check palindrom or left and right 
                    left = start + group_no
                    right = left + (len / d - 1) * d


        int getCost(int i, int j) { // We could memo this bcz this method is called for every possible i and j so we could memoize and return cost when we have already computed.
            int len = j - i + 1;
            int minCost = max;  // Stores best cost from each d

            for(int d = 1; d < len; d++) {
                if(len % d != 0) continue;

                // Make groups for d
                int cost = 0;
                for(int group = 0; group < d; group++) {
                    int l = i + group;
                    int r = l + (len / d - 1) * d;

                    while(l < r) {
                        if(s[l] != s[r]) {
                            cost++;
                        }

                        l += d;  
                        r -= d;
                    }
                }

                minCost = min(minCost, cost);
            }

            return minCost;
        }

*/