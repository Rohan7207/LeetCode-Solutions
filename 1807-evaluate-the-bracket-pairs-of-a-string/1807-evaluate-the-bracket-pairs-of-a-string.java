class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < knowledge.size(); i++) {
            map.put(knowledge.get(i).get(0), knowledge.get(i).get(1));
        }

        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                StringBuilder temp = new StringBuilder();
                int idx = i + 1;

                while (idx < n && s.charAt(idx) != ')') {
                    temp.append(s.charAt(idx));
                    idx++;
                }

                String key = temp.toString();
                String value = map.getOrDefault(key, "?");

                res.append(value);
                i = idx;
            } else {
                res.append(ch);
            }
        }

        return res.toString();
    }
}