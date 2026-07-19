class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        int even = 0;
        int odd = 1;

        while (even < n && odd < n) {
            if (nums[even] % 2 == 0) {
                even += 2;
            } else if (nums[odd] % 2 != 0) {
                odd += 2;
            } else {
                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;
                even += 2;
                odd += 2;
            }
        }

        return nums;
    }
}

/*
    int temp[]=new int[arr.length];
    int even=0;
    int odd=1;

    for(int i=0;i<arr.length;i++){

        if(arr[i]%2==0){
            temp[even]=arr[i];
            even+=2;
        }
        else{
            temp[odd]=arr[i];
            odd+=2;

        }

    }

    return temp;
*/