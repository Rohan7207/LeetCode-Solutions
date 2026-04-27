class Solution {
    public List<List<String>> solveNQueens(int n) {
        /*Use backtracking
        and base case is at any point if we can place queen in last column then we 
        can add it list or else we backtrack till we get a order 
        in recursion we increase the row and col and add position of queen in curr
        
        Time=O(n*2*n^2) which much greater so we have n=2 to 9
        */

        char[][] board = new char[n][n];
        //Construct the board filled with . 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> res = new ArrayList<>();
        backtrack(board, 0, res);
        return res;
    }

    private void backtrack(char[][] board, int col, List<List<String>> res) {
        //Base case
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                backtrack(board, col + 1, res);
                board[i][col] = '.';
            }
        }
    }

    /*If you are placing queens column-by-column from left to right (starting col = 0), you only need to check the left side of the board. 
    Why you skip these checks:
    Column Down/Up: Since your recursive logic only places one queen per column before moving to the next, there will never be another queen in the current column col when isValid is called.
    Upper/Lower Right Diagonals: Because you haven't reached those columns yet, they are guaranteed to be empty. Checking them would be redundant and slightly slower. 
    
    Corrected logic for column-by-column placement:
    When placing at board[row][col], you only need to check these three "threat zones":
    Row (Left side): board[row][0...col-1]
    Upper-Left Diagonal: Upwards and to the left until the edge.
    Lower-Left Diagonal: Downwards and to the left until the edge */

    private boolean isValid(char[][] board, int row, int col) {
        //Row condition that iterates horizontally to the left within the same row. If a 'Q' is found, it returns false
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }

        //Upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        //lower left diagonal
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        //Construct the board as the value present in board like ..Q.
        for (int i = 0; i < board.length; i++) {
            String row = new String(board[i]);
            result.add(row);
        }
        return result;
    }
}

/*
    public boolean issafe(int row,int col,char[][] board){
        //horizontal
       for(int j=0;j<board.length;j++){
        if(board[row][j]=='Q'){
            return false;
        }
       } 

       //Vertical
       for(int i=0;i<board.length;i++){
        if(board[i][col]=='Q'){
            return false;
        }
       }

       //upper-left
       int r=row;
       for(int c=col;c>=0 && r>=0;c--,r--){
        if(board[r][c]=='Q'){
            return false;
        }
       }

       //upper-right
        r=row;
        for(int c=col;c<board.length && r>=0;r--,c++){
           if(board[r][c]=='Q'){
            return false;
        } 
        }

        //lower-left
        r=row;
        for(int c=col;c>=0 && r<board.length;r++,c--){
            if(board[r][c]=='Q'){
             return false;
         }
        }

        //lower-right
        for(int c=col;c<board.length && r<board.length;r++,c++){
            if(board[r][c]=='Q'){
             return false;
         }
        }
        return true;
    }

    public void saveBoard(List<List<String>> allBoards,char[][] board){
        String row="";
        List<String> newBoard=new ArrayList<>();

        for(int i=0;i<board.length;i++){
            row="";
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='Q'){
                    row+='Q';
                }else{
                    row+='.';
                }
            }
            newBoard.add(row);
        }
        allBoards.add(newBoard);
    }
    public void helper(List<List<String>> allBoards,char[][] board,int col){
        if(col==board.length){
            saveBoard(allBoards,board);
            return;
        }

        for(int row=0;row<board.length;row++){
            if(issafe(row,col,board)){
                board[row][col]='Q';
                helper(allBoards,board,col+1);
                board[row][col]='.';
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards=new ArrayList<>();
        char[][] board=new char[n][n];

        helper(allBoards,board,0);
        return allBoards;
    }
*/