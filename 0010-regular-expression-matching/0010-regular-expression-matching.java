class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        int mr = dp.length - 1;
        int mc = dp[0].length - 1;

        for(int i = 0; i <= mr; i++) {
            for(int j = 0; j <= mc; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if(i == 0) {
                    char chp = p.charAt(j - 1);

                    if(chp == '*') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = false;
                    }
                } else if(j == 0) {
                    dp[i][j] = false;
                } else {
                    char chp = p.charAt(j - 1);
                    char chs = s.charAt(i - 1);

                    if(chp == '*') {
                        dp[i][j] = dp[i][j - 2];

                        char chprev = p.charAt(j - 2);
                        if(chprev == '.' || chprev == chs) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    } else if(chp == chs || chp == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[mr][mc];
    }
}

/*When there is . it matches with every character
        and for * there are 3 cases consider s=faa and p=fa*
        1. for checking only f patent becomes f(a* = 0) check then value before
          preceeding it if true return true like for (1,3) check (1,1)
        2.for checking fa patent becomes fa(a*= a)  then if value preceeding is
          true return true like for (2,3) check (2,2)
        3.for checking faa(or faaaaaaaaa) patent becomes faa(a*= aa) then if
          value above it is true return true like for (3,3) check (2,3) */

        //O(m*n) and O(m*n) where m and n are length of each strings


/*
   s = aab p = c*a*b

   dp is 
        0     1    2     3    4    5
pattern idx=  0    1     2    3    4   (while accesiing char use j-1)
        -     c    *     a    *    b
    -   T     F    T     F    T    F     

    a   F     F

    a   F

    b   F


    - The blank patten always matches eith blank string and does match with any other character so it is false
    - The first row, the in case of charcter it is false and if character is * then check the rowth previous 2 value bcz c* is trying to become '' nothing so check dp[0][0]
        - for pattern 1 c* becomes ''
        - for pattern 3 c*a* is trying to become c* so check 1 which is true

    - For middle block for aa if we try to match c*a and also same for
        aa to match with c*. . matches with a so check value of c*
        - In this if last a matches with a then we should check diagonal upward i.e. for (2, 3) check (1, 2) 
    
    - When we try to match a with c*, here if c* becomes ' ' then it is blank so check value of its previous 2 row i.e. dp[i][j - 2]
        and if c* try to become cc*, if c 2nd c whould matches with a then we would check the above value i.e. dp[i - 1][j]
        - Consider s=xz p xyz*
            if z* = '' then try to match xz with xy
            if z* = zz* then try to match xz with xyzz* where 4th z matches and cancels with z of s then we get 
            s = x and p = xyz*, we can see that pattern is same but one character will be cancelled so check for above value
 */