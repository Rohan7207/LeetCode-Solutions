class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        while(n != 0){
            count++;
            n = n & (n - 1);
        }

        return count;
    }
}

/*
    We use AND operator with O(1) and O(1) and Brian Kernighan’s algorithm loops based on the number of 1s.
    In loop we & with number n and n-1, in this process we eliminate one 1
    from the no. and increment count and we do till binary is all 0's 
    1ms with 32.89%
*/