class Solution {

    int MOD = 1000000007;
    int[] prev; // prev[n] = last time where nth character was seen [1-based indexing for convience]
    int[] dp = new int[2001];

    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] lastSeen = new int[26];
        prev = new int[n + 1];
        Arrays.fill(dp, -1);

        for(int i = 1; i <= n; i++) {
            int idx = s.charAt(i - 1) - 'a';

            prev[i] = lastSeen[idx];
            lastSeen[idx] = i;
        }

        return (solve(n) - 1 + MOD) % MOD;
    }

    private int solve(int n) {
        // Base Case
        if(n == 0) {
            return 1;  // Empty Subsequence
        }

        if(dp[n] != -1) {
            return dp[n];
        }

        int total = (2 * solve(n - 1)) % MOD;

        if(prev[n] != 0) {
            int duplicates = solve(prev[n] - 1);
            total = (total - duplicates + MOD) % MOD;
        }

        return dp[n] = total;
    }
}


/*
    Brute force:

        s = "ab"  curr = ""
            /   \ 
    curr= "a"  curr= ""
    /    \         /   \
c="ab"  c="a"  c="b"   c=""   so when i goes out of bounds we stop there and count subsequences leaving empty ones, so we need to count of subsequences, so we store these uniques sunsequneces in Set of String and return the length of it

    solve(i = 0, curr = "", s);

    solve(i, curr, s) {
        if(i == n) {
            if(curr.length() > 0) { // Not Empty String
                set.add(curr);
            }
        }

        solve(i + 1, curr + s[i], s);
        solve(i + 1, curr, s);
    }

    But this solution leads Memory Limited Exceeded bcz since we are storing both taking and not taking character it leads 2 ^ n possiblilities and n = 2000 is given so 2 ^ 2000 will lead Memory limit.

Leading to better solution
    How sequences are formed:
    s = "abc"

    consider we have empty sequence {""}, now when i = 'a' i have option to add a or not
    {""} -> {"", a} -> {"", "b", "a", "ab"} -> {"", "c", "b", "bc", "a", "ac", "ab", "abc"}

    In each step it is becoming twice so for n = 3 we can get 8 subsequences
    So in general if we know (n - 1) subsequences the we can get n  subsequences by multipying by 2

     => subseq(n - 1) * 2 where if n == 0 we return 1 i.e. base case

     return solve(n) - 1;  where n = length of s, - 1 bcz there would be one empty subsequence so we substract

     solve(int n) {
        if(n == 0) {
            return 1;
        }

        int total = 2 * solve(n - 1);

        return total; 
     }

    But in above approach there is issue with handling duplicate characters 

    consider s = "xaba" 
     {""} sub = 1
     | x
     {"", "x"} sub = 2
     | a
     {"", "a", "x", "xa"} sub = 4
     | b
     {"", "b", "a", "ab", "x", "xb", "xa", "xab"}  sub = 8
     | a (duplicate) 
     {"", "a", "b", "ba", "a", "aa", "ab", "aba", "x", "xa", "xb", "xba", "xa", "xaa", "xab", "xaba"} sub = 16

    while duplicate
    Now check the previous state length of subsequences when a was came, and bcz in first when a comes it asks each character of its previous state to whether append or not and when duplicate comes again it asks those same characte to append or not which leads of duplication so to know no.of duplicates if we know the previous state lenght that many duplicate subsequences will be generated like in above case 'a' comes as duplicate at i = 3 and it has occurred at i = 1 and its previous state length  is 2 and in i = 3 state subsequences there are exactly 2 subsequences they are "a", "xa" so the answer would be total subsequneces - duplicate subsequneces - 1 empty string
    i.e. 16 - 2 - 1 => 13 

    solve = 2 * solve(n - 1) % MOD and should handle duplciate

    How to code duplication logic

    "xaba"
    - 'a' arrive 1st time index 1 so we need to know what were the subsequnece or length before it
        so we can call solve(1) 1 bcz there is only character before 1st 'a'
    - when 'a' comes at index 3 it must know that when did i occur first in s, so we maintain prev array to store the first index of character

    prev[3] = 1 - 1
    so we call solve(prev[3] - 1) where prev[3]=1 stores the index of duplicate of itself

    Code:
    return solve(n) - 1;

    int solve(int n) {
        if(n == 0) return 1;  // Empty ""

        int total = 2 * solve(n - 1);

        int duplicate = solve(prev[n] - 1);

        total -= duplicate;

        return total
    }

    int[] lastSeen = new int[26];

    For s= "xaba" prev = {0, 0, 0, 1}
    for(int i = 0; i <= n; i++) {
        int idx = s.charAt(i) - 'a';
        prev[i] = lastSeen[idx];
        lastSeen[idx] = i;
    }
*/