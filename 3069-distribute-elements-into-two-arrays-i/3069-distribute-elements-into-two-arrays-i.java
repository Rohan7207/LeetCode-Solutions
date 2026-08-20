class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int last1 = 0;
        int last2 = 0;

        for(int i = 2; i < n; i++) {
            if(arr1[last1] > arr2[last2]) {
                last1++;
                arr1[last1] = nums[i];
            } else {
                last2++;
                arr2[last2] = nums[i];
            }
        }

        int[] res = new int[n];

        for(int i = 0; i <= last1; i++) {
            res[i] = arr1[i];
        }

        for(int i = 0; i <= last2; i++) {
            res[last1 + 1 + i] = arr2[i];
        }

        return res;
    }
}