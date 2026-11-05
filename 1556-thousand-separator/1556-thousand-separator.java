class Solution {
    public String thousandSeparator(int n) {
        if(n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        while(n > 0) {
            if(count == 3) {
                sb.append('.');
                count = 0;
            }

            sb.append(n % 10);
            count++;
            n /= 10;
        }

        return sb.reverse().toString();
    }
}