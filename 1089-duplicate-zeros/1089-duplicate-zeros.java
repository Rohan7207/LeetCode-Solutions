class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        int i = 0;
        int j = 0;

        while(i < n && j < n) {
            if(i < n - 1 && arr[j] == 0) {
                ans[i] = 0;
                ans[i + 1] = 0;
                i += 2;
                j++;
            } else {
                ans[i++] = arr[j++];
            }
        }

        for(i = 0; i < n; i++) {
            arr[i] = ans[i];
        }
    }
}