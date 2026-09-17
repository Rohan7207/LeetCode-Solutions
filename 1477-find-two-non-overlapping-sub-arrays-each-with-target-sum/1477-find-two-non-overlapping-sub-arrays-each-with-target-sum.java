class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLenTillIdx = new int[n];

        for (int i = 0; i < n; i++) {
            minLenTillIdx[i] = Integer.MAX_VALUE;
        }

        int i = 0, j = 0;
        int currSum = 0;
        int bestMin = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        while (j < n) {
            currSum += arr[j];

            while (i < j && currSum > target) {
                currSum -= arr[i];
                i++;
            }

            if (currSum == target) {
                int len = j - i + 1;

                if (i > 0 && minLenTillIdx[i - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, len + minLenTillIdx[i - 1]);
                }

                bestMin = Math.min(bestMin, len);
            }

            minLenTillIdx[j] = bestMin;
            j++;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

/*
    arr = {7, 3, 4, 7}
    We need to store track of minimum-before, It would work for normal problem,
    Since we haave give non-overlapping
    {1, 6, 1}
    currSum = 1 + 6 = 7 len = 2
    minBefore = 2
    - Now in next slide 6 + 1 leads to over-lapping subarray so previous approach fails
    - So at this window we need to know how many subarrays are present before i i.e. 6 so we maintain a 1d array, minLenTillIdx[idx] = min length of subarray with sum == target
    So we need minLenTillIdx[i - 1] which stores miimum length of subarray before i, if it is not present we will store MAX_VALUE.

    {7, 3, 4, 7}

    currSum = 7
    len = 1
    minLenTillIdx = {}

    while(j < n) {  // O(2*n) and s.c = O(n)
        currSum += arr[j];

        while(currSum > target) {
            currSum -= arr[i];
            i++;
        }

        if(currSum == target) {
            len = j - i + 1;
            if(i > 0 && minLenTillIdx[i - 1] != MAX) {
                res = min(res, len + minLenTillIdx[i - 1])
            }

            bestMin = min(bestMin, len);
        } 

        minLenTillIdx[j] = bestMin;
        j++;
    }

    
*/