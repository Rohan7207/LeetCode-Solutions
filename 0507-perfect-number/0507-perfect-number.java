class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num % 2 != 0) return false;
        int end = num / 2;
        int sum = end;

        for (int i = 1; i < end; i++) {
            if (num % i == 0) sum += i;
            if (sum > num) return false;
        }

        if (sum == num) return true;
        return false;
    }
}