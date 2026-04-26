class Solution {
    public int maxArea(int[] height) {
        // (width) × min(leftHeight, rightHeight)

        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while(left < right) {
            int width = right - left;
            int heightofLine = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, (heightofLine * width));

            while(left < right && height[left] <= heightofLine) left++;
            while(left < right && height[right] <= heightofLine) right--;
        }

        return maxArea;
    }
}

