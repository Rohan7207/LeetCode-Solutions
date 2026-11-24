class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];

                gcd = findGcd((int) gcd, nums[j]);

                lcm = (lcm / findGcd((int) lcm, nums[j])) * nums[j];

                if (prod == lcm * gcd) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }

    private long findGcd(int a, int b) {
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}

/*
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int[] seen = new int[11];

        Arrays.fill(seen, -1);

        int left = 0;
        int maxLen = 1;

        for (int right = 0; right < n; right++) {
            int num = nums[right];

            // Find distinct prime factors
            for (int p = 2; p * p <= num; p++) {
                if (num % p == 0) {

                    // Move left past the previous occurrence
                    left = Math.max(left, seen[p] + 1);

                    // Remove repeated occurrences of p
                    while (num % p == 0) {
                        num /= p;
                    }
                }
            }

            // Remaining number is also a prime factor
            if (num > 1) {
                left = Math.max(left, seen[num] + 1);
            }

            // Update last-seen indices
            num = nums[right];

            for (int p = 2; p * p <= num; p++) {
                if (num % p == 0) {
                    seen[p] = right;

                    while (num % p == 0) {
                        num /= p;
                    }
                }
            }

            if (num > 1) {
                seen[num] = right;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
*/

/*
    gcd(a,b,c)=gcd(gcd(a,b),c)

    *** gcd = findGcd(gcd, nums[j]);

    Exactly the same idea:

        lcm(a,b,c)=lcm(lcm(a,b),c)

        For two numbers:
        lcm(a,b)= (a / gcd(a,b)) * b

       *** lcm = (lcm / findGCD(lcm, nums[j])) * nums[j];
*/