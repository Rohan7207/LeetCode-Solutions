class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int sum = 0;
        for (int j = 0; j < n; j++) {
            for (int start = j; start >= 0; start -= 2) {
                sum += (prefix[j + 1] - prefix[start]);
            }
        }

        return sum;
    }
}

/*
    Mine own attempt to make O(n) using prefix sum but it is O(n ^ 2)
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int sum = prefix[n];

        for (int i = 0; i < n; i++) {
            int j = i;

            int odd = 2;
            while (true) {
                int k = j - odd;
                if (k >= 0) {
                    sum += (prefix[j + 1] - prefix[k]);
                    odd += 2;
                } else {
                    break;
                }
            }
        }

        return sum;
    }

    Another easy version of this above is 
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int sum = 0;
        for (int j = 0; j < n; j++) {
            for(int start = j;  start >= 0; start -= 2) {
                sum += (prefix[j + 1] - prefix[start]);
            }
        }   

        return sum;
    }

    Remember this general formula:
\[
sum(i,j)=prefix[j+1]-prefix[i]
\]

Here:
- i = starting index
- j = ending index
Now suppose we fix the ending index j.

For odd lengths:
Length	Starting index	Prefix formula
1	j	prefix[j+1] - prefix[j]
3	j-2	prefix[j+1] - prefix[j-2]
5	j-4	prefix[j+1] - prefix[j-4]
7	j-6	prefix[j+1] - prefix[j-6]

See the pattern?

Starting index:
\[
i=j-(length-1)
\]

Since odd lengths are 1, 3, 5, 7..., we can generate them by subtracting 0, 2, 4, 6... from j.
*/

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