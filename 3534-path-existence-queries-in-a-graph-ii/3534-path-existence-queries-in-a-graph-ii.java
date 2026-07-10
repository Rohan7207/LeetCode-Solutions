class Solution {
    private int LOG = 20;

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int len = nums.length;
        int[][] arr = new int[len][2];

        // Store value with original index
        for (int i = 0; i < len; i++) {
            arr[i][0] = nums[i]; // Value
            arr[i][1] = i; // Original Index
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // Map original index → sorted position
        int[] position = new int[len];
        for (int i = 0; i < len; i++) {
            position[arr[i][1]] = i;
        }

        int[] component = new int[len];
        int comp = 0;
        component[0] = 0;
        for (int i = 1; i < len; i++) {
            if (arr[i][0] - arr[i - 1][0] > maxDiff)
                comp++;

            component[i] = comp;
        }

        // Build next[] with two pointers
        int[] next = new int[len];
        int j = 0;
        for (int i = 0; i < len; i++) {
            j = Math.max(j, i);

            while (j + 1 < len && arr[j + 1][0] - arr[i][0] <= maxDiff) {
                j++;
            }

            next[i] = j;
        }

        // Binary Lifting we've built, For every sorted position i, we can now answer:
        // "Where will I end up after 2^k greedy jumps?" in O(1).
        int[][] jump = new int[len][LOG];

        // Fill first column
        for (int i = 0; i < len; i++) {
            jump[i][0] = next[i];
        }

        // Fill remaining columns
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < len; i++) {
                jump[i][k] = jump[jump[i][k - 1]][k - 1];
            }
        }

        int qLen = queries.length;
        int[] ans = new int[qLen];

        for (int i = 0; i < qLen; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int left = position[u];
            int right = position[v];

            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }

            if (component[left] != component[right]) {
                ans[i] = -1;
            } else {
                ans[i] = minJump(left, right, jump);
            }
        }

        return ans;
    }

    private int minJump(int left, int right, int[][] jump) {
        if (left == right)
            return 0;

        int answer = 0;

        for (int k = LOG - 1; k >= 0; k--) {
            if (jump[left][k] < right) {
                left = jump[left][k];

                answer += 1 << k;
            }
        }

        return answer + 1;
    }
}

/*
    Most Important Observation

After sorting, the graph can be visualized as connected chains separated by gaps larger than maxDiff.

For this example:

Sorted Values

1      5 ---- 7 ---- 8 ---- 10
│      │      │      │      │
C0     C1     C1     C1     C1
P0     P1     P2     P3     P4

Where:

C = Component ID
P = Position in the sorted array

Then each query becomes:

Different components? → -1
Same component? → |position[u] - position[v]|

That's why the solution answers every query in O(1) after an O(n log n) preprocessing step.
*/