class Solution {
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            int sum = 0;

            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }

            min = Math.min(min, sum);
        }

        return min;
    }
}

/*
    My written code :
         int minSum = Integer.MAX_VALUE;
        int sumOfDigit = 0;

        for(int i = 0; i < nums.length; i++) {
            sumOfDigit = sumOfDigits(nums[i]);

            minSum = Math.min(minSum, sumOfDigit);
        }   

        return minSum;
    }

    private int sumOfDigits(int n) {
        int sum = 0;

        while(n != 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
*/