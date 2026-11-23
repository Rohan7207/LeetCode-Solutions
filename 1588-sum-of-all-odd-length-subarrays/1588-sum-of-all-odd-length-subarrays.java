class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int sum = 0;
        int currSum = 0;

        for(int i = 0; i < n; i++) {    
            currSum = arr[i];
            sum += currSum;

            for(int j = i + 1; j < n; j++) {
                currSum += arr[j];

                if((j - i + 1) % 2 == 1) {
                    sum += currSum;
                }
            }
        }

        return sum;
    }
}

/*
    1 4 2 5 3

    prefix = 1 5 7 12 15


*/