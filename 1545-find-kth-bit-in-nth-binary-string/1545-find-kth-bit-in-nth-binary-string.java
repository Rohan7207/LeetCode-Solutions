class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }

        int mid = (1 << (n - 1)); // 2 ^ n 

        if (k == mid) {
            return '1';
        }

        if (k < mid) {
            return findKthBit(n - 1, k);
        }

        char ch = findKthBit(n - 1, (1 << n) - k); // 2 ^ n - k

        return ch == '0' ? '1' : '0';
    }
}

/*
    public char findKthBit(int n, int k) {
        StringBuilder sb = new StringBuilder("0");

        for(int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder(sb);
            next.append("1");

            for(int j = sb.length() - 1; j >= 0; j--) {
                char inverted = sb.charAt(j) == '0' ? '1' : '0';

                next.append(inverted);
            }

            sb = next;
        }

        return sb.charAt(k - 1);
    }
*/

/*
    Recursion
    class Solution {
    public char findKthBit(int n, int k) {
        String binary = solve(n, new StringBuilder("0"));

        return binary.charAt(k - 1);
    }

    private String solve(int n, StringBuilder sb) {
        if (n == 1) {
            return sb.toString();
        }

        StringBuilder newSb = new StringBuilder(sb);
        newSb.append("1");

        for (int i = sb.length() - 1; i >= 0; i--) {
            char ch = sb.charAt(i);

            char invertChar = ch == '0' ? '1' : '0';
            newSb.append(invertChar);
        }

        return solve(n - 1, newSb);
    }
}
*/