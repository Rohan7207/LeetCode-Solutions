class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] start = new int[26];
        int[] end = new int[26];
        boolean[] isValid = new boolean[26];

        List<String> res = new ArrayList<>();
        Arrays.fill(start, -1);
        Arrays.fill(isValid, true);

        for(int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';

            if(start[idx] == -1) {
                start[idx] = i;
            }

            end[idx] = i;
        }

        for(int c = 0; c < 26; c++) {  // Checking all characters
            if(start[c] == -1) {
                continue;
            }

            // Extend end and mark invalid if character was before then start
            for(int i = start[c]; i <= end[c]; i++) {
                if(start[s.charAt(i) - 'a'] < start[c]) {
                    isValid[c] = false;
                    break;
                }

                end[c] = Math.max(end[c], end[s.charAt(i) - 'a']);
            }
        }

        int lastTakenStart = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 0; i--) {
            int idx = s.charAt(i) - 'a';

            if(!isValid[idx]) {
                continue;
            }

            if(i == start[idx] && end[idx] < lastTakenStart) {
                res.add(s.substring(i, end[idx] + 1));

                lastTakenStart = i;
            }
        }

        return res;
    }
}

/*
               -  Recursion / DP (We should this last option) 
             /  - Sliding Window
    SubArray    - Greedy
            \
              -  Two Pointers

    s = "adefaddaccc"
    
    - Now if try to take a charcter in substring, since we should get every occurrence we should get substring till last occurrence of that charcter and try to find next substring in next block
    "adefadda", then we should try in next of 'a' i.e. "ccc"

    "adefadda",  "ccc"  => 2 substrings
    "defadd" (can't make this substring bcz 'a' is also present outside window) -> isValid[1] = false;
    "e", "f", "ccc"  => 3 Substrings
    isValid[4], isValid[5], isValid[6], isValid[7] = false;

    - So we need to store the start and end indexes of all characters and mark isValid as false if currIndex > startIndex.
    - 'a' -> s = 0 to e = 7, and we also need to check middle charcters whether there any occurences in right side if yes modify value end.
    - We need to also check the middleCharacters such that it first occurence should be seen already means
    currIndex <= startIndex, if it is greater we can mark isValid as false.
    Ex = "adzfaddacccz"
    - Now at index 0 we check its lastIndex which is 7, and check d which is 6 and check z which is 11
      so we must take substring from 0 to 11 else we can leave a and try next character


    Step 1: Store start and end indexes of string
    int[] startIdx = new int[26];
    int[] endIdx = new int[26];

    for(int i = 0; i < n; i++) {  //O(n)
        int idx = s.charAt(i) - 'a';

        if(startIdx[i] == -1) {
            startIdx[idx] = i;
        }

        endIdx[idx] = i;
    }

    Step 2: Extend end index if possible and mark invalid if possible
    for(int c = 0; c < 26; c++) {  // Check all charcaters // O(n)
        if(startIdx[c] == -1) continue;

        // For every character check whether any middle character is already present before the start of present charcter for ex = "xadexddaccc"  now for i = 1 s = 1 e = 8 and x is already before s=1 so we mark it as invalid. 
        - It also updates the end character of current substring according to middle characters
        for(int i = startIdx[c]; i <= endIdx[c]; i++) {   O(1) or O(n) in worst case
            if(startIdx[s.charAt(i) - 'a'] < startIdx[c]) {
                isValid[i] = false;
                break;
            }

            endIdx[c] = Math.max(endIdx[c], endIdx[s.charAt(c) - 'a'])
        }
    }

    - We need to also be causios about return the one with minimum total length this 
    "aeea" here if we choose a then "aeea" is valid 1 substring and if not take 'a' and take 'e' then "ee" is also valid substring with length 2 which is our goal so to do this we will traverse from right to left and check whether particular character is starting point or not, if it is starting point
    we can calculate len as end - start + 1. so for e at i = 1 which is starting point we take "ee" as substring and we go to i = 0, we also need to check whether last valid substring start must be greater than end of curr valid substring, This helps to deal with overlapping substrings.

    Bcz of right to left loop:
    - Deal with Overlapping
    - Keep track of last substring startIdx
    - Smaller length string which is greedy approach

    int lastTakenStart = MAX;
    for(i = n - 1; i >= 0; i--) {  // O(n)
        int idx = s.charAt(i) - 'a';

        if(!isValid[idx]) continue;

        if(i == startIdx[idx] && endIdx[idx] < lastTakenStart) {
            res.append(s.substring(i, endIdx[idx] - i + 1));
            lastTakenStart = i;
        }
    }

    return res;  //Overall O(n);
*/  