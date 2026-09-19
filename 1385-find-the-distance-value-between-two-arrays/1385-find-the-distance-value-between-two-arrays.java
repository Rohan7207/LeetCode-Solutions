class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;

        for (int i = 0; i < arr1.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr2.length; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}

/*
    Arrays.sort(arr2);
        int count = 0;

        for (int i = 0; i < arr1.length; i++) {
            int nearestElement = helper(arr2, arr1[i], 0, arr2.length - 1);

            if (Math.abs(arr1[i] - nearestElement) > d) {
                count++;
            }
        }

        return count;
    }

    private int helper(int[] arr, int x, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (low == 0) {
            return arr[low];
        } else if (low == arr.length) {
            return arr[low - 1];
        }

        int left = Math.abs(x - arr[low - 1]);
        int right = Math.abs(x - arr[low]);

        if (left < right) {
            return arr[low - 1];
        }

        return arr[low];
    }
*/
/*
    // Use Binary search bcz if nearest point of arr1 in arr2 is <= means it is invalid, if nearest point to arr1 fails means there is no chance to other element.
    // So apply binary search on arr2 to find nearst point to arr1 elements
    // Brute force => O(m * n) and O(1)
*/