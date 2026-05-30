class Solution {
    public int lengthOfLIS(int[] nums) {
        //Solution with Patient Sort Algorithm with O(n*logn)
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sub.get(sub.size() - 1)) {
                sub.add(nums[i]);
            } else {
                int j = binarySearch(sub, nums[i]);
                sub.set(j, nums[i]);
            }
        }

        return sub.size();
    }

    private int binarySearch(ArrayList<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sub.get(mid) == num) {
                return mid;
            } else if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}

/*
    //Solution with O(n^2)
        int[] lis = new int[nums.length];
        Arrays.fill(lis, 1);
        int max = 1;

        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    lis[i] = Math.max(lis[i], 1 + lis[j]); //Recurence relation
                    max = Math.max(max, lis[i]);
                }
            }
        }

        return max;
*/