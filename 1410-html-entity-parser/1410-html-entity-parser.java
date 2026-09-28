class Solution {
    public String entityParser(String text) {
        int n = text.length();
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = text.charAt(i);

            if (ch == '&') {
                int idx = i;
                while (idx < n && text.charAt(idx) != ';') {
                    idx++;
                }

                if (idx < n) {
                    String substr = text.substring(i, idx + 1);
                    if (map.containsKey(substr)) {
                        ans.append(map.get(substr));
                        i = idx;
                        continue;
                    }
                }
            }
            ans.append(ch);
        }

        return ans.toString();
    }
}