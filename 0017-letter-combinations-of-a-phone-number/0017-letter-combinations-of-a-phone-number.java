class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if(digits == null || digits.length() ==0){
            return combinations;
        }

        // Store the values in array
        String[] mapping = {
            "",  //0
            "",  //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
        };

        
        backtrack(combinations, mapping, new StringBuilder(), digits, 0);
        return combinations;
    }

    private void backtrack(List<String> combinations, String[] mapping, StringBuilder sb, String digits, int idx){
        if(idx == digits.length()){
            combinations.add(sb.toString());
            return;
        }

        String curr = mapping[digits.charAt(idx) - '0'];  //gets letters stored at the index
        for(char c : curr.toCharArray()){
            sb.append(c);
            backtrack(combinations, mapping, sb, digits, idx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

//Use backtracking  with O(3*3) 3 or 4 and O(2-9) or O(1)
        /* Store the keys as integers and store their values in Hashmap
         we can also use array
         Consider digits [2,3] break values of 2 as a b c and for each value append
         it with d e f as a with d e f,b with d e f and c d e f */