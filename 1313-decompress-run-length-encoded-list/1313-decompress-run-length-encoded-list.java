class Solution {
    public int[] decompressRLElist(int[] nums) {
        int size = 0;
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            size += nums[2 * i];
        }

        int[] ans = new int[size];
        int idx = 0;
        for (int i = 0; i < n / 2; i++) {
            int freq = nums[2 * i];
            int val = nums[2 * i + 1];

            for (int j = 0; j < freq; j++) {
                ans[idx++] = val;
            }
        }

        return ans;
    }
}

/*
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < nums.length; i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];

            for(int j = 0; j < freq; j++) {
                list.add(val);
            }
        }

        int len = list.size();
        int[] ans = new int[len];
        for(int i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
*/