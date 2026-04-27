class Solution {
    private int count = 0;

    public int totalNQueens(int n) {
        //Track the column and both types of diagonal
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n]; //Anti diagonal [/] (r + c)
        boolean[] d2 = new boolean[2 * n]; //Main diagonal [\] (r - c + n)

        backtrack(0, n, cols, d1, d2);
        return count;
    }

    private void backtrack(int row, int n, boolean[] cols, boolean[] d1, boolean[] d2) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int id1 = row + col;
            int id2 = row - col + n;

            if (cols[col] || d1[id1] || d2[id2]) {
                continue;
            }

            //Place Queen 
            cols[col] = d1[id1] = d2[id2] = true;

            //Move to next row
            backtrack(row + 1, n, cols, d1, d2);

            //Remove Queen(Bactrack and reset)
            cols[col] = d1[id1] = d2[id2] = false;
        }
    }
}

/*
    int count=0;
    int[] arr;
    public boolean issafe(int row,int col){
        for(int i=0;i<row;i++){
            if(arr[i]==col || Math.abs(row-i)==Math.abs(col-arr[i]))
                return false;
        }
        return true;
    }

    public void helper(int row,int n){
        if(row==n){
            count++;
            return;
        }
        for(int col=0;col<n;col++){
            if(issafe(row,col)){
                arr[row]=col;
                helper(row+1,n);
            }
        }
    }
*/