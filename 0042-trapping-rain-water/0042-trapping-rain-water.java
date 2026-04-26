class Solution {
    public int trap(int[] height) {
        //min(leftmax, rightmax) - height[i]

        int n = height.length;
        int left = 0, right = n - 1;
        int lmax = 0, rmax = 0;
        int ans = 0;

        while (left < right) {
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);

            if (lmax < rmax) {
                ans += lmax - height[left];
                left++;
            } else {
                ans += rmax - height[right];
                right--;
            }
        }

        return ans;
    }
}