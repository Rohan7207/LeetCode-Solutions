class Solution {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;

        int count = 0;
        int val = arr[0];
        for (int i = 1; i < n; i++) {
            if (val > arr[i]) {
                count++;
            } else {
                val = arr[i];
                count = 1;
            }

            if (count == k) {
                return val;
            }
        }

        return val;
    }
}