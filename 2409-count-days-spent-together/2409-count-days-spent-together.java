// Problem: Count Days Spent Together
// Link: https://leetcode.com/problems/count-days-spent-together/?envType=daily-question&envId=2026-10-24
// Difficulty: Easy

// Approach:
//
// 1. Convert every date from "MM-DD" format into a day number
//    representing its position in a non-leap year.
//
// 2. Create a prefixDays array where prefixDays[m] stores the
//    total number of days before month m + 1, based on the
//    indexing convention used in the implementation.
//
// 3. For each date, extract the month and day using substring()
//    and parse them into integers.
//
// 4. Calculate the day number using:
//
//       prefixDays[month - 1] + day
//
//    This gives a comparable integer for every date.
//
// 5. Find the beginning of the overlapping interval using the
//    later arrival date:
//
//       overlapStart = max(arriveAliceDays, arriveBobDays)
//
// 6. Find the end of the overlapping interval using the earlier
//    departure date:
//
//       overlapEnd = min(leaveAliceDays, leaveBobDays)
//
// 7. Since both arrival and departure dates are inclusive,
//    calculate:
//
//       overlapDays = overlapEnd - overlapStart + 1
//
// 8. If overlapDays is negative, there is no overlap, so return
//    0. Otherwise, return overlapDays.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

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
