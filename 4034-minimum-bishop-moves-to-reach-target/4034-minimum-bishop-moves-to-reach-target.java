class Solution {
    public int minBishopMoves(int[] source, int[] target) {
        //If there is differenct colour parity then bishop cannot reach target
        if ((source[0] + source[1]) % 2 != (target[0] + target[1]) % 2) {
            return -1;
        }

        // Both source and target are in same diagonal then in one move we can reach target
        if (Math.abs(source[0] - target[0]) == Math.abs(source[1] - target[1])) {
            return 1;
        }

        return 2;
    }
}