class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int aliceTotal = 0;
        int bobTotal = 0;
        for (int i = 0; i < aliceSizes.length; i++) {
            aliceTotal += aliceSizes[i];
        }

        for (int i = 0; i < bobSizes.length; i++) {
            bobTotal += bobSizes[i];
        }

        int diff = (aliceTotal - bobTotal) / 2;

        Set<Integer> set = new HashSet<>();

        for (int num : bobSizes) {
            set.add(num);
        }

        for (int x : aliceSizes) {
            int y = x - diff;

            if (set.contains(y)) {
                return new int[] { x, y };
            }
        }

        return new int[] {};
    }
}

/*
    🟢 Hint 1

Don't think about comparing every pair of boxes.

Instead, first compute:

AliceTotal = sum(aliceSizes)
BobTotal   = sum(bobSizes)

Now suppose:

Alice gives a box with x candies.
Bob gives a box with y candies.

After the swap:

Alice = AliceTotal - x + y

Bob   = BobTotal - y + x

Since they should have the same total after the swap, write the equation:

AliceTotal - x + y = BobTotal - y + x

👉 Your task: Simplify this equation.

You should end up with a relation between x, y, and the difference of the two sums. That simplified equation is the key to solving the problem efficiently.

x = y + (alicetotal - bobtotal / 2 ) and y = x + (alicetotal - bobtotal / 2 )

You're very close. The only mistake is with the algebra (and parentheses).

Let's simplify it carefully.

We have:

AliceTotal - x + y = BobTotal - y + x

Move everything to one side:

AliceTotal - BobTotal = 2x - 2y

Divide both sides by 2:

(AliceTotal - BobTotal) / 2 = x - y

or equivalently,

x = y + (AliceTotal - BobTotal) / 2

or

y = x - (AliceTotal - BobTotal) / 2

✅ So your first equation is correct if you add the parentheses properly:

x = y + ((AliceTotal - BobTotal) / 2)

not

x = y + (AliceTotal - BobTotal / 2)   ❌

because division has higher precedence than subtraction.

🎯 Now think about this

Suppose you've computed:

diff = (AliceTotal - BobTotal) / 2

Then the equation becomes:

x = y + diff

or equivalently,

y = x - diff

Question: If you're iterating through every x in aliceSizes, how can you check in O(1) whether the required y exists in Bob's boxes?

(Hint: Think about which data structure supports fast existence checks.)

hashset

Exactly! ✅ HashSet is the key optimization.

Step 1: Calculate the sums
AliceTotal
BobTotal

Compute:

diff = (AliceTotal - BobTotal) / 2
Step 2: Store Bob's boxes

Put every box from Bob into a HashSet.

Bob Boxes

1
2
5
8

becomes

HashSet = {1,2,5,8}

Now checking whether a value exists takes O(1).

Step 3: Iterate through Alice's boxes

Suppose

Alice = [4,1,2]
Bob   = [1,2,5]

AliceTotal = 7
BobTotal   = 8

diff = (7-8)/2 = -1

Now take each Alice box.

Alice chooses
x = 4

We know

x = y + diff

Rearrange:

y = x - diff

So

y = 4 - (-1)
  = 5

Now simply check

if(set.contains(5))

Yes!

Answer:

[4,5]
Step 4: Why does this work?

After swapping

Alice loses x
Alice gains y

Bob loses y
Bob gains x

The equation guarantees

AliceTotal - x + y
=
BobTotal - y + x

So both totals become equal.

Final Algorithm
Compute AliceTotal
        ↓
Compute BobTotal
        ↓
diff = (AliceTotal - BobTotal) / 2
        ↓
Store Bob's boxes in HashSet
        ↓
For every x in Alice:
        y = x - diff
        ↓
Is y in HashSet?
        ↓
Yes → return {x, y}
*/