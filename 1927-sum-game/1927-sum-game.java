class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int[] left = get(num.substring(0, n / 2));
        int[] right = get(num.substring(n / 2, n));

        int sumLeft = left[0], questionsLeft = left[1];
        int sumRight = right[0], questionsRight = right[1];

        return (questionsLeft + questionsRight) % 2 == 1
                || sumLeft - sumRight != ((questionsRight - questionsLeft) * 9) / 2;
    }

    private int[] get(String s) {
        int digitsSum = 0;
        int questions = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '?') {
                questions++;
            } else {
                digitsSum += ch - '0';
            }
        }

        return new int[] { digitsSum, questions };
    }
}

/*
    1. Start with the existing difference

Suppose:

sumLeft = 10
sumRight = 4

Then:

sumLeft - sumRight = 6

So currently:

Left is 6 more than Right
2. What do the ? on each side do?

Suppose:

questionsLeft  = 1
questionsRight = 3

There are 2 more ? on the right.

So:

questionsRight - questionsLeft = 3 - 1 = 2

Now, why multiply by 9?

Because digits range from:

0 → 9

The maximum difference between two choices is:

9 - 0 = 9

So the imbalance of 2 question marks corresponds to:

2 × 9 = 18

But because Alice and Bob alternate and each gets half of the relevant difference:

18 / 2 = 9

Therefore Bob can force a compensation of:

9
3. That's exactly what this calculates
((questionsRight - questionsLeft) * 9) / 2

In our example:

((3 - 1) × 9) / 2
= 18 / 2
= 9

So the condition becomes:

sumLeft - sumRight != 9

Our existing difference was 6:

6 != 9

So Bob cannot make the sums equal → Alice wins.

4. When does Bob win?

Bob can force equality when:

sumLeft - sumRight == ((questionsRight - questionsLeft) * 9) / 2

For example:

sumLeft = 13
sumRight = 4

difference = 9

and:

questionsLeft = 1
questionsRight = 3

((3 - 1) × 9) / 2 = 9

Therefore:

difference == required compensation
9 == 9

Bob can force:

Left sum = Right sum

Therefore Alice loses.

🧠 The important way to remember it

Don't memorize the entire expression.

Think:

Current difference:
    sumLeft - sumRight

        ↓

Difference that the ? can compensate for:
    (questionsRight - questionsLeft) × 9 / 2

        ↓

If they are EQUAL:
    Bob can make sums equal
    → Bob wins

If they are DIFFERENT:
    Bob cannot make sums equal
    → Alice wins

Therefore:

sumLeft - sumRight != ((questionsRight - questionsLeft) * 9) / 2

means:

"The current difference cannot be exactly cancelled by the maximum adjustment available from the imbalance of ?, so Bob cannot force equality, and Alice wins."
*/

/*
    Approach: Guess + Mathematical Induction Verification
Intuition
Hint 1

If the number of question marks is odd, then Alice is definitely the winner.

Hint 1 Explanation

Since Alice goes first, the last question mark must be filled by Alice.

It is clear that, in the range [0,9], there is at most one digit d that can make the sum of the digits in the first half equal to the sum of the digits in the second half. Therefore, Alice can simply replace the last question mark with any digit other than d.

Thus, we only need to consider the case where the number of question marks is even.

Hint 2

If the number of question marks is 0, then Bob wins if and only if the sum of the digits in the first half equals the sum of the digits in the second half.

Hint 3

If the number of question marks is 2 and they appear on different sides, that is, one question mark is in the first half and the other is in the second half, then Bob wins if and only if the sum of the known digits in the first half equals the sum of the known digits in the second half.

Hint 3 Explanation

If the sum of the known digits in the first half is equal to that in the second half, Alice can choose either question mark and replace it with any digit d. Bob can then replace the other question mark with the same digit d. Therefore, Bob is guaranteed to win.

If the sums of the known digits in the two halves are not equal, Alice can choose the question mark in the half with the larger sum and replace it with 9. Since no digit greater than 9 can be chosen, Alice is guaranteed to win.

Hint 4

If the number of question marks is 2 and both appear on the same side, Bob wins if and only if the sum of the known digits on that side is exactly 9 less than the sum of the known digits on the other side.

Hint 4 Explanation

Bob can always ensure that the digits chosen for two consecutive question marks sum to 9. Specifically, if Alice replaces one question mark with a digit d, Bob can replace the other question mark with 9−d.

Therefore, if the above condition is satisfied, Bob is guaranteed to win. Otherwise:

If the difference exceeds 9, Alice replaces the question mark with 0.

If the difference does not exceed 9, Alice replaces the question mark with 9.

In either case, Bob cannot choose a digit in [0,9] that makes the sums of the two halves equal, so Alice is guaranteed to win.

Hint 5

Suppose the sum of the known digits in the first half is n 
0
​
 , the number of question marks in the first half is q 
0
​
 , the sum of the known digits in the second half is n 
1
​
 , and the number of question marks in the second half is q 
1
​
 . If q 
0
​
 +q 
1
​
  is even, then Bob wins if and only if

n_0 - n_1 = \frac{9}{2}(q_1 - q_0). \tag{1}

We can interpret this equation based on Hints 3 and 4. Without loss of generality, assume q 
0
​
 ≤q 
1
​
 .

For the q 
0
​
  question marks on the two sides, according to Hint 3, Alice and Bob can choose digits so that their contributions to the two halves cancel each other out.

For the remaining q 
1
​
 −q 
0
​
  question marks in the second half, according to Hint 4, Alice and Bob can pair them up, with each pair contributing a total of 9.

Therefore, for the sums of the two halves to become equal, the first half must initially have a sum that is  
2
9
​
 (q 
1
​
 −q 
0
​
 ) larger than the second half.

This gives Equation (1).


*/