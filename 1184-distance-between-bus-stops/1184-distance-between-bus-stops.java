class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int totalDistance = 0;

        for(int d : distance) {
            totalDistance += d;
        }

        int i = start;
        int clockwiseDistance = 0;
        
        while(i != destination) {
            clockwiseDistance += distance[i];
            i = (i + 1) % distance.length;
        }

        return Math.min(clockwiseDistance, totalDistance - clockwiseDistance);
    }
}