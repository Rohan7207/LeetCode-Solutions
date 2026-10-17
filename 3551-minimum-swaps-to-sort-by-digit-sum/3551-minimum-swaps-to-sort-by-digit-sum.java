class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> valToIdx = new HashMap<>();

        Integer[] sortedNums = new Integer[n];
        for(int i = 0; i < n; i++) {
            sortedNums[i] = nums[i];
            valToIdx.put(nums[i], i);
        }

        Arrays.sort(sortedNums, (num1, num2) -> {   // nlogn * N (log(maxNum))
            int num1DigitSum = getDigitSum(num1);  // log10(num)
            int num2DigitSum = getDigitSum(num2);

            if(num1DigitSum != num2DigitSum) {
                return Integer.compare(num1DigitSum, num2DigitSum);
            }

            return Integer.compare(num1, num2);
        });

        int swapCount = 0;
        for(int i = 0; i < n; i++) {
            int expectedVal = sortedNums[i];
            int currVal = nums[i];

            // No swap required
            if(expectedVal == currVal) {
                continue;
            }

            int idx = valToIdx.get(expectedVal);

            int temp = currVal;
            nums[i] = expectedVal;
            nums[idx] = temp;

            valToIdx.put(expectedVal, i);
            valToIdx.put(currVal, idx);
            swapCount++;
        }

        return swapCount;
    }

    private int getDigitSum(int num) {
        int sum = 0;

        while(num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}

/*
In constraint nums consists of distinct positive integers.
    nums = {18,43,34,16} where we need to sort based on digitSum which leads to use Custom Sort

    sort(nums, lambda); where lambda is defines how to sort based on digit sum

    auto lambda = [](int num1, int num2) {
        int digitSum1 = getDigitSum(num1);
        int digitSum2 = getDigitSum(num2);

        if(digitSum1 != digitSum2) {
            return digitSum1 < digitSum2;
        }

        return num1 < num2;
    }

    sortedNums = {16, 34, 43, 18}
    nums = {18, 43, 34, 16}

    - We need to swap numbers in nums so that becomes equal to sortedNums, At i = 0, num = 18 but idealy there should be 16 in that pos, so we need to find 16 in nums to swap to 0th position, since given that each element is distinct we will keep nums[i] - i map so that we can search for element and get i

    map => 16 - 3, 34 - 2, 43 -1, 18 - 0
    expected at i = 0 -> 16
    curr at i = 0 -> 18
    Take expected idx from map -> 3
    and swap (0, 3) count = 1 => {16, 43, 34, 18}, In one level we have brought 16 to its correct position we are not sure about 18 but 16 is to expected at 0th pos, and change the index of maps
    16 - 0, 34 - 2, 43 -1, 18 - 3 and do this for other elements.

    Story points:
    1. Custom sort, map val -> idx
    2. i = 0 to n - 1
        expectVal
        currVal
        swap -> count+=
        update map with idx
*/