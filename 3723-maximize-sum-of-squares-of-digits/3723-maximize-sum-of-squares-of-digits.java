class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9) {
            return "";
        }

        int nines = sum / 9;
        int rem = sum % 9;
        StringBuilder sb = new StringBuilder();

        sb.repeat('9', nines);

        if (rem > 0) {
            sb.append((char) (rem + '0'));
        }

        int remLength = num - sb.length();
        if (remLength > 0) {
            sb.repeat('0', remLength);
        }

        return sb.toString();
    }
}

/*
    num = 2  sum = 17
    9, 8  = 145
    8, 9  = 145

    num = 3 sum = 22
    9 8 5
    8 9 5 
    9 9 3 = this gives best possible ans bcz 9 gives always highest square

    num = 5 sum = 21
    nines = 21 / 9 = 2, fill 2times 9 at leftmost  9 9 
    rem = 21 % 9 = 3, fill 3 at next and fill 0, 9 9 3 0 0

    Story points:
    1. nines = sum / 9 
    2. rem = sum % 9
    3. append 0 at end
*/