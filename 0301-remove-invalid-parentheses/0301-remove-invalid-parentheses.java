class Solution {

    Set<String> set;
    int n;
    int maxLength;

    public List<String> removeInvalidParentheses(String s) {
        n = s.length();
        set = new HashSet<>();

        backtrack(s, 0, new StringBuilder(), 0);

        List<String> res = new ArrayList<>();
        for (String validString : set) {
            res.add(validString);
        }

        return res;
    }

    private void backtrack(String s, int i, StringBuilder curr, int count) {
        if (count < 0) { // Invalid parentheses
            return;
        }

        // Base case
        if (i == n) { // At last level of tree
            if (count == 0) { // If curr string is vps
                if (curr.length() > maxLength) { // If it produces better string than previous strings
                    maxLength = curr.length();
                    set.clear();
                }

                if (curr.length() == maxLength) { // If equal length then add to set
                    set.add(curr.toString());
                }
            }

            return;
        }

        if (s.charAt(i) != '(' && s.charAt(i) != ')') {
            curr.append(s.charAt(i));
            backtrack(s, i + 1, curr, count);
            curr.deleteCharAt(curr.length() - 1);
            return;
        }

        curr.append(s.charAt(i));
        backtrack(s, i + 1, curr, count + (s.charAt(i) == '(' ? 1 : -1));
        curr.deleteCharAt(curr.length() - 1);
        backtrack(s, i + 1, curr, count);
    }
}

/*
    s = "(a)())()"
    - We will need to explre every possiblities by checking whether take or not take which gives 2^n and we will know that this solution will be accepted by given constraint i.e. there will be atmost 20 parentheses which is 2 ^ 20 => 10^6
    Which gives approach to backtracking
    Suppose after adding every valid parentheses in set we should check max length string bcz which as max length it consists minimal removal of invalid position brackets
    Ex: set = {"()", "(())", "()()", "((()))"} here minimal removal is max length string which is our required answer according to question. and add all such answers.

    - We could do this process on going by tracking maxlength of curr string and in future string as better max length ans we clear set and add that string if it is same we will append it to set so that in main we can directly return set.

    int max = 0;
    void solve(i, curr, count, s, maxLength) {  => O(2 ^ n) and O(m * n)
        if(count < 0) return;  // early proning

        if(i == n) {
            if(count == 0) {
                if(curr.length() > maxLength) {
                    maxLength = curr.length();
                    set.clear();
                }

                if(curr.length() == maxLength) {
                    set.add(curr);
                }
            }

            return;
        }

        if(s.charAt(i) != '(' && s.charAt(i) != ')') {
            curr.append(s.charAt(i));
            solve(i + 1, curr, count, s, maxLength);
            curr.deleteCharAt(curr.length() - 1);
            return;
        }

        curr.append(s.charAt(i));
        solve(i + 1, curr, count += (s.charAt(i) == '(' ? 1 : -1), s, maxLength);
        curr.deleteCharAt(curr.length() - 1);
        solve(i + 1, curr, count, s, maxLength);
    }
*/