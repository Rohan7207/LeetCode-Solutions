class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> openBracketIdx = new Stack<>();
        int[] bracketIdx = new int[n];  // bracketIdx[0] = 2 and bracketIdx[2] = 0 means two way mapping

        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // We use stack bcz whenever we find closing stack stores it corrct opening bracket and we store it in closeBracketIdx
            if(ch == '(') {
                openBracketIdx.push(i);  
            } else if(ch == ')') {
                int j = openBracketIdx.peek();
                openBracketIdx.pop();

                bracketIdx[i] = j;
                bracketIdx[j] = i;
            }
        }

        StringBuilder res = new StringBuilder();
        int flag = 1;  // For changing direction
        for(int i = 0; i < n; i += flag) {
            char ch = s.charAt(i);

            if(ch == '(' || ch == ')') {
                i = bracketIdx[i];
                flag = -flag;  // Changing direction if RTL -> LTR or LTR -> RTL
            } else {
                res.append(ch);
            }
        }

        return res.toString();
    }
}

/*
    Approach 1: Brute force with stack, O(n ^ 2) and O(n) and above is simple observation approach of changing direction and printing
    public String reverseParentheses(String s) {
        // O(n ^ 2) (bcz reverse takes O(n) in worst case) and O(n)
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
*/

/*
    Approach 1: Brute Force with Stack
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

/*
    Approach 2: 
        - Here simple is that we need to print characters from end with macthing pair so we need to change directions based on parenthesis. We need to go its matching counter bracket
        Ex: "(a(bc)d)"
        index  char  counterIdx    direction    res
         0      (      7              RTL        ""
         6      d      7              RTL         d
         5      )      2              LTR         d
         3      b      2              LTR         db
         4      c      2              LTR         dbc
         5      )      2              RTL         dbc
         1      a      2              RTL         dbca
         0      (      7              LTR         dbca  
         8      "out of bounds and we get our required answer"                                 

    - Since we need to store each matching pair so we store in map like
    0 - 7, 2 - 5 and 7 - 0, 5 - 2

    - How we change direction
        LTR = i + 1
        RTL = i - 1
    We will take flag = 1 and when we find any parentheses we will change flag to -1 and do 
        LTR = i + flag
        RTL = i + flag (we will do right based on changing flag value)
*/