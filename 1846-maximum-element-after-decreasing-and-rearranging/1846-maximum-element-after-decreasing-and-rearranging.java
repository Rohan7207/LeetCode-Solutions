class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);

        int m = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > m) {
                m++;
            }
        }

        return m;
    }
}

/*
    Approach in which we replace but in above we will not replace 
    we assign m = 1and increment m iff m > arr[i] so that count every element of arr which is done
    without a arranging
    Arrays.sort(arr);
    arr[0] = 1;

    for (int i = 1; i < arr.length; i++) {
        // int newValue = Math.min(arr[i], arr[i - 1] + 1);

        // arr[i] = newValue;

        if(arr[i] > arr[i - 1] + 1) {
            arr[i] = arr[i - 1] + 1;
        }
    }

    return arr[arr.length - 1];
*/