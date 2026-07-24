class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] one = new boolean[2048];
        boolean[] two = new boolean[2048];
        boolean[] three = new boolean[2048];

        for (int num : nums) {
            one[num] = true;
        }

        for (int xor = 0; xor < 2048; xor++) {
            if (!one[xor]) {
                continue;
            }

            for (int num : nums) {
                two[xor ^ num] = true;
            }
        }

        for (int xor = 0; xor < 2048; xor++) {
            if (!two[xor]) {
                continue;
            }

            for (int num : nums) {
                three[xor ^ num] = true;
            }
        }

        int count = 0;
        for (int xor = 0; xor < 2048; xor++) {
            if (three[xor]) {
                count++;
            }
        }

        return count;
    }
}