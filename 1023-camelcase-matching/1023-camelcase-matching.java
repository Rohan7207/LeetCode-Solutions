class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        int n = pattern.length();

        for (String query : queries) {
            int i = 0;
            int j = 0;
            int m = query.length();

            while (i < m) {
                if (j < n && query.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                } else if (Character.isLowerCase(query.charAt(i))) {
                    i++;
                } else {
                    break; // Reject unmatched uppercase letters
                }
            }

            ans.add(i == m && j == n);
        }

        return ans;
    }
}