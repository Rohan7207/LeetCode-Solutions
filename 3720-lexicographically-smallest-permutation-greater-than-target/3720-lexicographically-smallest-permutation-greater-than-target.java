class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder res = new StringBuilder();
        int n = target.length();

        for(int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';

            // Case 1: First try to place the same character as target[i] at the current position
            if(count[targetChar] > 0) {
                count[targetChar]--;

                // Check if the remaining characters can form a string greater than target[i+1:]
                if(canFormGreaterString(count, target, i + 1)) {
                    res.append(target.charAt(i));
                    continue;
                }

                // Cannot form a larger string, backtrack                    
                count[targetChar]++;
            }

            // Case 2: Place a character greater than target[i] at the current position
            for(int j = targetChar + 1; j < 26; j++) {
                if(count[j] > 0) {
                    count[j]--;
                    res.append((char) ('a' + j));

                    // Fill remaining positions with the smallest lexicographical order and return final string
                    res.append(getMinString(count));

                    return res.toString();
                }
            }

            // No feasible solution found, return directly
            return "";
        }

        return "";
    }

    // Check if the remaining characters can form a string greater than the suffix.
    private boolean canFormGreaterString(int[] count, String target, int start) {
        String maxStr = getMaxString(count);
        String suffixStr = target.substring(start);

        return maxStr.compareTo(suffixStr) > 0;
    }

    // Get the maximum lexicographical string (in descending order)
    private String getMaxString(int[] count) {
        StringBuilder ans = new StringBuilder();

        for(int i = 25; i >= 0; i--) {
            if(count[i] > 0) {
                ans.append(String.valueOf((char) ('a' + i)). repeat(count[i]));
            }
        }

        return ans.toString();
    }

    // Get the lexicographically smallest string (in ascending order)
    private String getMinString(int[] count) {
        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < 26; i++) {
            if(count[i] > 0) {
                ans.append(String.valueOf((char) ('a' + i)). repeat(count[i]));
            }
        }

        return ans.toString();
    }
}

