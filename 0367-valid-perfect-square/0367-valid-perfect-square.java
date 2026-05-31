class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;

        int low = 0;
        int high = num;
        int result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid <= num / mid) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (result * result == num) return true;

        return false;
    }
}