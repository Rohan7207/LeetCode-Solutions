class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ans = 0;

        for (int i = 1; i < n - 1; i++) {
            // Check if i is a peak
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                int left = i;
                // Expand to left
                while (left > 0 && arr[left] > arr[left - 1]) {
                    left--;
                }

                int right = i;
                // Expand right
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }

                ans = Math.max(ans, right - left + 1);
            }
        }

        return ans;
    }
}