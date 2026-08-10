class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] rem = new int[60];
        int count = 0;

        for(int i = 0; i < time.length; i++) {
            int r = time[i] % 60;
            int needed = (60 - r) % 60;

            count += rem[needed];

            rem[r]++;
        }

        return count;
    }
}