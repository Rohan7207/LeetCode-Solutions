class Solution {
    public int binaryGap(int n) {
        int res = 0;
        boolean found = false;
        int a = 1;
        int pos = -1;

        for (int i = 0; i < 32; i++) {
            if ((a & n) == a) { // If bit is 1 then execute
                if (found) {
                    int d = i - pos;
                    if (d > res)
                        res = d;
                    pos = i;
                } else {
                    pos = i; // Found first set bit 
                    found = true;
                }
            }

            a = a << 1; // Left shift i.e if 0001 after this step 0010
        }

        return res;
    }
}

/*
    Another Solution:
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
*/

/*
Last bit  : n & 1

Remove last bit : n >>= 1

Set bit i     : n | (1 << i)

Clear bit i   : n & ~(1 << i)

Toggle bit i  : n ^ (1 << i)

Check bit i   : (n & (1 << i)) != 0
*/