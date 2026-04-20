class Solution {
    public int maxDistance(int[] colors) {
        int maxDist = 0;
        int n = colors.length;

        // Compare with first element
        for(int i = n - 1; i >= 0; i--) {
            if(colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i);
            }
        }

        // Compare with last element
        for(int i = 0; i < n; i++) {
            if(colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, n - 1 - i);
            }
        }
       

        return maxDist;
    }
}

/*
    To maximize distance:

        Compare first element with last different
        Compare last element with first different

    Time = O(n) and Space = O(1)
*/