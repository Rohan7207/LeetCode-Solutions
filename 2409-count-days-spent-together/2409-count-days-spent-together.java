class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // We need to find a day of each date in year
        int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // To find day of particular month store in prefix sum, prefix[8 - 1] + date;
        int[] prefixDays = new int[13];
        prefixDays[1] = 31;
        for (int i = 2; i < 13; i++) {
            prefixDays[i] = prefixDays[i - 1] + months[i - 1];
        }

        int arriveAliceDays = calculateDays(arriveAlice, prefixDays);
        int leaveAliceDays = calculateDays(leaveAlice, prefixDays);
        int arriveBobDays = calculateDays(arriveBob, prefixDays);
        int leaveBobDays = calculateDays(leaveBob, prefixDays);

        int overlapDays = Math.min(leaveAliceDays, leaveBobDays) - Math.max(arriveAliceDays, arriveBobDays) + 1;

        return overlapDays < 0 ? 0 : overlapDays;
    }

    private int calculateDays(String date, int[] prefixDays) {
        int month = Integer.parseInt(date.substring(0, 2));
        int days = Integer.parseInt(date.substring(3));

        return prefixDays[month - 1] + days;
    }
}

/*
    Alice = "08-15"  and "08-18"
    Bob = "08-16" and "08-19"

    - If we represent date in days in particular day then we can find no.of days when they were together, to find no.of days we can keep prefix sum of months and calculate for current,
    as  prefix[month - 1] + date

    08-15  = 227th day    and 08-18  = 230th day
    08-16  = 228th day  and   08-19  = 231th day

    - To find when they were together like,
    start day = max(alicestart, bobstart)  = 228th 
    end day = min(aliceleave, bobleave) = 230

    days = (230 - 228) + 1 = 3days
*/