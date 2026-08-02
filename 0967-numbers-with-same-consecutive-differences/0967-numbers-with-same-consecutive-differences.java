class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> list = new ArrayList<>();

        for(int digit = 1; digit <= 9; digit++) {
            dfs(list, digit, n - 1, k);
        }

        int size = list.size();
        int[] ans = new int[size];
        for(int i = 0; i < size; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void dfs(List<Integer> list, int currNum, int remainingDigits, int k) {
        if(remainingDigits == 0) {
            list.add(currNum);
            return;
        }

        int last = currNum % 10;
        if(k == 0) {
            int newNumber = currNum * 10 + last;
            dfs(list, newNumber, remainingDigits - 1, k);
        } else {
            int nextDigit1 = last - k;
            int nextDigit2 = last + k;

            if(nextDigit1 >= 0 && nextDigit1 <= 9) {
                int newNumber = currNum * 10 + nextDigit1;
                dfs(list, newNumber, remainingDigits - 1, k);
            }

            if(nextDigit2 >= 0 && nextDigit2 <= 9) {
                int newNumber = currNum * 10 + nextDigit2;
                dfs(list, newNumber, remainingDigits - 1, k);
            }
        }
    }
}