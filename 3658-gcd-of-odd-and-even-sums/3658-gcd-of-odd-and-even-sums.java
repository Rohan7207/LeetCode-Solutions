class Solution {
    public int gcdOfOddEvenSums(int n) {
        int oddSum = n * n;
        int evenSum = n * (n + 1);

        return gcd(oddSum, evenSum);
    }

    private int gcd(int a, int b) {
        while(b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}