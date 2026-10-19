// Problem: Path Crossing
// Link: https://leetcode.com/problems/path-crossing/
// Difficulty: Easy

// Approach:
// 1. Create a HashSet to store all previously visited coordinates.
// 2. Initialize the starting position as (0, 0) and add it to the set.
// 3. Traverse each character in path and update the coordinates:
//    N -> y++, S -> y--, E -> x++, W -> x--.
// 4. Represent the new position as a coordinate pair [x, y].
// 5. Attempt to add the coordinate to the HashSet.
// 6. If set.add() returns false, the position was already visited,
//    meaning the path has crossed itself. Return true.
// 7. If every move visits a new position, return false.

// Time Complexity: O(n), where n is path.length().
// Space Complexity: O(n), for storing visited coordinates.


class Solution {
    public boolean isPathCrossing(String path) {
        Set<List<Integer>> set = new HashSet<>();
        int x = 0, y = 0;
        set.add(List.of(x, y));

        for (int i = 0; i < path.length(); i++) {
            char ch = path.charAt(i);

            if (ch == 'N') {
                y++;
            } else if (ch == 'S') {
                y--;
            } else if (ch == 'E') {
                x++;
            } else {
                x--;
            }

            if (!set.add(List.of(x, y))) {
                return true;
            }
        }

        return false;
    }
}
