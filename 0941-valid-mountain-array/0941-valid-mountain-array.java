class Solution {
    public boolean validMountainArray(int[] arr) {
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
    }
}