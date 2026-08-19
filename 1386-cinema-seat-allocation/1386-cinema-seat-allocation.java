// Problem: Cinema Seat Allocation
// Link: https://leetcode.com/problems/cinema-seat-allocation/?envType=daily-question&envId=2026-08-19
// Difficulty: Medium

// Approach:
// Use a HashMap to store only the rows that contain reserved seats.
//
// 1. Create:
//      Map<Integer, Set<Integer>>
//
//    Each row maps to a Set containing its reserved seat numbers.
//
// 2. Rows that are not present in the map have no reservations,
//    so each such row can always accommodate 2 groups.
//
// 3. For every reserved row, check the three possible blocks:
//      - 2,3,4,5
//      - 4,5,6,7
//      - 6,7,8,9
//
// 4. If the left and right blocks are both available, the row can
//    accommodate 2 groups because they don't overlap.
//
// 5. Otherwise, if any one of the three blocks is available,
//    the row can accommodate 1 group.
//
// 6. Finally, add 2 groups for every row that has no reservations.

// Time Complexity: O(R), where R = number of reserved seats,
// because each reserved row checks only a constant number of seats.
//
// Space Complexity: O(R), for storing the reserved seats.


class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reserved = new HashMap<>();

        for (int[] seats : reservedSeats) {
            int row = seats[0];
            int col = seats[1];

            reserved.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }

        int groups = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : reserved.entrySet()) {
            Set<Integer> seats = entry.getValue();

            boolean left = canSit(seats, 2);
            boolean middle = canSit(seats, 4);
            boolean right = canSit(seats, 6);

            if (left && right) {
                groups += 2;
            } else if (left || middle || right) {
                groups++;
            }
        }

        groups += (n - reserved.size()) * 2;

        return groups;
    }

    private boolean canSit(Set<Integer> seats, int start) {
        for (int i = start; i < start + 4; i++) {
            if (seats.contains(i)) {
                return false;
            }
        }
        
        return true;
    }
}
