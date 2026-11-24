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
    gcd(a,b,c)=gcd(gcd(a,b),c)

    *** gcd = findGcd(gcd, nums[j]);

    Exactly the same idea:

        lcm(a,b,c)=lcm(lcm(a,b),c)

        For two numbers:
        lcm(a,b)= (a / gcd(a,b)) * b

       *** lcm = (lcm / findGCD(lcm, nums[j])) * nums[j];
*/