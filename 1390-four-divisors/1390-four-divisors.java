class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int count = 0;
            int sum = 1 + x;
            int sqrt = (int) Math.sqrt(x);

            for (int j = 2; j <= sqrt; j++) {
                if (j * j == x) {
                    count++;
                    sum += j;
                }

                if (x % j == 0) {
                    count += 2;
                    sum += j + x / j;
                }

                if (count > 2) {
                    break;
                }
            }

            if (count == 2) {
                ans += sum;
            }
        }

        return ans;
    }
}

// Time Complexity: O(n × √m), where m = max(nums)
// Space Complexity: O(1)


// I check divisors only up to √x, count divisor pairs, and stop as soon as more than two additional divisors are found.

/*
    🔑 Key Observation

You already know:

1 and x

So instead of asking:

Does x have exactly 4 divisors?

we can ask:

Does x have exactly 2 more divisors besides 1 and x?

Therefore:

count == 2

means the total number of divisors is 4.

✨ Magic Line
count += 2;
sum += j + x / j;

If:

x = 21
j = 3

then:

3 × 7 = 21

So we immediately discover both:

3 and 7

without checking 7 separately.

💡 How We Came Up With It

Your original solution checked:

2 → x/2

That's unnecessary because divisors appear in pairs.

For:

x = 28

we have:

1 × 28
2 × 14
4 × 7

When we reach 4, we already discover both 4 and 7.

So we only need:

j <= √x

This reduces the work from roughly O(x) to O(√x) per number.

⚠️ Important Line

There is one mistake in your current implementation.

You have:

if (j * j == x) {
    count++;
    sum += j;
}

if (x % j == 0) {
    count += 2;
    sum += j + x / j;
}

For a perfect square, both if conditions execute.

For example:

x = 16
j = 4

First if:

count += 1
sum += 4

Second if also executes:

count += 2
sum += 4 + 4

So 4 gets counted incorrectly.

Use else:

if (j * j == x) {
    count++;
    sum += j;
} else if (x % j == 0) {
    count += 2;
    sum += j + x / j;
}

However, with your count > 2 logic, the perfect-square case will generally get rejected anyway; the important issue is that the divisor sum is incorrectly accumulated.

🧩 Pattern Recognition

Number Theory → Divisor Pairing

Whenever you see:

Find/count/sum divisors of n

immediately think:

Check j ≤ √n
       ↓
if j divides n
       ↓
j and n/j are a divisor pair

And always remember the special case:

j * j == n

because the pair contains the same divisor.

📚 Similar Questions
LeetCode 1390 — Four Divisors → this problem
LeetCode 1952 — Three Divisors → perfect-square observation
LeetCode 507 — Perfect Number → divisor enumeration
LeetCode 728 — Self Dividing Numbers → digit/divisor checking

🚀 What Can Be Optimized

Your main optimization is already correct:

int sqrt = (int) Math.sqrt(x);

and:

for (int j = 2; j <= sqrt; j++)

gives:

O(n × √m)

instead of checking up to x/2.

Just fix the perfect-square double counting with else if.
*/