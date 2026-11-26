class Solution {
    public boolean canAliceWin(int n) {
        int stones = 10;
        int count = 0;

        if(n < 10) {
            return false;
        }

        // 30
        while(n >= 0) {
            if(n - stones < 0) {
                break;
            }

            n -= stones;
            stones--;
            count++;
        }

        return count % 2 == 1;
    }   
}