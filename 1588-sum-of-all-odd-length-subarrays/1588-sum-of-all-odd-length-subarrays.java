class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int total = (i + 1) * (n - i);
            int oddCount = (total + 1) / 2;

            sum += arr[i] * oddCount;
        }


        return sum;
    }
}

/*
    Can we make it O(n)?
    Yes! There's a mathematical observation.

    Instead of calculating every odd-length subarray separately, ask:

    How many odd-length subarrays contain a particular element?

    For an element at index i:
    - Number of possible starting positions = i + 1
    - Number of possible ending positions = n - i
    Every combination of start and end containing i forms a subarray.

    So total subarrays containing i:
    \[
    (i+1)(n-i)
    \]

    But we only want odd-length subarrays.

    Among these combinations, exactly half (rounded up) have odd length:
    \[
    count=\left\lceil\frac{(i+1)(n-i)}{2}\right\rceil
    \]

    Therefore, the contribution of each element is:
    \[
    arr[i]\times count
    \]

    Dry run with explanation
    1. Example
    arr = [1, 2, 3, 4, 5]


    All odd-length subarrays are:
    Length	Subarrays	Sum
    1	[1], [2], [3], [4], [5]	15
    3	[1,2,3], [2,3,4], [3,4,5]	21
    5	[1,2,3,4,5]	15

    Total:
    \[
    15+21+15=\boxed{51}
    \]

    Instead of calculating these subarrays, let's calculate the contribution of each element.
    2. How many subarrays contain an element?
    Consider:
    Index:  0  1  2  3  4
    Array:  1  2  3  4  5


    Let's take element 3 at index 2.
    Element = 3
    A subarray must start at or before index 2 and end at or after index 2.

    Starting positions:
    0, 1, 2


    There are 3 choices.

    Ending positions:
    2, 3, 4


    There are also 3 choices.

    Therefore:
    \[
    TotalSubarrays=3\times3=9
    \]

    Let's enumerate them:
    Start	End	Subarray	Length
    0	2	[1,2,3]	3
    0	3	[1,2,3,4]	4
    0	4	[1,2,3,4,5]	5
    1	2	[2,3]	2
    1	3	[2,3,4]	3
    1	4	[2,3,4,5]	4
    2	2	[3]	1
    2	3	[3,4]	2
    2	4	[3,4,5]	3

    Out of these 9 subarrays, 6 have odd length.

    So element 3 contributes:
    \[
    3\times6=18
    \]

    3. Why exactly half (rounded up) are odd?
    We know:
    - Starting position can be selected in i + 1 ways.
    - Ending position can be selected in n - i ways.
    So:
    \[
    Total=(i+1)(n-i)
    \]

    Now consider the length:
    \[
    Length=end-start+1
    \]

    For a fixed starting position, as we move the ending position forward, the length alternates:
    1, 2, 3, 4, 5, 6...
    O, E, O, E, O, E...


    Therefore, among any consecutive number of possible ending positions, odd and even lengths occur alternately.

    Across all starting positions, the total number of odd-length subarrays is:
    \[
    OddCount=\left\lceil\frac{Total}{2}\right\rceil
    \]

    For integer arithmetic, ceiling division by 2 is:
    \[
    OddCount=\frac{Total+1}{2}
    \]

    In Java, integer division automatically removes the decimal part.
    4. Dry run of the mathematical code
    int n = arr.length;
    int sum = 0;

    for (int i = 0; i < n; i++) {
        int total = (i + 1) * (n - i);
        int oddCount = (total + 1) / 2;

        sum += arr[i] * oddCount;
    }


    For arr = [1, 2, 3, 4, 5], n = 5:
    i	arr[i]	Left choices (i+1)	Right choices (n-i)	Total	Odd count	Contribution
    0	1	1	5	5	3	3
    1	2	2	4	8	4	8
    2	3	3	3	9	5	15
    3	4	4	2	8	4	16
    4	5	5	1	5	3	15

    Let's calculate each iteration.

    Iteration 1: i = 0
    total = (0 + 1) * (5 - 0);  // 5
    oddCount = (5 + 1) / 2;     // 3
    sum += 1 * 3;               // sum = 3


    Iteration 2: i = 1
    total = (1 + 1) * (5 - 1);  // 8
    oddCount = (8 + 1) / 2;     // 4
    sum += 2 * 4;               // sum = 11


    Iteration 3: i = 2
    total = (2 + 1) * (5 - 2);  // 9
    oddCount = (9 + 1) / 2;     // 5
    sum += 3 * 5;               // sum = 26


    Iteration 4: i = 3
    total = (3 + 1) * (5 - 3);  // 8
    oddCount = (8 + 1) / 2;     // 4
    sum += 4 * 4;               // sum = 42


    Iteration 5: i = 4
    total = (4 + 1) * (5 - 4);  // 5
    oddCount = (5 + 1) / 2;     // 3
    sum += 5 * 3;               // sum = 57


    Final result: 57
    Notice that the earlier direct enumeration gave 51. That means we need to correct the odd-count reasoning: for i = 2, there are actually 5 odd-length subarrays containing 3, not 6, and its contribution is 15. The same method gives contributions 3 + 8 + 15 + 16 + 15 = 57, which is still inconsistent with the direct sum.

    Let's verify the enumeration: the actual total of all odd-length subarrays is:
    - Length 1: 15
    - Length 3: 27 (1+2+3=6, 2+3+4=9, 3+4+5=12)
    - Length 5: 15
    So the correct total is 57. The contribution calculation is correct.
    5. Final understanding
    The biggest shift in thinking is:
    - Prefix sum approach: calculate the sum of every valid subarray.
    - Contribution approach: calculate how many valid subarrays include each element, then multiply by its value.
    This is called the Contribution Technique.
    Complexity
    - Time: O(n), because each element is processed once.
    - Space: O(1), because we only use a few variables.
    Interview One-liner: Count the number of odd-length subarrays containing each element and add its value multiplied by that count.
*/

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

    Another easy version of this above, but still O(n ^ 2) we need based on mathematical formulae
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