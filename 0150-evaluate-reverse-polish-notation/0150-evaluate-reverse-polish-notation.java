class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = s.pop();
                int a = s.pop();
                int res = operation(token, a, b);
                s.push(res);
            } else {
                s.push(Integer.parseInt(token));
            }
        }
        
        return s.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int operation(String token, int a, int b) {
        switch (token) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
        }

        return 0;
    }
}