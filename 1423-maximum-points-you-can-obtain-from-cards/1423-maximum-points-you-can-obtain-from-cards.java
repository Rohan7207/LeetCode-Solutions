class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int totalSum = 0;
        int n = cardPoints.length;

        for(int point : cardPoints) {
            totalSum += point;
        }

        if(k == n) {
            return totalSum;
        }

        int currSum = 0;
        int left = 0;
        int window = n - k;
        int ans = 0;

        for(int right = 0; right < n; right++) {
            currSum += cardPoints[right];

            if(right - left + 1 > window) {
                currSum -= cardPoints[left];
                left++;
            }

            if(right - left + 1 == window) {
                ans = Math.max(ans, totalSum - currSum);
            }
        }

        return ans;
    }
}