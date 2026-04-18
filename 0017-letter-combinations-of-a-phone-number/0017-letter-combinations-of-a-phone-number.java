class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if(digits == null || digits.length() ==0){
            return combinations;
        }

        //Mapping of digits with letters
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(combinations, map, new StringBuilder(), digits, 0);
        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> map, StringBuilder sb, String digits, int idx){
        if(idx == digits.length()){
            combinations.add(sb.toString());
            return;
        }

        String curr = map.get(digits.charAt(idx));  //gets letters stored at the index
        for(int i = 0; i < curr.length(); i++){
            sb.append(curr.charAt(i));
            backtrack(combinations, map, sb, digits, idx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

//Use backtracking  with O(3*3) 3 or 4 and O(2-9) or O(1)
        /* Store the keys as integers and store their values in Hashmap
         we can also use array
         Consider digits [2,3] break values of 2 as a b c and for each value append
         it with d e f as a with d e f,b with d e f and c d e f */