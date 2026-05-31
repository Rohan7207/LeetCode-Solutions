class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long currMass = mass;

        for(int i = 0; i < asteroids.length; i++) {
            if(currMass < asteroids[i]) {
                return false;
            }

            currMass += asteroids[i];
        }

        return true;
    }
}