class Solution {
    public int binaryGap(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(n & 1);
            n >>= 1;
        }

        String binary = sb.reverse().toString();

        int currPos = 0;
        int dist = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < binary.length(); i++) {
            char bit = binary.charAt(i);

            if (bit == '0') {
                continue;
            } else {
                dist = i - currPos;
                currPos = i;
            }

            max = Math.max(max, dist);
        }

        return max;
    }
}

/*
Last bit  : n & 1

Remove last bit : n >>= 1

Set bit i     : n | (1 << i)

Clear bit i   : n & ~(1 << i)

Toggle bit i  : n ^ (1 << i)

Check bit i   : (n & (1 << i)) != 0
*/