class Solution {
    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    private String build(String str) {
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (c != '#') {
                sb.append(c); // Normal character
            } else if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1); // '#': pop the last character of sb
            }
        }

        return sb.toString();
    }
}