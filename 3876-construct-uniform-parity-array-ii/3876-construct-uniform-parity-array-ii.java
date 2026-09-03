// Problem: Construct Uniform Parity Array II
// Link: https://leetcode.com/problems/construct-uniform-parity-array-ii/?envType=daily-question&envId=2026-09-03
// Difficulty: Medium

 // Approach:
 // Use Minimum Element + Parity Observation.
 //
 // 1. Find the minimum element and check whether all elements are even.
 //
 // 2. If all elements are already even, choose every element unchanged.
 //
 // 3. If the minimum element is odd:
 //      - Keep odd elements unchanged.
 //      - For every even element, subtract the minimum odd element.
 //      - even - odd = odd
 //      - Since the minimum is the smallest element, every even element
 //        is greater than it, so the subtraction is always >= 1.
 //
 // 4. If the minimum is even but an odd element exists:
 //      - The smallest odd element cannot subtract a smaller odd element
 //        to become even.
 //      - Therefore, making everything even is impossible.
 //
 // 5. Hence, the array is possible if:
 //      all elements are even OR minimum element is odd.
 
 // Time Complexity: O(n)
 // Space Complexity: O(1)


class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean allEven = true;

        for (int num : nums1) {
            min = Math.min(min, num);

            if (num % 2 == 1) {
                allEven = false;
            }
        }

        return allEven || min % 2 == 1;
    }
}
