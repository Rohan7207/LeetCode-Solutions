class Solution {
    public int myAtoi(String s) {
        if(s == null || s.length() == 0) return 0; 

        //Constants for 32-bit signed integer range, used to handle overflow and underflow
        final int INT_MIN = Integer.MIN_VALUE;
        final int INT_MAX = Integer.MAX_VALUE;

        int i = 0;
        int n = s.length();

        //Skip leading whitespace
        while(i < n && s.charAt(i) == ' ') {
            i++;
        }

        //Check if we have reached end
        if(i == n) return 0;
        

        //Check for sign
        int sign = 1;
        if(s.charAt(i) == '+'){
            i++;
        }else if(s.charAt(i) == '-'){
            sign = -1;
            i++;
        }

        //Read digits and convert
        long res = 0;
        while(i < n && Character.isDigit(s.charAt(i))){
            int digit = s.charAt(i) - '0';
            res = res * 10 + digit;  //here there is chance of no. going overflow or underflow

            //overflow handling
            if(sign * res <= INT_MIN){
                return INT_MIN;
            }
            if(sign * res >= INT_MAX){
                return INT_MAX;
            }

            i++;
        }

        //Apply sign and return 
        return (int)(res * sign);
    }
}