class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        int count = 0;
        boolean[] vis = new boolean[n];

        dfs(0, rooms, vis);

        for (boolean visRoom : vis) {
            if (visRoom)
                count++;
        }

        return count == n;
    }

    private void dfs(int startRoom, List<List<Integer>> rooms, boolean[] vis) {
        vis[startRoom] = true;

        for (int nextRoom : rooms.get(startRoom)) {
            if (!vis[nextRoom]) {
                dfs(nextRoom, rooms, vis);
            }
        }
    }
}