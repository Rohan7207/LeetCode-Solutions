// Problem: Number of Students Doing Homework at a Given Time
// Link: https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/
// Difficulty: Easy

// Approach:
// 1. Traverse every student's start and end time.
// 2. Check whether queryTime lies inside [startTime[i], endTime[i]].
// 3. If startTime[i] <= queryTime <= endTime[i], increment count.
// 4. Return the total count.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;

        for (int i = 0; i < startTime.length; i++) {
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
                count++;
            }
        }

        return count;
    }
}
