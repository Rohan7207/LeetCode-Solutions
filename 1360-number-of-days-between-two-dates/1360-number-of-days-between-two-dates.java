class Solution {
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(totalDays(date1) - totalDays(date2));
    }

    private int totalDays(String date) {
        int y = Integer.parseInt(date.substring(0, 4)); 
        int m = Integer.parseInt(date.substring(5, 7)); 
        int d = Integer.parseInt(date.substring(8, 10)); 
        
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int res = d;

        for(int i = 1971; i < y; i++) {
            if((i % 400 == 0) || (i % 4 == 0 && i % 100 != 0)) { 
                res += 366;
            } else {
                res += 365;
            }
        }

        boolean leap = (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);

        for(int i = 1; i < m; i++) {
            res += days[i - 1];

            if(i == 2 && leap) {
                res++;
            }
        }

        return res;
    }
}