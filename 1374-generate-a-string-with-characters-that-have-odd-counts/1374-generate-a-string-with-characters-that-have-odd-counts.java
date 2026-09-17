class Solution {
    public String generateTheString(int n) {
        char[] res = new char[n];

        for (int i = 0; i < n; i++) {
            res[i] = 'a';
        }

        if (n % 2 == 0) {
            res[0] = 'b';

            return new String(res);
        }

        return new String(res);
    }
}

/*
    StringBuilder sb = new StringBuilder();

    if(n % 2 != 0) {
        sb.append("a".repeat(n));
    } else {
        sb.append("a".repeat(n - 1)).append("b");
    }

    return sb.toString();
*/