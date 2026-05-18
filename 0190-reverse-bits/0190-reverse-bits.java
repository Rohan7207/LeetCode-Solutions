class Solution {
    public int reverseBits(int n) {
        int rev = 0;

        for(int i = 0; i < 32; i++){
            rev = rev << 1;  
            rev = rev | (n & 1);
            n = n >> 1; 
        }

        return rev;   
    }
}

/*
    //We bit manipulation operator with O(1) and O(1)
    //we do & with n and do or with rev ans of & oper with 0 {initially is set}
*/