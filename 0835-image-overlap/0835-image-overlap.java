// Problem: Image Overlap
// Link: https://leetcode.com/problems/image-overlap/?envType=daily-question&envId=2026-09-13
// Difficulty: Medium

// Approach:
// Use coordinate translation + HashMap frequency counting.
//
// 1. Store the coordinates of all 1s in img1 and img2.
// 2. For every pair of 1s, calculate the translation needed to align them:
//      dr = r1 - r2
//      dc = c1 - c2
// 3. Use (dr, dc) as the key in a HashMap and count how many pairs produce
//    the same translation.
// 4. The translation with the highest frequency gives the maximum overlap.

// Time Complexity: O(n^4) in the worst case
// Space Complexity: O(n^2)


class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        int n = img1.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1.add(new int[] { i, j });
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img2[i][j] == 1) {
                    ones2.add(new int[] { i, j });
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        for (int[] a : ones1) {
            for (int[] b : ones2) {
                int dr = a[0] - b[0];
                int dc = a[1] - b[1];

                String key = dr + "," + dc;
                map.put(key, map.getOrDefault(key, 0) + 1);

                ans = Math.max(ans, map.get(key));
            }
        }

        return ans;
    }
}
