class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int depth = 0;

        for(int i = 0; i < n; i++) {
            char ch = seq.charAt(i);

            if(ch == '(') {
                depth++;
                res[i] = (depth % 2 == 0) ? 0 : 1;
            } else {
                res[i] = (depth % 2 == 0) ? 0 : 1;
                depth--;
            }
        }

        return res;
    }
}

/*
    The problem asks to group each index into two groups whose max depth must be minimum and it should be VPS
    Ex: seq = (((((())))))
    Consider:
        G0 = () // middle one => depth = 1
        G1 = ((((())))) => depth = 5
        max(1, 5) = 5 which is not correct ans bcz we need it to be min

        lets add another pair
        G0 = (()) => 2,  G1 = (((()))) => 4, max = 4

        or
        G0 = ((())) => 3, G1 = ((())) => 3, max = 3
        we could add
        G0 = (((()))) => 4, G1 = (()) => 2, max = 4

        which is going to answer and it is obvious since we are adding every pair so we could get simple intution that distribute each group half of each pairs, which can be done like, calculate total depth and distribute half to each.
        For above depth = 6, so eaxh pair gets 3 and max is 3
        add alternates when depth increases, which can be used like depth is even add it to even group0 and odd for group1 which generates alternates.
        - When we reach closing bracket we need to find its opening bracket which value of depth so at first ) depth is even = 6 so it belongs to even g0

        res = {1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1}

        - The intution is simple to divide and build res by distributting half to each group based on value of depth

        d = 0;
        for(int i = 0; i < n; i++) {
            char ch = seq.charAt(i);

            if(ch == '(') {
                d++;
                res[i] = (d % 2 == 0) ? 0 : 1;
            } else {
                res[i] = (d % 2 == 0) ? 0 : 1;
                d--;
            }
        }

        return res;  O(n)

        dry run: sep = ()(())()
        i = 0, ch = '(' d = 1, res = {1}
        i = 1, ch = ')' res = {1, 1} d = 0
        i = 2, ch = '(' d = 1, res = {1, 1, 1}
        i = 3, ch = '(' d = 2, res = {1, 1, 1, 0}
        i = 4, ch = ')' res = {1, 1, 1, 0, 0}, d = 1
        i = 5, ch = ')' res = {1, 1, 1, 0, 0, 1}, d = 0
        i = 6, ch = '(' d = 1, res = {1, 1, 1, 0, 0, 1, 1}
        i = 7, ch = ')' res = {1, 1, 1, 0, 0, 1, 1, 1 } d= 0
*/