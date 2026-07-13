class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;

        int ans = 10; // All 1-digit numbers (0-9)
        int curr = 9; // First digit choices for numbers with length >= 2
        int available = 9; // Remaining choices for the second digit

        for (int digit = 2; digit <= n; digit++) {
            curr *= available;
            ans += curr;
            available--;
        }

        return ans;
    }
}