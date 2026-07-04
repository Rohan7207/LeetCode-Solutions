class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        int[] vis = new int[n];

        dfs(0, rooms, vis);

        for (int i = 0; i < n; i++) {
            if (vis[i] == 1)
                continue;
            else
                return false;
        }

        return true;
    }

    private void dfs(int startRoom, List<List<Integer>> rooms, int[] vis) {
        vis[startRoom] = 1;

        for (int nextRoom : rooms.get(startRoom)) {
            if (vis[nextRoom] == 0) {
                dfs(nextRoom, rooms, vis);
            }
        }
    }
}