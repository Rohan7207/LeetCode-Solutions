class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();

        int i = 0, j = 0;

        while(i < pushed.length && j < popped.length) {
            s.push(pushed[i]);
            i++;

            while(!s.isEmpty() && s.peek() == popped[j]) {
                s.pop();
                j++;
            }
        }

        return s.isEmpty();
    }
}