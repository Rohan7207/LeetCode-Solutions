class Solution {
    public long sumAndMultiply(int n) {
        int res = 0;
        int sum = 0;
        int placeValue = 1;

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                sum += digit;
                res += digit * placeValue;
                placeValue *= 10;
            }

            n /= 10;
        }

        return (long) res * sum;
    }
}