// Problem: Number of Days Between Two Dates
// Link: https://leetcode.com/problems/number-of-days-between-two-dates/
// Difficulty: Easy

// Approach:
// Convert each date into a single integer representing its position
// in the timeline starting from the year 1971.
//
// 1. Extract year, month, and day from the date string.
//
// 2. Start with the current day:
//
//      res = d
//
// 3. Add the number of days in all complete years from 1971
//    up to year - 1.
//
//      Normal year → 365
//      Leap year   → 366
//
// 4. Determine whether the current year is a leap year.
//
// 5. Add the number of days in all complete months before
//    the current month.
//
//    If February is in a leap year, add one extra day.
//
// 6. Now `res` represents the date's position relative to 1971.
//
// 7. Convert both dates using `countDays()` and take their
//    absolute difference.
//
//      |countDays(date1) - countDays(date2)|

// Time Complexity: O(year difference + month) → O(1) for given constraints
// Space Complexity: O(1)


class Solution {
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(countDays(date1) - countDays(date2));
    }

    private int countDays(String date) {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(5, 7));
        int d = Integer.parseInt(date.substring(8, 10));

        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int res = d;

        for (int i = 1971; i < y; i++) {
            if ((i % 400 == 0) || (i % 4 == 0 && i % 100 != 0)) {
                res += 366;
            } else {
                res += 365;
            }
        }

        boolean leap = (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);

        for (int i = 1; i < m; i++) {
            res += days[i - 1];

            if (i == 2 && leap) {
                res++;
            }
        }

        return res;
    }
}
