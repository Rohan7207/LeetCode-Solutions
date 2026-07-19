class Solution {
    public String smallestSubsequence(String s) {
        // Step 1: Store the last occurrence of every character
        int[] lastIndex = new int[26];

        // Fill lastIndex array
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            lastIndex[ch - 'a'] = i;
        }

        // Step 2: Stack to build the answer
        Stack<Character> stack = new Stack<>();

        // Step 3: Keep track of characters already in the stack
        Set<Character> set = new HashSet<>();

        // Step 4: Traverse the string
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(set.contains(ch)) {
                continue;
            }

            while(!stack.isEmpty() && stack.peek() > ch && lastIndex[stack.peek() - 'a'] > i) {
                char removed = stack.pop();
                set.remove(removed);
            }

            stack.push(ch);
            set.add(ch);
        }

        // Step 5: Build answer from stack
        StringBuilder ans = new StringBuilder();

        // Pop every character from stack
        while(!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        // Since stack gives reverse order, reverse the StringBuilder
        return ans.reverse().toString();
    }
}