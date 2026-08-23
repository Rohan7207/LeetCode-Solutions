class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int[] left = get(num.substring(0, n / 2));
        int[] right = get(num.substring(n / 2, n));

        int sumLeft = left[0], questionsLeft = left[1];
        int sumRight = right[0], questionsRight = right[1];

        return (questionsLeft + questionsRight) % 2 == 1 || sumLeft - sumRight != ((questionsRight - questionsLeft) * 9) / 2;
    }

    private int[] get(String s) {
        int digitsSum = 0;
        int questions = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '?') {
                questions++;
            } else {
                digitsSum += ch - '0';
            }
        }

        return new int[] {digitsSum, questions};
    }
}