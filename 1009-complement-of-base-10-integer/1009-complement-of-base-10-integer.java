class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }

        int mask = 0;
        int temp = n;

        while (temp > 0) {
            // Move all existing 1s one position left, then add another 1 at the end
            mask = (mask << 1) | 1;
            temp >>= 1; // right shift by 1
        }

        return mask ^ n;
    }
}