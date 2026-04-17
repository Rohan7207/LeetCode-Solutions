class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";

        String prefix = strs[0];

        for(int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                if(prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}

/* 
    str.indexOf(prefix)
    Returns:
        0 → if prefix is at the start of the string ✅
        > 0 → if prefix is somewhere later ❌
        -1 → if not present ❌

    Keep reducing prefix until it becomes a valid prefix of the current string.

    It checks whether the current prefix exists at the beginning of the string; if not, we shrink the prefix.

*/