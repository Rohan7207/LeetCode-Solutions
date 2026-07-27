class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;

        if (n < 3) {
            return false;
        }

        int i = 1;

        while (i < n && arr[i] > arr[i - 1]) {
            i++;
        }

        if (i == 1 || i == n) {
            return false;
        }

        while (i < n && arr[i] < arr[i - 1]) {
            i++;
        }

        return i == n;
    }
}

/*
     boolean flag1 = false;
        boolean flag2 = false;
        int n = arr.length;

        if (n < 3) {
            return false;
        }

        int i = 1;

        while (i < n && arr[i] > arr[i - 1]) {
            i++;
            flag1 = true;
        }

        while (i < n && arr[i] < arr[i - 1]) {
            i++;
            flag2 = true;
        }

        if (i == n && flag1 && flag2) {
            return true;
        }

        return false;
*/

// Time Complexity: O(n)
// Space Complexity: O(1)

// Traverse the array once by first climbing the increasing slope and then descending the decreasing slope.

/*
    🔑 Key Observation

A valid mountain array has exactly one turning point (peak) where the increasing sequence ends and the decreasing sequence begins.

✨ Magic Line / Important Line
while (i < n && arr[i] > arr[i - 1])

Traverses the increasing part.

while (i < n && arr[i] < arr[i - 1])

Traverses the decreasing part.

💡 How We Thought to Derive the Solution
A mountain has only two phases: increasing then decreasing.
Traverse the increasing phase until it stops.
Without resetting the index, continue traversing the decreasing phase.
If both phases exist and the traversal reaches the end, the array is valid.

✅ Why It Works
The first loop ensures the array is strictly increasing before the peak.
The second loop ensures it is strictly decreasing after the peak.
flag1 guarantees there was an increasing section.
flag2 guarantees there was a decreasing section.
i == n confirms every element belongs to one of these two valid phases with no extra increasing/decreasing segments afterward.

🧩 Pattern Recognition
Two Pointers
Single Pass Traversal
Peak Detection

⭐ Interview Importance

⭐⭐⭐☆☆

A common array validation problem that tests identifying and traversing distinct phases in a sequence.

📚 Similar Problems
LeetCode 852 – Peak Index in a Mountain Array
LeetCode 162 – Find Peak Element
LeetCode 941 – Valid Mountain Array
Monotonic Array problems
*/