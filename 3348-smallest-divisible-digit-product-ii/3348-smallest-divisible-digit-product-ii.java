class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;

        // Step 1: Check if t can be formed using digits (2 to 9).
        // If any prime factor greater than 9 remains, answer is impossible.
        for(int i = 2; i <= 9; i++) {
            while(temp % i == 0) {
                temp /= i;
            }
        }

        if(temp > 1) return "-1";

        int n = num.length();

        // rem[i] = Remaining factor of t that still needs to be satisfied
        // after processing the first i digits.
        long[] rem = new long[n + 1];
        rem[0] = t;

        // Position from where modification should begin.
        // If a '0' is encountered, we cannot keep it,
        // so modifications start from there.
        int pos = n - 1;

        char[] numChars = num.toCharArray();

        // Compute remaining factor needed after every prefix.
        for(int i = 0; i < n; i++) {
            // Digits cannot be zero in the final answer.
            if(numChars[i] == '0') {
                pos = i;
                break;
            }

            // Remove from the remaining requirement whatever
            // factor this digit already contributes.
            rem[i + 1] = rem[i] / gcd(rem[i], numChars[i] - '0');
        }

        // Entire number already satisfies the condition.
        if(rem[n] == 1) {
            return num;
        }

        // Try modifying digits from right to left
        // so that the resulting number stays as small as possible.
        for(int i = pos; i >= 0; i--) {
            // Try increasing the current digit.
            while(++numChars[i] <= '9') {
                // Remaining factor needed after choosing this digit.
                long tNow = rem[i] / gcd(rem[i], numChars[i] - '0');

                // Greedily fill the suffix using largest digits
                // that divide the remaining factor.
                int k = 9;

                for(int j = n - 1; j > i; j--) {
                    while(tNow % k != 0) {
                        k--;
                    }

                    tNow /= k;
                    numChars[j] = (char) ('0' + k);
                }

                // Successfully satisfied all factors.
                if(tNow == 1) {
                    return new String(numChars);
                }
            }
        }

        // Same length is impossible.
        // Build the smallest longer valid number.
        StringBuilder ans = new StringBuilder();
        long originalT = t;

        // Factorize t using digits 9 to 2.
        for(int i = 9; i > 1; i--) {
            while(originalT % i == 0) {
                ans.append((char) ('0' + i));
                originalT /= i;
            }
        }

        // Pad with leading 1's if needed to make
        // the number longer than the original.
        int padding = Math.max(n + 1 - ans.length(), 0);
        for (int i = 0; i < padding; i++) {
            ans.append('1');
        }

        // Digits were collected in reverse order,
        // so reverse to obtain the smallest number.
        return ans.reverse().toString();
    }

    // Euclidean algorithm for GCD.
    // Used to determine how much of the remaining factor
    // is already covered by the current digit.
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}