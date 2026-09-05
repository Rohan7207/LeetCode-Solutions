class Solution {
    public int maximum69Number(int num) {
        int len = (num == 0) ? 1 : (int) Math.log10(Math.abs(num)) + 1;
        int[] arr = new int[len];
        int temp = num;
        int idx = len - 1;

        while (num > 0 && idx >= 0) {
            arr[idx--] = num % 10;

            num /= 10;
        }

        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 6) {
                arr[i] = 9;
                flag = true;
                break;
            }
        }

        if (!flag) {
            return temp;
        }

        int res = 0;
        for (int digit : arr) {
            res = res * 10 + digit;
        }

        return res;
    }
}

/*
     public int maximum69Number (int num) {
        int ans=0;
        String s=String.valueOf(num);
     StringBuilder sb=new StringBuilder();
     for(int i=0;i<s.length();i++){
        char ch=s.charAt(i);
        if(ch=='6' && ans<1){
            sb.append('9');
            ans++;
        }
        else{
            sb.append(ch);
        }
     }
     
     String s1=sb.toString();
     return Integer.valueOf(s1);

    }
*/