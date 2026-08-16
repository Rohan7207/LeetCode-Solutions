class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        int i = 0;
        int j = 0;

        while(i < n && j < n) {
            if(i < n - 1 && arr[j] == 0) {
                ans[i] = 0;
                i++;

                if(i == n) break;
                ans[i] = 0;
            } else {
                ans[i] = arr[j];
            }

            i++;
            j++;
        }

        System.arraycopy(ans, 0, arr, 0, n);
    }
}