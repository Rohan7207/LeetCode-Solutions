class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] ans = new String[names.length];
        int idx = 0;

        for(String name : names) {
            if(!map.containsKey(name)) {
                map.put(name, 0);
                ans[idx++] = name;
            } else {
                int nextSuffix = map.get(name) + 1;
                String candidate = name + "(" + nextSuffix + ")";

                while(map.containsKey(candidate)) {
                    nextSuffix++;
                    candidate = name + "(" + nextSuffix + ")";
                }

                map.put(name, nextSuffix);
                map.put(candidate, 0);
                ans[idx++] = candidate;
            }
        }

        return ans;
    }
}