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

            if(i == 2 && leap) {
                totalDays++;
            }
        }

        // Complete days in current month
        totalDays += day - 1;

        // Jan 1, 1971 was Friday (index 5)
        return days[(totalDays + 5) % 7];
    }
}