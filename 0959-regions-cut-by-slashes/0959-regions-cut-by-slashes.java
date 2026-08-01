class Solution {

    private static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int regionsBySlashes(String[] grid) {
        int gridSize = grid.length;

        int[][] expandedGrid = new int[gridSize * 3][gridSize * 3];

        for(int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++) {
                int baseRow = i * 3;
                int baseCol = j * 3;

                if(grid[i].charAt(j) == '\\') {
                    expandedGrid[baseRow][baseCol] = 1;
                    expandedGrid[baseRow + 1][baseCol + 1] = 1;
                    expandedGrid[baseRow + 2][baseCol + 2] = 1;
                } else if(grid[i].charAt(j) == '/') {
                    expandedGrid[baseRow + 2][baseCol] = 1;
                    expandedGrid[baseRow + 1][baseCol + 1] = 1;
                    expandedGrid[baseRow][baseCol + 2] = 1;
                }
            }
        }

        int regionCount = 0;

        for(int i = 0; i < gridSize * 3; i++) {
            for(int j = 0; j < gridSize * 3; j++) {
                if(expandedGrid[i][j] == 0) {
                    floodFill(expandedGrid, i, j);

                    regionCount++;
                }
            }
        }

        return regionCount;
    }

    private void floodFill(int[][] expandedGrid, int row, int col) {
        int n = expandedGrid.length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { row, col });

        expandedGrid[row][col] = 1;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int[] dir : dirs) {
                int newRow = curr[0] + dir[0];
                int newCol = curr[1] + dir[1];

                if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && expandedGrid[newRow][newCol] == 0) {
                    expandedGrid[newRow][newCol] = 1;
                    q.offer(new int[] { newRow, newCol });
                }
            }
        }
    }
}