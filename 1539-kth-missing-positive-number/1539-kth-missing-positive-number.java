class Solution {
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int idx = 0;
        int counter = 1;

        while(k > 0) {
            if(idx < n && arr[idx] == counter) {
                idx++;
            } else {
                k--;
            }

            counter++;
        }

        return counter - 1;
    }
}