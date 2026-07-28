class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();

        int i = 0, j = 0;

        while (i < pushed.length && j < popped.length) {
            s.push(pushed[i]);
            i++;

            while (!s.isEmpty() && s.peek() == popped[j]) {
                s.pop();
                j++;
            }
        }

        return s.isEmpty();
    }
}

/*
    int top = -1;
        int i = 0;
        for(int x: pushed){
            pushed[++top] = x;
            while(top>=0 && pushed[top]==popped[i]){
                top--;
                i++;
            }
        }

        return top==-1;
*/