// Problem: Angle Between Hands of a Clock
// Link: https://leetcode.com/problems/angle-between-hands-of-a-clock/?envType=daily-question&envId=2026-06-18
// Difficulty: Medium

// Approach:
// We need to find the smaller angle between
// the hour hand and minute hand of a clock.
// First calculate the position of both hands
// from 12 o'clock in degrees.
// Minute Hand:
//     Clock has 60 minutes = 360 degrees
//     So 1 minute = 360 / 60 = 6 degrees.
//     minuteAngle = minutes * 6
// Hour Hand:
//     Clock has 12 hour positions = 360 degrees
//     So 1 hour = 360 / 12 = 30 degrees.
//     But hour hand also moves every minute.
//     In 60 minutes, it moves 30 degrees.
//     So in 1 minute, it moves:
//         30 / 60 = 0.5 degrees
//     hourAngle = (hour % 12) * 30 + minutes * 0.5
// Find absolute difference:
//     diff = abs(hourAngle - minuteAngle)
// There are always two angles between hands:
//     diff
//     360 - diff
// Return the smaller one.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public double angleClock(int hour, int minutes) {
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;
        double minuteAngle = minutes * 6;

        double diff = Math.abs(hourAngle - minuteAngle);

        return Math.min(diff, 360 - diff);
    }
}
