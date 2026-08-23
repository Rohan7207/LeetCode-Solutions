// Problem: Day of the Week
// Link: https://leetcode.com/problems/day-of-the-week/
// Difficulty: Easy

// Approach:
// Use January 1, 1971 (Friday) as the reference date and calculate
// how many days have passed between that date and the given date.
//
// 1. Store the names of the 7 days and the number of days in each month.
//
// 2. Calculate the number of days in all complete years from 1971
//    up to year - 1.
//
//      Normal year → 365 days
//      Leap year   → 366 days
//
// 3. Determine whether the given year is a leap year.
//
// 4. Calculate the number of days in all complete months before
//    the given month.
//
//    If February is in a leap year, add one extra day.
//
// 5. Add `day - 1` because we count the days that have completely
//    passed before reaching the given date.
//
// 6. January 1, 1971 was Friday, whose index is 5 in our `days` array.
//
// 7. Since the week repeats every 7 days:
//
//      (totalDays + 5) % 7
//
//    gives the index of the required day.

// Time Complexity: O(year - 1971 + month) → effectively O(year)
// Space Complexity: O(1)


class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] days = {
                "Sunday", "Monday", "Tuesday",
                "Wednesday", "Thursday", "Friday", "Saturday"
        };

        int[] monthDays = {
                31, 28, 31, 30, 31, 30,
                31, 31, 30, 31, 30, 31
        };

        int totalDays = 0;

        // Complete years
        for (int i = 1971; i < year; i++) {
            if ((i % 400 == 0) || (i % 4 == 0 && i % 100 != 0)) {
                totalDays += 366;
            } else {
                totalDays += 365;
            }
        }

        // Complete months
        boolean leap = (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);

        for (int i = 1; i < month; i++) {
            totalDays += monthDays[i - 1];

            if (i == 2 && leap) {
                totalDays++;
            }
        }

        // Complete days in current month
        totalDays += day - 1;

        // Jan 1, 1971 was Friday (index 5)
        return days[(totalDays + 5) % 7];
    }
}
