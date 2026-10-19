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