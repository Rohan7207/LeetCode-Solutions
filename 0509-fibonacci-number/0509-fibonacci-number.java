class Solution {
    public int fib(int n) {
        int first = 0;
        int second = 1;

        if(n == 0) return 0;

        for(int i = 1; i < n; i++) {
            int ans = second;
            second = first + second;
            first = ans;
        }

        return second;
    }
}

/* 
    if(n == 0) return 0;
    else if(n == 1) return 1;

    else return fib(n - 1) + fib(n - 2);
*/