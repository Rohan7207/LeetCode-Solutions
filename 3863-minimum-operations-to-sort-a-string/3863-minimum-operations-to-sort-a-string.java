class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check whether string is already sorted, if yes return 0 
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            return 0;
        }

        // "ba", "gf" bcz we cannot sort it, bcz we cannot choose "gf" as substring and it is not possible to sort by choosing only 'g'.
        if (n == 2) {
            return -1;
        }

        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch < minChar) {
                minChar = ch;
            } else if (ch > maxChar) {
                maxChar = ch;
            }
        }

        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        char minMid = s.charAt(1);
        char maxMid = s.charAt(1);
        for (int i = 2; i < n - 1; i++) {
            char ch = s.charAt(i);

            if (ch < minMid) {
                minMid = ch;
            } else if (ch > maxMid) {
                maxMid = ch;
            }
        }

        if (s.charAt(0) > maxMid && minMid > s.charAt(n - 1)) {
            return 3;
        }

        return 2;
    }
}

/*
    s = "card"  targetSorted = "acdr"

    Brute Force: 
        "card"      "acdr"
    We have to reach from s to target in minimum no.of operations and in each step we have to sort a substring and would give new string state, so in each step we are getting new state and we have to reach target state which tells us to use graph approach where,

                    "acrd" (vis)
                acr/
             1 "acrd" --> (crd) 2 "acdr" (target) so return 2 and stop exploring others 
substring  car/      
        "card"  --> (ard) 1 "cadr" 
          ar\
                "card" already vis so return  

    - We need to use BFS bcz in one state we are sorting and adding those states in new level until target is found in particular gurantees an minimum operation

    BFS:
        queue<String> q;
        q.offer(s);
        Map<String(state), Integer(steps)> map;

        while(!q.isEmpty()) {
            // Compute all possible sort of strings and add new states if not vis and return if target is found
        }

    - But bfs will give us TLE bcz of heavy work in while loop

    Optimal appraoch:
    - We can been given that we cannot sort full length string but we can sort maximum length substring.
        Ex: "card"
        - If we cannot sort full string then we can sort "car" or "ard" bcz how taking large no.of characters ensures us that we will get required ans.
        - We choose "car" bcz the min_char 'a' is present in it which ideally should be present at 0th position so we choose it and sort and get "acrd", Now we need to check the last character whether it is max_char if not then we should choose next max no.of characters substrung i.e. "crd" and get final correct sorted string "acdr" which costs us 2 min operations.
        - So, the thing is that we need,
            min_char
            max_char
        - the indexes of both min and max char matters in sorting and there 3 cases possible
       
       Case 1: min_char is already at its correct position 0
        Ex: "acrd"    min = 'a' max = 'r'
        - Since min is at correct position then we should leave this and sort the substring from 1 to n - 1 so that we can get correct sorted string in one single step.
        which gives "acdr"

        Case 2: max_char is already at its correct position n - 1
        Ex: "cdar"  min 'a' max = 'r'
        - Since max is already at its position then the ans would be to sort substring from 0 to n - 2 "cda" which gives sorted string "acdr" in 1 operation.

        Case 3: string is already sorted
        Ex: "acdr"  return 0;

    Story Points:     
    1. is_sorted(s) -> return 0
    2. min_char, max_char
    3. s[0] == min_char or s[0] == max_char   then we can return 1


    Edge case: s[0] = max_char or  s[n - 1] = min_char
    Ex: "racd" for s[0] = max_char and "cdra" for  s[n - 1] = min_char
    1.                                        2.  
            "racd"                                "cdra"  
    "rac" - "acrd"                         "dra" - "cadr"
    "crd" - "acdr"                         "cad" - "acdr"
    2 steps                                  2 steps

    - Here we were lucky that when s[0] was max_char the min_char was present in middle so that when we sorted 0 to n - 2 the min_char came to its correct pos and same when s[n - 1] was min_char the max_char was in middle so that when we sorted 1 to n - 1 the max_char went to its correct possition but there could be situation that min or max char would'nt be at middle the ex for that is,
        Ex: "rdca" where max_char is present in 0th pos and min_char is present in n - 1th pos means in both cases they are not present in middle positions so we can assume like the previous section we should do this case seperatly which i think takes 3 steps.

            "rdca"  - Sending largest to end
     "rdc" - "cdra"   (We are sending largest char at end)
     "dra" - "cadr"   (We sent largest to end but min is in middle so we need one more step)
     "cad" - "acdr"   (Now both are in correct pos and string is sorted)

             "rcda"  - Sending smallest to start
     "cda" - "racd"
     "rac" - "acrd"
     "crd" - "acdr" 

     - It takes 3 steps in this cases.

     The case is: The smallest and largest char are in its position and also there shouldn't be any such element in middle such that it is greater then s[0] and less than s[n - 1]
        0        n - 1
        r  d  c  a
        r  c  d  c

     - We should compute max_mid and min_mid from 1 to n - 2
     - For this case it should this satisfy this condition,
        - s[0] > max_mid  , there should not be any element which greater than s[0], means s[0] should be in first position only if not then  it would fall in "cdra" this case.
        - min_mid > s[n - 1], there should not be any element which smaller than s[0], means s[n - 1] should be in last position only if not then it would fall in "racd" this case.

        if(s[0] > max_mid  && min_mid > s[n - 1]) {
            return 3;
        } else {
            return 2;  For this cases "cdra" and "racd" where either largest or smallest is present in middle.
        }

        It also handle duplicates case like ex: "dcc" where min_mid = c and max_mid = c, and s[0] > max_mid is true bcz d > c but min_mid > s[n - 1]  is false bcz c > c so it returnes 2
                "dcc"
           "dc" - "cdc"
           "dc" - "ccd"  2 steps

     Story Points:     
    1. is_sorted(s) -> return 0
    2. min_char, max_char
    3. s[0] == min_char or s[0] == max_char   then we can return 1
Imp 4. max_mid, min_mid and  if(s[0] > max_mid  && min_mid > s[n - 1]) {
            return 3;
        } else {
            return 2;  For this cases "cdra" and "racd" where either largest or smallest is present in middle.
        }

    // So the ans would be only 0, 1, 2, 3
*/