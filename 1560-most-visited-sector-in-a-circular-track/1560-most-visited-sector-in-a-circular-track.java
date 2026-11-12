class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int m = rounds.length;
        int start = rounds[0];
        int end = rounds[m - 1];
        List<Integer> res = new ArrayList<>();
       
        if(start <= end) {
            for(int i = start; i <= end; i++) {
                res.add(i);
            }
        } else {
            for(int i = 1; i <= end; i++) {
                res.add(i);
            }

            for(int i = start; i <= n; i++) {
                res.add(i);
            }
        }

        return res;
    }
}

// 3 5 1 4 2
// 1 - 2,  2 - 2,  3 - 2 , 4 - 2 , 5 - 2

/*
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] diff = new int[n + 2];
        int start = rounds[0];
        diff[start] += 1;
        diff[start + 1] -= 1;

        for (int i = 0; i < rounds.length - 1; i++) {
            int u = rounds[i];
            int v = rounds[i + 1];

            if (u < v) {
                // Normal forward range: (u, v] -> [u + 1, v]
                diff[u + 1] += 1;
                diff[v + 1] -= 1;
            } else if (u > v) {
                // Circular wrap: (u, n] and [1, v]
                diff[u + 1] += 1;
                diff[1] += 1;
                diff[v + 1] -= 1;
            }
        }

        // Compute prefix sums to get the final counts for indices 1 to n
        int[] count = new int[n];
        int currCount = 0;
        int maxVisSector = 0;
        for (int i = 1; i <= n; i++) {
            currCount += diff[i];
            count[i - 1] = currCount;

            maxVisSector = Math.max(maxVisSector, count[i - 1]);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (count[i] == maxVisSector) {
                res.add(i + 1);
            }
        }

        return res;
    }

 Dry Run for path = {1, 3, 1, 2} and N = 4

We create a diff array of size N + 2 (indices 0 to 5) initialized to all zeros:
diff = [0, 0, 0, 0, 0, 0] (Index 0 is unused, indices 1..4 are our elements, index 5 is overflow)

Step 1: Move 1 -> 3 (u = 1, v = 3)

We want to increment indices 2 and 3.
• diff[u + 1] += 1 \(\rightarrow \) diff[2] += 1 (Start adding at 2)
• diff[v + 1] -= 1 \(\rightarrow \) diff[4] -= 1 (Stop adding at 4, so 3 is the last one included)
Current diff array:

Index	1	2	3	4	5
diff	0	+1	0	-1	0


Step 2: Move 3 -> 1 (u = 3, v = 1) — Circular Wrap

We want to increment index 4 (end of array) and index 1 (start of array).
• diff[u + 1] += 1 \(\rightarrow \) diff[4] += 1 (Start adding at 4; note -1 + 1 = 0)
• diff[1] += 1 \(\rightarrow \) diff[1] += 1 (Start adding at 1)
• diff[v + 1] -= 1 \(\rightarrow \) diff[2] -= 1 (Stop adding at 2; note +1 - 1 = 0)
Current diff array:

Index	1	2	3	4	5
diff	+1	0	0	0	0


Step 3: Move 1 -> 2 (u = 1, v = 2)

We want to increment only index 2.
• diff[u + 1] += 1 \(\rightarrow \) diff[2] += 1 (Start adding at 2)
• diff[v + 1] -= 1 \(\rightarrow \) diff[3] -= 1 (Stop adding at 3, so only 2 gets it)
Final diff array before prefix sum:

Index	1	2	3	4	5
diff	+1	+1	-1	0	0


Step 4: Final Prefix Sum Loop (i = 1 to 4)

Now we walk through diff from 1 to 4, keeping a running total (currentCount += diff[i]):
1. i = 1: currentCount = 0 + diff[1] = 0 + 1 = 1
2. i = 2: currentCount = 1 + diff[2] = 1 + 1 = 2
3. i = 3: currentCount = 2 + diff[3] = 2 + (-1) = 1 (The -1 here cancelled out the +1 from index 2!)
4. i = 4: currentCount = 1 + diff[4] = 1 + 0 = 1
Final Result: [1, 2, 1, 1]

Why v + 1 instead of v?

If you did diff[v] -= 1 instead of diff[v + 1] -= 1 in Step 3 (1 -> 2):
• diff[2] would get +1 and immediately -1 (0), meaning index 2 would never get incremented!
• Subtracting at v + 1 ensures index v still receives the increment during the running sum, and the effect only turns off after index v.
Would you like to see:
• How this handles a case where v == N (the last index) and why the array size needs to be N + 2?
• A dry run with a different path of your choice?

bro but we needed sector 1 also to be 2 bcz it was visisted twice right once 1 to 3 and 3 to 1

Yes, you are completely right. If the starting position (path[0]) counts as an initial visit, sector 1 is visited twice, making the final counts [2, 2, 1, 1].

Why Sector 1 Was 1 Earlier

In the previous code, each step u -> v only incremented (u, v] (the sectors entered during the move) and skipped u so we wouldn't double-count intermediate stops:
• 1 -> 3: Counted 2, 3 (skipped starting point 1)
• 3 -> 1: Counted 4, 1 (counted 1 on arrival)
• 1 -> 2: Counted 2 (skipped 1 since it was already counted on arrival)
Because path[0] (1) was never arrived at from a previous move, its very first visit was missed.

How to Fix It (One-Line Addition)

You only need to add +1 to the starting sector (path[0]) before the loop begins. Every intermediate stop after that is already counted once when you land on it.
java
// Count the initial starting sector once
int start = path[0];
diff[start] += 1;
diff[start + 1] -= 1;
Use code with caution.

Updated Dry Run for path = {1, 3, 1, 2}

• Initial (path[0] = 1): diff[1] += 1, diff[2] -= 1
• Move 1 -> 3: diff[2] += 1, diff[4] -= 1
• Move 3 -> 1: diff[4] += 1, diff[1] += 1, diff[2] -= 1
• Move 1 -> 2: diff[2] += 1, diff[3] -= 1
Final diff array:

Index	1	2	3	4	5
diff	+2	0	-1	0	0


Prefix Sum Result (i = 1 to 4)

1. i = 1: 0 + 2 = 2 (Sector 1 visited twice!)
2. i = 2: 2 + 0 = 2 (Sector 2 visited twice!)
3. i = 3: 2 - 1 = 1 (Sector 3 visited once)
4. i = 4: 1 + 0 = 1 (Sector 4 visited once)
Final Output: [2, 2, 1, 1]
*/