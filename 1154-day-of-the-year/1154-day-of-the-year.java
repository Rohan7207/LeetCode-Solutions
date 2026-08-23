class Solution {
    public int dayOfYear(String date) {
        int[] monthDays = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        boolean leap = (year % 400 == 0) ||
                   (year % 4 == 0 && year % 100 != 0);

        int totalDays = 0;
        for(int i = 1; i < month; i++) {
            totalDays += monthDays[i - 1];

            if(i == 2 && leap) {
                totalDays++;
            }
        }

        totalDays += day;

        return totalDays;
    }
}