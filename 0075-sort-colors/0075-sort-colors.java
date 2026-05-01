class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int mid = 0, low = 0, high = n - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low++] = nums[mid];
                nums[mid++] = temp;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[high];
                nums[high--] = nums[mid];
                nums[mid] = temp;
            }
        }
    }
}

/*
    Dutch National Flag Algorithm 
    0's = 0 to low - 1
    1's = low to mid - 1
    2's = high + 1 to n - 1
    unsorted ele = mid to high 
*/