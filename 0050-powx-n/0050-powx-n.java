class Solution {
    public double help(double x,long n){
        if(n==0) return 1;
        double half=help(x,n/2);
        if(n%2==0){
            return half*half;
        }else{
            return half*half*x;
        }
    }
    public double myPow(double x, int n) {
        long N=n; 
        if(n<0){
            x=1/x;
            N=-N;
        }
        return help(x,N);
    }
}