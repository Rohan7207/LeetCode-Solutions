class Solution {
    public boolean hasAllCodes(String s, int k) {
        int count = 0;
        Set<String> set = new HashSet<>();

        for(int i = 0; i <= s.length() - k; i++) {
            String str = s.substring(i, i + k);

            if(set.add(str)) {
                count++;
            }
        }
       
        return count == Math.pow(2, k);
    }
}

/*
    Brute force lead to TLE due to 2 ^ k binary code generation
     private List<String> binaryCodes;

    public boolean hasAllCodes(String s, int k) {
        binaryCodes = new ArrayList<>();

        backtrack(new StringBuilder(), k);

        for(String str : binaryCodes) {
            if(!s.contains(str)) {
                return false;
            }
        }

        return true;
    }

    private void backtrack(StringBuilder curr, int k) {
        if(curr.length() == k) {
            binaryCodes.add(curr.toString());
            return;
        }

        backtrack(curr.append('0'), k);
        curr.deleteCharAt(curr.length() - 1);

        backtrack(curr.append('1'), k);
        curr.deleteCharAt(curr.length() - 1);
    }
*/