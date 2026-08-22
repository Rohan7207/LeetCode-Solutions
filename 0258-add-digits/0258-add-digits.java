class Solution {
    public int addDigits(int num) {
        // Use digital root property: repeated digit summation of a positive integer is equivalent to 1 + (num - 1) % 9
        if(num == 0) {
            return 0;
        }

        return 1 + (num - 1) % 9;
    }
}

/*
    Iterative loop:
      while(num >= 10) {
            int sum = 0;

            while(num > 0) {
                sum += num % 10;
                num /= 10;
            }

            num = sum;
        }

        return num;
*/

/*
    Recursive appraoch:
    if(num < 10) {
            return num;
        }

        int sum = 0;

        while(num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return addDigits(sum);
*/