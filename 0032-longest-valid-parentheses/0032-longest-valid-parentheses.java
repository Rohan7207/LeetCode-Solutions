class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        int n = s.length();
        int open = 0, close = 0;

        // Left to Right
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                res = Math.max(res, open + close);
            } else if (close > open) {
                open = close = 0;
            }
        }

        open = 0;
        close = 0;
        // Right to Left
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                res = Math.max(res, open + close);
            } else if (open > close) {
                open = close = 0;
            }
        }

        return res;
    }
}

/*
    Another approach using open and close
    s = "())()"   L to R

    open = 0, close = 0
    open > close, move forward hopping there will be ) to balance
    close > open, reset open = close = 0, bcz there will be not ( in future to balance so rest
    open == close, take res = max(res, open + close)

    but above version fails for s = "(()" here we never reach open == close so res would be 0 but we have valid pair so length is 2
    - So we need to traverse from right to left by checking open goes out of count
    R - L

    open = 0, close = 0
    close > open, move backward hopping there will be ( to balance
    open > close, reset open = close = 0, bcz there will be not ) in backward to balance so rest and move
    open == close, take res = max(res, open + close)
*/

/*
    public int longestValidParentheses(String s) {
        int maxlength = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1); //Initial value

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();

                if (st.isEmpty()) {
                    //Push the current index as base for next substring
                    st.push(i);
                } else {
                    maxlength = Math.max(maxlength, i - st.peek());
                }
            }
        }

        return maxlength;
    }
*/

// Time Complexity: O(n)
// Space Complexity: O(n)

// Use a stack of indices to track unmatched ( and calculate each valid substring as currentIndex - stackTop.

/*
🔑 Key Observation

The stack doesn't store the parentheses themselves — it stores their indices.

Example:

s = ( ) ( )

i = 0   '(' → push 0
i = 1   ')' → pop 0

Stack still contains:

[-1]

So:

length = 1 - (-1) = 2
✨ Magic Line
maxlength = Math.max(maxlength, i - st.peek());

st.peek() represents the index just before the current valid substring.

Therefore:

current index - base index = valid length
💡 How We Came Up With It

The main difficulty is finding where a valid substring starts.

Consider:

) ( ) ( )

The first ) cannot be matched.

So when we encounter it:

if (st.isEmpty()) {
    st.push(i);
}

We use that unmatched ) as a new boundary/base.

Then later:

( )

can be measured from that boundary.

That's why -1 is initially pushed:

st.push(-1);

It gives us a valid base when the valid substring starts at index 0.

⚠️ Important Line
st.push(i);

when the stack becomes empty after processing ).

This means:

An unmatched ) has been found, so no valid substring can cross this index.

It becomes the new boundary.

🧩 Pattern Recognition

For Longest Valid Parentheses, recognize:

Need matching + need length → store indices in a stack.

The stack maintains the boundary needed to calculate lengths.

📚 Similar Questions
LeetCode 32 — Longest Valid Parentheses
LeetCode 20 — Valid Parentheses
LeetCode 22 — Generate Parentheses
LeetCode 856 — Score of Parentheses
LeetCode 1614 — Maximum Nesting Depth of the Parentheses
*/

/*
    “I use a stack of indices to track unmatched parentheses and compute valid substring lengths efficiently.”

    Time Complexity: O(n)
    Space Complexity: O(n)
*/