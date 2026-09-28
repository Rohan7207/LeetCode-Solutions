class Solution {
    public int maxDepth(String s) {
        int open = 0;
        int maxOpen = -1;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                open++;
            } else if(ch == ')') {
                open--;
            }

            maxOpen = Math.max(maxOpen, open);
        }

        return maxOpen;
    }
}