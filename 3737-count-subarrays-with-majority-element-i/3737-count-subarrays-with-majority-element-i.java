// Problem: Count Subarrays With Majority Element I
// Link: https://leetcode.com/problems/count-subarrays-with-majority-element-i/?envType=daily-question&envId=2026-06-25
// Difficulty: Medium

// Approach:
// We need to count subarrays where the target element appears more than
// half of the subarray length.
// Brute force checks every subarray, but that takes O(n²).
// Observation:
// Convert the problem into a prefix sum problem.
// Treat:
//      target element  -> +1
//      other elements  -> -1
// Now for any subarray:
//      if sum > 0
//      then target appears more than half times.
// Example:
// [target, 2, target]
// becomes
// [ +1, -1, +1 ]
// Now we need to count subarrays having positive sum.
// Using prefix sum:
// Subarray sum = prefix[right] - prefix[left]
// For the sum to be positive:
//      prefix[right] > prefix[left]
// So the problem becomes:
// For every prefix sum, count how many previous prefix sums are smaller
// than the current prefix sum.
// To do this efficiently:
// 1. Create prefix sum array.
// 2. Prefix sums can be negative, so apply coordinate compression:
//       - sort all prefix values
//       - remove duplicates
//       - assign each value a rank/index
// 3. Use Fenwick Tree (Binary Indexed Tree):
//       Fenwick stores frequency of previous prefix sums.
//       query(rank - 1):
//            gives number of previous prefix sums smaller than current
//       add(rank):
//            stores current prefix sum for future queries
// 4. Traverse prefix sums from left to right:
//       count smaller previous prefix sums
//       add current prefix sum
// The total count gives the number of valid subarrays.

// Time Complexity: O(n log n)
//
//      Coordinate compression  -> O(n log n)
//      Fenwick operations     -> O(n log n)
//
// Space Complexity: O(n)
//
// Key Observation:
//
// Majority condition:
//      count(target) > length / 2
//
// becomes:
//
//      transformed subarray sum > 0
//
// which can be solved using prefix sums and Fenwick Tree.


class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        // Step 1: Convert array into +1 and -1
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {

            if (nums[i] == target) {
                prefix[i + 1] = prefix[i] + 1;
            } else {
                prefix[i + 1] = prefix[i] - 1;
            }
        }

        // Step 2: Coordinate compression
        int[] sorted = prefix.clone();

        Arrays.sort(sorted);

        List<Integer> unique = new ArrayList<>();

        for (int x : sorted) {

            if (unique.size() == 0 ||
                unique.get(unique.size() - 1) != x) {

                unique.add(x);
            }
        }

        // value -> rank mapping
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < unique.size(); i++) {

            map.put(unique.get(i), i + 1);
        }

        // Step 3: Fenwick Tree
        int[] fenwick = new int[unique.size() + 1];
        int ans = 0;

        // Add empty prefix sum = 0
        add(fenwick, map.get(0), 1);

        // Step 4: Process prefix sums
        for (int i = 1; i <= n; i++) {
            int current = prefix[i];
            int rank = map.get(current);

            // count previous prefix sums smaller than current
            ans += query(fenwick, rank - 1);

            // store current prefix
            add(fenwick, rank, 1);
        }

        return ans;
    }

    // Fenwick update
    private void add(int[] fenwick, int index, int value) {
        while (index < fenwick.length) {
            fenwick[index] += value;

            index += index & -index;
        }
    }

    // Fenwick prefix sum query
    private int query(int[] fenwick, int index) {
        int sum = 0;

        while (index > 0) {
            sum += fenwick[index];

            index -= index & -index;
        }

        return sum;
    }
}
