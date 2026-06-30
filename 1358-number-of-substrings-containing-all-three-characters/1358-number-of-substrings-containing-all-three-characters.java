class Solution {
    public int numberOfSubstrings(String s) {
        int ans = 0;
        int n = s.length();

        int[] count = new int[3];

        int left = 0;

        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                ans += n - right;

                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return ans;
    }
}

/*
    We are not calculating the last c specifically. We are using the current right pointer index where the window has become valid.

Example:

s = "abcabc"
index:
0 1 2 3 4 5

a b c a b c

When:

right = 2

Current window:

abc

It is valid because it contains:

a ✅
b ✅
c ✅

Now:

ans += n - right;

means:

6 - 2 = 4

The 4 valid substrings are:

abc       (0,2)
abca      (0,3)
abcab     (0,4)
abcabc    (0,5)

Why?

Because once abc is already inside the window, adding more characters to the right cannot remove a,b,c.

Now the important part:

After shrinking:

left++;

Suppose:

left = 1
right = 2

Window:

bc

Not valid ❌

So we keep expanding.

Later:

left = 1
right = 3

Window:

bca

Valid ✅

Again:

ans += n - right;

Here:

6 - 3 = 3

Valid substrings:

bca
bcab
bcabc

So your understanding:

"we find the index where abc is complete and n - that index gives all valid substrings from that point to end"

✅ Correct.

Just remember:

It is not the last c index.

It is:

current right pointer
when window becomes valid

Pattern:

Expand right
      ↓
Window gets a,b,c
      ↓
Count all extensions: n-right
      ↓
Shrink left
      ↓
Repeat
*/

/*
    Brute Force - O(n ^ 2)
    int ans = 0;
    int n = s.length();

    for(int i = 0; i < n; i++) {
        int[] count = new int[3];

        for(int j = i; j < n; j++) {
            count[s.charAt(j) - 'a']++;
            
            if(count[0] > 0 && count[1] > 0 && count[2] > 0) {
                ans++;
            }
        }
    }

    return ans;
*/