class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Nearest Point
        int x0, y0;

        if (x1 > xCenter) {
            x0 = x1;
        } else if (x2 < xCenter) {
            x0 = x2;
        } else {
            x0 = xCenter;
        }

        if (y1 > yCenter) {
            y0 = y1;
        } else if (y2 < yCenter) {
            y0 = y2;
        } else {
            y0 = yCenter;
        }

        // Calculate distance: (x0, y0) -------- (xCenter, yCenter)
        return Math.sqrt(((x0 - xCenter) * (x0 - xCenter)) + ((y0 - yCenter) * (y0 - yCenter))) <= radius;
    }
}

/*
    Whether rectangle is located wherever we only need that point which is nearest point to circle. For example suppose (x0, y0) is that point which closest to circle then we need to calculate distance d between this point and circle and if d <= r then ot overlaps if not it is not overlapping. And if nearest point of rectangle to circle is not <= then there would not exist point which can be <= to radius.

    nearest point of rectangle (x0, y0) and (xc, yc) the distance between them should <= r
    i.e.  if(d <= r) return true  // Overlap
          else return false;    // Not overlap

    Nearest Point:
    Case 1: Rectangle is on right of circle
       - The nearest point on x-axis would be x1 only since rectangle is right side, 
        if(x1 > xc) nearest = x1
       - The nearest point on y-axis would be y1 only since rectangle is right side 
        if(y1 > yc) nearest = y1

    Case 2:  Rectangle is on left of circle
       - The nearest point on x-axis would be x2 only since rectangle is left side 
        if(x2 < xc) nearest = x2
       - The nearest point on y-axis would be y2 only since rectangle is left side 
        if(y2 < yc) nearest = y2

    Case 3:  if above cases fails then definetly nearest = xc (circle) and yc

    if(x1 > xc) {
        nearest = x1;
    } else if(x2 < xc) {
        nearest = x2;
    } else {
        nearest = xc;
    }

    if(y1 > yc) {
        nearest = y1;
    } else if(y2 < yc) {
        nearest = y2;
    } else {
        nearest = yc;
    }

    Now we have (x0, yo) (nearest)
    dist = Math.sqrt((x0 - xc) ^ 2 + (y0 - yc) ^ 2))
    if(dist <= r) true;
    else false;
*/