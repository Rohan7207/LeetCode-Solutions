// Problem: Sort Array By Parity II
// Link: https://leetcode.com/problems/sort-array-by-parity-ii/
// Difficulty: Easy

// Approach:
// Since the array contains an equal number of even and odd elements,
// every even index must contain an even number and every odd index
// must contain an odd number.
// Use two pointers:
// • even = 0
//   Points to the next even index that should contain an even number.
// • odd = 1
//   Points to the next odd index that should contain an odd number.
// Traverse the array using these two pointers:
// • If nums[even] is already even,
//   it is correctly placed, so move the even pointer
//   to the next even index.
// • Else if nums[odd] is already odd,
//   it is correctly placed, so move the odd pointer
//   to the next odd index.
// • Otherwise,
//   nums[even] is an odd number placed at an even index,
//   and nums[odd] is an even number placed at an odd index.
//   Swap these two elements to place both in their correct positions,
//   then move both pointers forward.
// Continue until either pointer reaches the end of the array.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        int even = 0;
        int odd = 1;

        while (even < n && odd < n) {
            if (nums[even] % 2 == 0) {
                even += 2;
            } else if (nums[odd] % 2 != 0) {
                odd += 2;
            } else {
                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;
                even += 2;
                odd += 2;
            }
        }

        return nums;
    }
}
