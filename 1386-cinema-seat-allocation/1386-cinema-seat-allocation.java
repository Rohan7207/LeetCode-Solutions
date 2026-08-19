class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reserved = new HashMap<>();

        for(int[] seats : reservedSeats) {
            int row = seats[0];
            int col = seats[1];

            reserved.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }

        int groups = 0;
        for(Map.Entry<Integer, Set<Integer>> entry : reserved.entrySet()) {
            Set<Integer> seats = entry.getValue();

            boolean left = canSit(seats, 2);
            boolean middle = canSit(seats, 4);
            boolean right = canSit(seats, 6);

            if(left && right) {
                groups += 2;
            } else if(left || middle || right) {
                groups++;
            }
        }

        groups += (n - reserved.size()) * 2;

        return groups;
    }   

    private boolean canSit(Set<Integer> seats, int start) {
        for(int i = start; i < start + 4; i++) {
            if(seats.contains(i)) {
                return false;
            }
        }

        return true;
    }
}