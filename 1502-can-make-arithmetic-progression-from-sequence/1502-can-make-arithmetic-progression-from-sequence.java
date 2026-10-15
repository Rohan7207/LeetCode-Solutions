class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int diff = Math.abs(arr[0] - arr[1]);

        if(n == 2) {
            return true;
        }

        for(int i = 2; i < n; i++) {
            if(Math.abs(arr[i] - arr[i - 1]) != diff) {
                return false;
            }
        }

        return true;
    }
}