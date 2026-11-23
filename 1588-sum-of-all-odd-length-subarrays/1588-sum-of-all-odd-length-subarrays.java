class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;

        for(int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int sum = prefix[n];

        for(int i = 0; i < n; i++) {    
            int j = i;

            int odd = 2;
            while(true) {
                int k = j - odd;
                if(k >= 0) {
                    sum += (prefix[j + 1] - prefix[k]);
                    odd += 2;
                } else {
                    break;
                }
            }
        }

        return sum;
    }
}

/*
    Brute force: O(n ^ 2)
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
*/

/*
    1 4 2 5 3

    prefix = 0 1 5 7 12 15


*/