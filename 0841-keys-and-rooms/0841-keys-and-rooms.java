// Problem : Keys and Rooms
// Link : https://leetcode.com/problems/keys-and-rooms/
// Difficulty : Medium

// Approach:
// The given input is already an adjacency list where:
//      rooms[i] = list of rooms whose keys are present in room i.
// Since room 0 is initially unlocked, start a DFS from room 0.
// During DFS:
//     1. Mark the current room as visited.
//     2. Traverse all keys available in the current room.
//     3. If a key leads to an unvisited room,
//        recursively visit that room.
// After DFS completes,
// check whether every room has been visited.
// If all rooms are visited, return true.
// Otherwise, return false.

// Time Complexity: O(V + E)
//      V = Number of rooms
//      E = Total number of keys
//
// Space Complexity: O(V)
//      Visited array + DFS recursion stack


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
