class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int window = n - k;
        int windowSum = 0;
        int minWindowSum = 0;
        int totalSum = 0;

        for (int num : cardPoints) {
            totalSum += num;
        }

        if (k == n)
            return totalSum;

        for (int i = 0; i < window; i++) {
            windowSum += cardPoints[i];
        }

        minWindowSum = windowSum;

        for (int i = window; i < n; i++) {
            windowSum += cardPoints[i] - cardPoints[i - window];

            minWindowSum = Math.min(minWindowSum, windowSum);
        }

        return totalSum - minWindowSum;
    }
}

/*
    It is also but instead we can maintain minimum window in array and then substract from totalSum 
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
*/