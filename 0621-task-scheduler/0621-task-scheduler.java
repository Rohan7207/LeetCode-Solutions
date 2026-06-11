// Problem: Task Scheduler
// Link: https://leetcode.com/problems/task-scheduler/
// Difficulty: Medium

// Approach:
// The task with the highest frequency determines
// the minimum size of the schedule.
// Count the frequency of every task.
// Find:
//     maxfreq   -> highest frequency
//     countfreq -> number of tasks having maxfreq
// Think of arranging the most frequent task first.
// Example:
//     A A A
// With cooldown n = 2:
//     A _ _ A _ _ A
// There are:
//     (maxfreq - 1)
// gaps between the most frequent tasks.
// Each gap must have size:
//     (n + 1)
// Therefore minimum slots required are:
//     (maxfreq - 1) * (n + 1)
// If multiple tasks have the same maximum frequency,
// all of them occupy the last row.
// So add:
//     countfreq
// Formula:
//     (maxfreq - 1) * (n + 1) + countfreq
// Sometimes other tasks are sufficient to fill
// all idle slots.
// In that case, the answer cannot be smaller than
// the total number of tasks.
// Therefore:
//     answer = max(tasks.length, formula)

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxfreq = 0;
        int countfreq = 0;

        // freq = {2, 2, 1, 1} maxfreq = 2 countfreq = 2
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        for (int i = 0; i < 26; i++) {
            if (maxfreq < freq[i]) {
                maxfreq = freq[i];
            }
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] == maxfreq) {
                countfreq++;
            }
        }

        int formula = (maxfreq - 1) * (n + 1) + countfreq;
        return Math.max(tasks.length, formula);
    }
}
