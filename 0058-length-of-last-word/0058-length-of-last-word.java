class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        s = " " + s;
        int n = s.lastIndexOf(' ');
        String sub = s.substring(n + 1, s.length());
        int l = sub.length();
        return l;
    }
}