class Solution {
    public boolean checkPerfectNumber(int num) {
        if(num % 2 != 0) return false;
        int ans = 0;

        for(int i = 1; i <= num / 2; i++) {
            if(num % i == 0) {
                ans += i;
            }
        }

        return num == ans;
    }
}