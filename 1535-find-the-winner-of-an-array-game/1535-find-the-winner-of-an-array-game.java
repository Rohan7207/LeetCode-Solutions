class Solution {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;
        int max = -1;

        for (int num : arr) {
            max = Math.max(max, num);
        }

        if (k >= n) {
            return max;
        }

        int count = 0;
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (arr[idx] > arr[i]) {
                count++;
            } else if (arr[i] > arr[idx]) {
                idx = i;
                count = 1;
            }

            if (count == k) {
                return arr[idx];
            }
        }

        return max;
    }
}