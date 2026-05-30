// Problem : Intersection of Two Arrays II
// Link : https://leetcode.com/problems/intersection-of-two-arrays-ii/
// Difficulty : Easy

// Approach:
// Store frequency of nums1 elements
// in a HashMap.
// Traverse nums2:
//     - If current number exists in map
//       and frequency > 0,
//       add it to answer.
//     - Decrease frequency.
// This ensures duplicates are counted
// exactly as many times as they appear
// in both arrays.

// Time Complexity: O(n + m)
// Space Complexity: O(min(n,m))


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
