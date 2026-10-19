// Problem: Average Salary Excluding the Minimum and Maximum Salary
// Link: https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
// Difficulty: Easy

// Approach:
// 1. Initialize max to Integer.MIN_VALUE and min to Integer.MAX_VALUE.
// 2. Traverse the salary array to find the maximum and minimum salaries.
// 3. Initialize sum and count to 0.
// 4. Traverse the array again, skipping salaries equal to max or min.
// 5. Add each remaining salary to sum and increment count.
// 6. Divide sum by (double) count to calculate the average with
//    floating-point precision.
// 7. Return the calculated average.

// Time Complexity: O(n), where n is salary.length.
// Space Complexity: O(1).


class Solution {
    public double average(int[] salary) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int val : salary) {
            max = Math.max(max, val);
            min = Math.min(min, val);
        }

        int count = 0;
        int sum = 0;
        for (int i = 0; i < salary.length; i++) {
            int value = salary[i];
            if (value == max || value == min) {
                continue;
            }

            sum += value;
            count++;
        }

        return sum / (double) count;
    }
}
