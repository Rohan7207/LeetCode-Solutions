class Solution {
    public int calculate(String s) {
        int res = 0;
        int sign = 1;
        int curr = 0;

        Stack<Integer> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)) {
                curr = curr * 10 + (c - '0');
            } else if (c == '+') {
                res += sign * curr;
                sign = 1;
                curr = 0;
            } else if(c == '-') {
                res += sign * curr;
                sign = -1;
                curr = 0;
            } else if(c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                curr = 0;
            } else if(c == ')') {
                res += sign * curr;
                curr = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }

        res += sign * curr;

        return res;
    }
}