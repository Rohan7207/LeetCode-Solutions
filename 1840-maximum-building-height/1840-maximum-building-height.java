// Problem: Maximum Building Height
// Link: https://leetcode.com/problems/maximum-building-height/?envType=daily-question&envId=2026-06-20
// Difficulty: Hard

// Approach:
// We need to find the maximum possible height of any building.
// Rules:
//     1. Building 1 height must be 0.
//     2. Adjacent building height difference <= 1.
//     3. Some buildings have max height restrictions.
// Key Idea:
//     Treat restricted buildings like checkpoints.
//     Since building 1 height is fixed as 0,
//     we manually add restriction:
//         [1, 0]
//     Then sort all restrictions by building id.
// Step 1:
//     If there are no restrictions,
//     height can grow like:
//         building 1 = 0
//         building 2 = 1
//         building 3 = 2
//         ...
//         building n = n - 1
//     So return n - 1.
// Step 2:
//     Add [1, 0] to restrictions.
// Step 3:
//     Sort restrictions by building id.
// Step 4:
//     Left to right adjustment.
//     For every current restriction,
//     check how much height is possible from previous restriction.
//     If previous restriction is:
//         [prevId, prevHeight]
//     and current restriction is:
//         [currId, currHeight]
//     distance = currId - prevId
//     Since height can increase only by 1 per building,
//     current height cannot be more than:
//         prevHeight + distance
//     So:
//         currHeight = min(currHeight, prevHeight + distance)
// Step 5:
//     Right to left adjustment.
//     A building can also be limited by a restriction on its right.
//     If next restriction is:
//         [nextId, nextHeight]
//     and current restriction is:
//         [currId, currHeight]
//     distance = nextId - currId
//     Current height cannot be more than:
//         nextHeight + distance
//     So:
//         currHeight = min(currHeight, nextHeight + distance)
// Step 6:
//     Now all restrictions are realistic from both sides.
//     For every pair of consecutive restrictions,
//     calculate the maximum peak possible between them.
//     left  = [id1, h1]
//     right = [id2, h2]
//     distance = id2 - id1
//     heightDiff = abs(h1 - h2)
//     First, height must cover the difference between h1 and h2.
//     Remaining distance can be used to go up and down equally.
//     peak = max(h1, h2) + (distance - heightDiff) / 2
//     Update answer with this peak.
// Step 7:
//     After the last restriction,
//     buildings can still increase by 1 until building n.
//     lastPossibleHeight = lastHeight + (n - lastId)
//     Update answer.
// Return answer.

// Time Complexity: O(r log r)
//
//     r = restrictions.length
//     Sorting takes O(r log r)
//
// Space Complexity: O(r)
//
//     We create a new restrictions array after adding [1, 0]


class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int l = restrictions.length;

        if (l == 0) {
            return n - 1;
        }

        int[][] arr = new int[l + 1][2];

        arr[0] = new int[] { 1, 0 };

        for (int i = 0; i < l; i++) {
            arr[i + 1] = restrictions[i];
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int m = arr.length;
        for (int i = 1; i < m; i++) {
            int prevId = arr[i - 1][0];
            int prevHeight = arr[i - 1][1];

            int currId = arr[i][0];
            int currHeight = arr[i][1];

            int distance = currId - prevId;

            arr[i][1] = Math.min(currHeight, prevHeight + distance);
        }

        for (int i = m - 2; i >= 0; i--) {
            int currId = arr[i][0];
            int currHeight = arr[i][1];

            int nextId = arr[i + 1][0];
            int nextHeight = arr[i + 1][1];

            int distance = nextId - currId;

            arr[i][1] = Math.min(currHeight, nextHeight + distance);
        }

        int max = 0;
        for (int i = 1; i < m; i++) {
            int id1 = arr[i - 1][0];
            int h1 = arr[i - 1][1];

            int id2 = arr[i][0];
            int h2 = arr[i][1];

            int distance = id2 - id1;

            int heightDiff = Math.abs(h1 - h2);

            int currMax = Math.max(h1, h2) + ((distance - heightDiff) / 2);

            max = Math.max(max, currMax);
        }

        int lastId = arr[m - 1][0];
        int lastHeight = arr[m - 1][1];

        max = Math.max(max, lastHeight + (n - lastId));

        return max;
    }
}
