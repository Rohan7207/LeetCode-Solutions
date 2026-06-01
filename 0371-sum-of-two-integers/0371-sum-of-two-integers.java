class Solution {
    public int getSum(int a, int b) {
        while(b != 0){
            int temp = a ^ b;
            int carry = (a & b) << 1;

            a = temp;
            b = carry;
        }

        return a;
    }
}

/*
    We use XOR and AND it,  with O(1) and O(1)
    We do a XOR b and a & b << 1 till 0
    
    a=5 and b=3
                1 0 1
                0 1 1
    a^b=        1 1 0
    a&b<<1= 0 0 1  
    //the last space is bcz we did left shift and we do until b!=0  and we return a 

    return a + b;
*/