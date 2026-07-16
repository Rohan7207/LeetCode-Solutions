class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;

        while(i < g.length && j < s.length) {
            if(s[j] >= g[i]) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i;
    }
}

/*
    Let's understand the problem first.

Example
g = [1,2,3]
s = [1,1]

Here:

g = greed factors of children.
s = sizes of cookies.

Child greed:

Child 1 needs at least 1
Child 2 needs at least 2
Child 3 needs at least 3

Cookies:

1
1

Give:

Cookie 1 → Child 1 ✅
Cookie 1 cannot satisfy Child 2 ❌

Answer = 1

Another Example
g = [1,2]
s = [1,2,3]

Possible assignment:

Child(1) ← Cookie(1)
Child(2) ← Cookie(2)

Answer = 2

Important Observations
One child can receive at most one cookie.
One cookie can be given to only one child.
We want to maximize the number of satisfied children.
🟢 Hint 1

Think about sorting.

Sort both arrays:

Greed:
1 2 3 4

Cookies:
1 2 2 5

Now ask yourself:

Should I give a large cookie to a child with very small greed?

For example:

Greed:
1 2 100

Cookies:
2 100

If you give:

100 → Child 1

then only cookie 2 remains:

2 → Child 100 ❌

Only 1 child is satisfied.

Instead:

2 → Child 1 ✅
100 → Child 100 ✅

Now 2 children are satisfied.

Think about this

After sorting both arrays, which child should receive the smallest cookie?

The child with the smallest greed?
The child with the largest greed?

Why?

smallest cookie should be assigned to smallest greed one

Exactly! ✅ That's the greedy insight.

Suppose:

Greed:   1 2 3 4
Cookies: 1 2 2 5

The smallest cookie should always be tried on the least greedy child.

Why?

Because if the smallest cookie cannot satisfy the least greedy child, it certainly cannot satisfy any child with higher greed.

For example:

Greed:   2 3 5
Cookies: 1 3 5

Cookie 1 cannot satisfy greed 2.

Can it satisfy greed 3 or 5?

No.

So cookie 1 is useless and we skip it.

🟢 Hint 2

After sorting:

Greed:   1 2 3
Cookies: 1 2 2

Maintain two pointers:

i → current child
j → current cookie

Now compare:

cookie[j] >= greed[i] ?
Yes → Assign the cookie.
One more child is satisfied.
Move both pointers.
No → Cookie is too small.
It cannot satisfy this child or any greedier child.
Discard this cookie by moving only the cookie pointer.
Example
Greed:   1 2 3
Cookies: 1 1 3

Start:

i = 0 (greed = 1)
j = 0 (cookie = 1)

1 >= 1 ✅

Assign:

Satisfied = 1
i++
j++

Now:

i = 1 (greed = 2)
j = 1 (cookie = 1)

1 >= 2 ❌

This cookie is too small, so:

j++

Keep i where it is because the child still needs a cookie.

Question: When do we stop the loop? Think about the conditions on i (children) and j (cookies).

till cookies length

You're close, but the complete condition is:

We should stop when either:

We've assigned/checked all children, or
We've used all cookies.

So the loop condition is:

while (i < g.length && j < s.length)
Why both conditions?
Case 1: No cookies left
Greed:   1 2 3
Cookies: 1 2

After using both cookies:

j == s.length

There are still children left, but we can't satisfy anyone else.

Case 2: All children are satisfied
Greed:   1 2
Cookies: 1 2 3 4

After satisfying both children:

i == g.length

There are cookies remaining, but there are no children left to give them to.

Final Greedy Logic
Sort greed array
Sort cookies array
        ↓
i = 0 (child)
j = 0 (cookie)

while (i < g.length && j < s.length)

    if (cookie >= greed)
        satisfy child
        i++
        j++
    else
        cookie too small
        j++

return i

Notice that i itself becomes the answer, because every time a child is satisfied, i moves to the next child. By the end, i equals the number of satisfied children.

This is why many solutions simply return:

return i;

without needing a separate counter.
*/