class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length();
        int n = typed.length();
        int i = 0;
        int j = 0;

        while(i < m && j < n) {
            if(name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if(j > 0 && typed.charAt(j) == typed.charAt(j - 1)){
                j++;
            } else {
                return false;
            }
        }

        if(i < m) return false;

        while(j < n) {
            if(j > 0 && typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }

            j++;
        }

        return i == m;
    }
}