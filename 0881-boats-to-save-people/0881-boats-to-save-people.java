// Problem: Boats to Save People
// Link: https://leetcode.com/problems/boats-to-save-people/
// Difficulty: Medium

// Approach:
// Sort the people array in non-decreasing order.
// Use two pointers:
//     left  -> lightest remaining person
//     right -> heaviest remaining person
// The heaviest person must always be assigned to a boat.
// Check whether the lightest and heaviest people can
// share the same boat.
// If:
//     people[left] + people[right] <= limit
// then both can travel together.
// Move both pointers inward.
// Otherwise, the heaviest person must travel alone
// because they cannot be paired with anyone else.
// Move only the right pointer.
// In every iteration, exactly one boat is used,
// so increment the boat count.
// Continue until all people have been assigned.

// Time Complexity:
//     O(n log n)
//     (Sorting dominates)
// Space Complexity:
//     O(1)
//     (Ignoring the sorting algorithm's internal stack)


class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int boats = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                boats++;
                left++;
                right--;
            } else {
                boats++;
                right--;
            }
        }

        return boats;
    }
}
