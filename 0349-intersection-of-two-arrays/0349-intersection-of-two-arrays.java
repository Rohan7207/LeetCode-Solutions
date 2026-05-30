// Problem : Intersection of Two Arrays
// Link : https://leetcode.com/problems/intersection-of-two-arrays/
// Difficulty : Easy

// Approach:
// Store all elements of nums1 in a HashSet.
// Traverse nums2:
//     - If current element exists in set,
//       it belongs to the intersection.
//     - Add it to result list.
//     - Remove it from set to avoid duplicates.
// Convert the result list into an array
// and return it.

// Time Complexity: O(n + m)
// Space Complexity: O(n)


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        for (int j = 0; j < nums2.length; j++) {
            if (set.contains(nums2[j])) {
                list.add(nums2[j]);
                set.remove(nums2[j]);
            }
        }

        int[] arr = new int[list.size()];
        int k = 0;
        for (int val : list) {
            arr[k++] = val;
        }
        
        return arr;
    }
}
