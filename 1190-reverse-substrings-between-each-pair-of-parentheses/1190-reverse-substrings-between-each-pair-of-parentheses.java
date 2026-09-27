class Solution {
    public String reverseParentheses(String s) {
        Stack<Integer> lastSkipLen = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                lastSkipLen.push(res.length());
            } else if (ch == ')') {
                int skipLen = lastSkipLen.peek();
                lastSkipLen.pop();

                // Extract the specific portion and reverse it
                String portionToReverse = res.substring(skipLen, res.length());
                String reversedString = new StringBuilder(portionToReverse).reverse().toString();

                // Replace the old portion with the reversed portion
                res.replace(skipLen, res.length(), reversedString);
            } else {
                res.append(ch);
            }
        }

        return res.toString();
    }
}

/*
    s = "(u(love)i)"
        first is innermost so (love) => evol gives "(uevoli)" => iloveu

    - We can use stack, 
        - In this we will store the '(' index in stack and when it is charcter we add it to result and when we get matching ')' then we will reverse and append to result and make sure starting charcter is '(' next of opening parenthesis
        Ex:  "(u(love)i)"
        - index 0: "(" store in stack, 1: res = u, 2: '(' in stack 3..6: ulove 7: ')' now we need to reverse string "love" from "ulove" which becomes "uevol" so res = uevol, 8: res = uevoli, 9: ')' now again we must reverse full string "uevoli" which gives final answer "iloveu"

    - So at each '(' we will ask res about length it has and we will add res.length to stack which would tell that in future how much characters we should skip. for example for first '(' we would add 0 and for second we would add 1 since only u is present.
    - When we reach ')' we had already stored length to be skipped at this point '(' so we will get 1 and we need to skip res leaving 1 character at this point which is only love, reverse(l, i), where l is skipping length and i is currrent res lenght or maybe res.length; and we remove 1 from stack
    - So stack will store skipping length to reverse whenever we found ')' which helps to solve problem easily.

    Ex: "(ed(et(oc))el)"

    Index  char   stack(res.length) res
     0      (      0(res.length)    "" 
     1      e      0                e
     2      d      0                ed
     3      (      0 2              ed
     4      e      0 2              ede
     5      t      0 2              edet
     6      (      0 2 4            edet
     7      o      0 2 4            edeto
     8      c      0 2 4            edetoc
     9      )      0 2              edetco (since stack top is 4 we will skip 4 and reverse oc to co)   
     10     )      0                edocte (since top is 2 skip and reverse etco to octe and remove 2)
     11     e      0                edoctee
     12     l      0                edocteel
     13     )      empty            leetcode (since top is 0 we will reverse whole string to get finalans)

*/