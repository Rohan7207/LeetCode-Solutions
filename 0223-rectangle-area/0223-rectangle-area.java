class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        int overlapArea = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1)) *
                            Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        return area1 + area2 - overlapArea;
    }
}

/*
    // O(1) and O(1) beats 2ms and 67.98% actually it should be 1ms

    // 1. Calculate individual areas
    int area1 = (ax2 - ax1) * (ay2 - ay1);
    int area2 = (bx2 - bx1) * (by2 - by1);

    // 2. Find overlap boundaries using max/min
    int ix1 = Math.max(ax1, bx1);  // Right-most of the left edges   
    int iy1 = Math.max(ay1, by1);  // Top-most of the bottom edges

    int ix2 = Math.min(ax2, bx2);   // Left-most of the right edges
    int iy2 = Math.min(ay2, by2);   // Bottom-most of the top edges

    // 3. Calculate overlap dimensions
    // If width or height is negative, there is no overlap
    int areaWidth = Math.max(0, ix2 - ix1);
    int areaHeight = Math.max(0, iy2 - iy1);
    int areaIntersection = areaWidth * areaHeight;

    // 4. Return total area (Sum - Overlap)
    int totalArea = area1 + area2 - areaIntersection;

    return totalArea;
*/