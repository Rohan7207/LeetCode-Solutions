class Solution {
    public int maxArea(int[] height) {
        // (width) × min(leftHeight, rightHeight)

        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while(left < right) {
            int width = right - left;
            int heightofLine = Math.min(height[left], height[right]);
            int area = width * heightofLine;

            maxArea = Math.max(maxArea, area);

            if(height[left] <= height[right]) left++;
            else right--;
        }

        return maxArea;
    }
}