/*
    . This code is solving:

Use all characters of s exactly once to create a permutation that is lexicographically greater than target, and return the smallest such permutation.

The important idea is greedy + backtracking.

Let's use a larger example that covers almost every part of the code.

Example
s      = "aabccde"
target = "abacced"

Characters available:

a → 2
b → 1
c → 2
d → 1
e → 1

We want the smallest permutation of s that is > target.

1. Build the frequency array
int[] cnt = new int[26];

for (char c : s.toCharArray()) {
    cnt[c - 'a']++;
}

After processing "aabccde":

a: 2
b: 1
c: 2
d: 1
e: 1

Everything else is 0.

2. Start constructing the answer
StringBuilder res = new StringBuilder();

We process target from left to right.

Our goal is to stay equal to target for as long as possible, because we want the smallest string that is greater.

Position 0

Target:

target = a b a c c e d
         ↑

Current character:

targetChar = 'a'

We first try:

if (cnt[targetChar] > 0)

We have two as, so use one:

cnt[a]--;

Now:

remaining:

a: 1
b: 1
c: 2
d: 1
e: 1

Our answer currently:

"a"

But we need to ask:

Can the remaining characters create something greater than the remaining target?

Remaining target:

"bacced"

The largest possible arrangement of our remaining characters is:

"edccba"

because getMaxString() puts characters in descending order.

String maxStr = getMaxString(cnt);

So:

maxStr = "edccba"
suffix = "bacced"

Compare:

"edccba" > "bacced"

Yes.

Therefore:

canFormGreater(...) == true

So we keep the a:

res.append(target.charAt(i));
continue;

Answer so far:

res = "a"
Why are we allowed to keep a?

Because although we're currently equal to target:

a = a

we know that some arrangement of the remaining characters can eventually make the whole string greater.

And keeping the same character gives us the smallest possible answer.

That's the greedy idea.

Position 1

Target:

a b a c c e d
  ↑

Try same character:

b

We have one b.

Remove it:

remaining:

a: 1
c: 2
d: 1
e: 1

Remaining target:

"acced"

Largest possible remaining string:

"edcca"

Compare:

"edcca" > "acced"

Yes.

So keep b.

res = "ab"
Position 2

Target:

a b a c c e d
    ↑

Try same character:

a

We have one a remaining.

Remove it.

Remaining:

c: 2
d: 1
e: 1

Remaining target:

"cced"

Largest remaining:

"edcc"

Compare:

"edcc" > "cced"

Yes.

Keep a.

res = "aba"
Position 3

Target:

a b a c c e d
      ↑

Try same:

c

Remove one c.

Remaining:

c: 1
d: 1
e: 1

Remaining target:

"ced"

Largest:

"edc"

And:

"edc" > "ced"

So keeping c is possible.

res = "abac"
Position 4 — Important part: BACKTRACKING

Target:

a b a c c e d
        ↑

Again try same character:

c

Remove it.

Remaining:

d: 1
e: 1

Remaining target:

"ed"

Largest possible remaining:

"ed"

Now compare:

"ed".compareTo("ed") == 0

So:

canFormGreater(...) == false

This is the important situation.

If we keep this c, the best we can possibly do is:

abacced

which is equal to target, not greater.

Therefore we cannot keep this c.

So we execute:

cnt[targetChar]++;

We put the c back.

Now:

remaining:

c: 1
d: 1
e: 1

And our answer goes back conceptually to:

"abac"

We now need to make the answer greater.

6. Try a character GREATER than target[i]

Target character is:

c

So:

for (int j = targetChar + 1; j < 26; j++)

means:

d
e
f
...
z

We first check d.

We have a d:

if (cnt[d] > 0)

Yes.

So:

cnt[d]--;
res.append('d');

Now:

res = "abacd"

Look what happened:

target = abac c e d
             ↑
answer = abac d ...
             ↑

At this position:

d > c

Therefore the entire answer is automatically greater than target, regardless of what comes after it.

This is extremely important.

7. Now fill the remaining characters with the SMALLEST order

Remaining characters are:

c
e

We don't need to worry about becoming greater anymore because we've already established:

d > c

So we want the smallest possible suffix.

That's why:

res.append(getMinString(cnt));

getMinString() sorts remaining characters ascending:

c < e

so:

"ce"

Our final answer becomes:

"abacdce"
8. Verify
target = abacced
answer = abacdce

Compare from left:

a = a
b = b
a = a
c = c
d > c

Therefore:

"abacdce" > "abacced"

And because we stayed equal for as long as possible before choosing d, this is the smallest greater permutation.

Now understand the three major cases

The entire algorithm revolves around these three cases.

Case 1: Same character works
if (cnt[targetChar] > 0) {
    cnt[targetChar]--;

    if (canFormGreater(cnt, target, i + 1)) {
        res.append(target.charAt(i));
        continue;
    }

    cnt[targetChar]++;
}

Meaning:

"Can I keep the current character equal to target and still eventually make the answer greater?"

If yes → keep it.

This gives the smallest answer.

Case 2: Same character doesn't work

Suppose:

target[i] = c

and keeping c means the remaining characters can only make the suffix:

<= target suffix

Then we undo:

cnt[targetChar]++;

This is backtracking.

Then we try:

d
e
f
...

until we find a character greater than c.

Case 3: We found a greater character

Suppose:

target[i] = c

and we choose:

d

Now:

d > c

The answer is already guaranteed to be greater.

So don't waste time checking the remaining characters.

Just put them in the smallest order:

res.append(getMinString(cnt));
What does canFormGreater() really mean?

This is probably the most confusing function:

private boolean canFormGreater(int[] cnt, String target, int start) {
    String maxStr = getMaxString(cnt);
    String suffix = target.substring(start);

    return maxStr.compareTo(suffix) > 0;
}

Suppose:

remaining characters = c d e
target suffix        = c e d

The largest possible remaining permutation is:

edc

If:

edc > ced

then there exists some arrangement that can make the final answer greater.

Therefore keeping the current prefix is safe.

But if:

maxStr == suffix

then even the largest possible remaining arrangement only gives equality.

Therefore keeping the current prefix is impossible.

Why only check the MAXIMUM?

Because we're asking:

"Is there any permutation of the remaining characters that is greater than the target suffix?"

The maximum permutation is the best possible chance.

So:

maximum remaining > target suffix

means:

some valid arrangement exists

But:

maximum remaining <= target suffix

means:

no arrangement can make it greater

That's why getMaxString() is enough.

And why getMinString()?

Once we choose a character greater than the target:

target:  ... c ...
answer:  ... d ...
             ↑
          already greater

The rest doesn't affect whether we're greater.

So we minimize the suffix:

getMinString()

This is what ensures we get the smallest greater permutation, not just any greater permutation.

🔥 Whole algorithm in one picture
At every position
       ↓
Try SAME character as target
       ↓
Can remaining chars still make answer greater?
       ↓
   YES          NO
    ↓            ↓
 Keep it      Put it back
    ↓            ↓
 continue     Try character > target[i]
                  ↓
             Found one?
                  ↓
                YES
                  ↓
       Fill remaining chars
        in ascending order
                  ↓
               return

If we reach the end without finding a greater possibility:

return "";

means:

No permutation of s is greater than target.

Interview one-liner

Greedily match the target from left to right; whenever matching a character cannot possibly lead to a greater permutation, backtrack and choose the smallest available character larger than the target character, then append all remaining characters in ascending order.
*/