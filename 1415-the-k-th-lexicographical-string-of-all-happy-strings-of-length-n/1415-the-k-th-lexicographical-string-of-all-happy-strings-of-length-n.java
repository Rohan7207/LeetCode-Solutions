class Solution {

    private List<String> list;

    public String getHappyString(int n, int k) {
        list = new ArrayList<>();

        happyStrings(n, new StringBuilder());

        return (list.size() >= k) ? list.get(k - 1) : "";
    }

    private void happyStrings(int n, StringBuilder curr) {
        if (curr.length() == n) {
            list.add(curr.toString());
            return;
        }

        for (int i = 0; i < 3; i++) {
            char ch = (char) (i + 'a');

            if (curr.length() == 0 || curr.charAt(curr.length() - 1) != ch) {
                curr.append(ch);
                happyStrings(n, curr);
                curr.deleteCharAt(curr.length() - 1); // backtrack
            }
        }
    }
}