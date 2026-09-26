class Solution {
    public int numSteps(String s) {
        int count = 0;
        int carry = 0;

        // Loop from right to left, stopping before the first character (index 0)
        for(int i = s.length() - 1; i > 0; i--) {
            // Calculate the net value of the current position
            int currBit = (s.charAt(i) - '0') + carry;

            if(currBit == 1) {
                // Case 1: Odd number (1)
                // Needs 2 steps: +1 to make it even, then /2 to shift it out.
                count += 2;
                carry = 1;
            } else {
                // Case 2: Even number (0 or 2)
                // Needs 1 step: just /2 to shift it out.
                count += 1;

                // If currentBit was 2, it keeps a carry of 1. If it was 0, carry becomes 0.
                carry = (currBit == 2) ? 1 : 0;
            }
        }

        // Add the final remaining carry to complete the reduction to 1
        return carry + count;
    }
}

/*

    // This would accepted if s was not to long since s may go upto 500 characters it will overflow
    public int numSteps(String s) {
        long num = 0;
        
        for(int i = 0; i < s.length(); i++) {
            // Shift the existing num to the left by 1 bit (multiply by 2)
            num = num * 2;

            // Add the current bit (0 or 1)
            if(s.charAt(i) == '1') {
                num += 1;
            }
        }

        int count = 0;
        while(num > 1) {
            if(num % 2 == 0) {
                num /= 2;
            } else {
                num = num + 1;
            }

            count++;
        }

        return count;
    }
*/