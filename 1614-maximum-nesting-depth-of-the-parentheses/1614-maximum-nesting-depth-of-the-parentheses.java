class Solution {
    public int maxDepth(String s) {
        int open = 0;
        int maxOpen = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                open++;

                if(maxOpen < open) {
                    maxOpen = open;
                }
            } else if(ch == ')') {
                open--;
            }
        }

        return maxOpen;
    }
}