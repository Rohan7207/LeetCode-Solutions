class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void backtrack(List<List<String>> res, List<String> current, String s, int start){
        if(start == s.length()){
            res.add(new ArrayList<>(current));
            return;
        }

        //end is used to point end of string 
        for(int end = start; end < s.length(); end++){
            if(isPalindrome(s, start, end)){
                current.add(s.substring(start, end + 1));
                backtrack(res, current, s, end + 1);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }

        return true;
    }
}

/*We should partion string such that every element must be palindrome 
    Consider [aab]
            /     \
            a(vp)   aa(vp)
            /  \         \ 
            a(vp) ab(nvp)   b(vp)
        /
        b(vp)       
        
    res=[a,a,b],[aa,b]
    time= O(n*2^n) n=s.length
*/