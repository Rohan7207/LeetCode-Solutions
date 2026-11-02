class Solution {

    private Random random = new Random();

    public int minMoves2(int[] nums) {
        int n = nums.length;

        // Find middle element using QuickSelect Algorithm in O(n)
        int mid = quickSelect(nums, 0, n - 1, n / 2);
        int moves = 0;

        for (int num : nums) {
            moves += Math.abs(num - mid);
        }

        return moves;
    }

    private int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = randomPartition(arr, left, right);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private int randomPartition(int[] arr, int left, int right) {
        int pivotIdx = left + random.nextInt(right - left + 1);
        swap(arr, pivotIdx, right);

        return partition(arr, left, right);
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/*
    My solution with sorting = O(nlogn) above uses O(n) by finding median
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int midEle = nums[n / 2];
        int moves = 0;

        for (int num : nums) {
            moves += Math.abs(num - midEle);
        }

        // Above for loop can be optimized as, O(n / 2)
        for(int i = 0; i < n / 2; i++) {
            moves += Math.abs(nums[i] - midEle);
            moves += Math.abs(nums[n - i - 1] - midEle);  // right elements
        }
        
        return moves;
    }
*/

/*
    Optimal Approach: O(n) 

        {3, 1, 2}
     - We only need to know mid element, so we need to sort full array we only need the n / 2th element, i.e. when the array would be sorted what is n / 2th element which can be removed using help of 
      ** nth_element approach, which tells us that if array would be sorted what is mid element 

      nums = {7, 5, 1, 3, 2}  n = 5    => we need to find what is n / 2 = 2nd idx element when array is sorted
      -If sorted - {1, 2, 3, 5, 7}

      - The nth element = The nth_element ensures that n / 2th element is at correct position and remaoning less than will be before and greater after it not necessary sorted i.e.
          nth_element = {2, 1, 3, 7, 5}

        - There is direct method nth_element(begin(nums), begin(nums) + n / 2, end(nums)); => Average=O(n)
          - int mid = nums[n / 2];
          - for(i = 0; i < n / 2; i++) {
                moves += Math.abs(nums[i] - mid);
                moves += Math.abs(nums[n - i - 1] - mid);
            }
*/