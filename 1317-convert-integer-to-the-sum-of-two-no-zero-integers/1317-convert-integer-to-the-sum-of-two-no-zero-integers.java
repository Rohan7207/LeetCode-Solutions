class Solution {
    public int[] getNoZeroIntegers(int n) {
        for(int i = 1; i < n; i++) {
            if(!(helper(i) && helper(n - i))) {
                continue;
            }

            if((i + n - i == n)) {
                return new int[] {i, n - i};
            }
        }

        return new int[] {-1, -1};
    }

    private boolean helper(int num) {
        while(num > 0) {
            if(num % 10 == 0) {
                return false;
            }

            num /= 10;
        }

        return true;
    }
}