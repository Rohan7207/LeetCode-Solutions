class Solution {
    public boolean check(int[] nums) {
        int breaks = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if(nums[i] > nums[(i + 1) % n]) {
                breaks++;
            }
        }

        return breaks <= 1;
    }
}

/*
    Given an array nums, determine if it can be formed by taking an originally non-decreasing sorted array and rotating it some number of positions.

Instead of checking every possible rotation explicitly (which takes O(n²) time), we examine the array layout circularly. If an array is sorted and rotated, it represents a single climbing sequence that wraps around. This means there can be at most ONE single breakpoint across the entire circular sequence where an element is strictly greater than its successor.

Underlying Concept
if nums[i] > nums[(i + 1) % n]: count += 1
Check if the current element is greater than the next element using circular modulo indexing. If true, an inversion break is logged.
return count <= 1
After completing a full circular walk, the array is valid only if it contains 0 breaks (already sorted) or exactly 1 break (properly rotated).
1
Circular Array Property.
Using the modulo operation `(i + 1) % n` connects the last element back to the very first element, turning the linear array segment into an endless circle.
2
The Inversion Cap Rule.
A completely sorted non-decreasing sequence has 0 drops. Rotating it creates exactly 1 drop point where the largest element wraps around to meet the smallest element. Therefore, any valid state must satisfy `count <= 1` drops.
3
Handling Duplicates safely.
By checking strict inequality (`nums[i] > nums[nxt]`), flat plateaus formed by identical adjacent elements are treated as valid sorting progressions without triggering false positive breakpoints.


This part:

(i + 1) % n
connects:

last element → first element
So array behaves like a circle.

Example:

i = 4
(4 + 1) % 5 = 0
Meaning:

nums[4] compares with nums[0]
*/