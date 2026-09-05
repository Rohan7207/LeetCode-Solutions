class Solution {
    public int maximum69Number (int num) {
        int len = (num == 0) ? 1 : (int) Math.log10(Math.abs(num)) + 1;
        int[] arr = new int[len];
        int temp = num;
        int idx = len - 1;

        while(num > 0 && idx >= 0) {
            arr[idx--] = num % 10;

            num /= 10;
        }

        boolean flag = false;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 6) {
                arr[i] = 9;
                flag = true;
                break;
            }
        }

        if(!flag) {
            return temp;
        }

        int res = 0;
        for(int digit : arr) {
            res = res * 10 + digit;
        }

        return res;
    }
}