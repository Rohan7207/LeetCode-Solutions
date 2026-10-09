class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int count = 0;
        int n = s.length();
        int i = 0;

        while (i < n) {
            if (s.charAt(i) == '(') {
                count++;
                i++;
            } else {
                if (count > 0) { // We have '('
                    count--;
                } else { // We should add '(' to res
                    res++;
                }

                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2; // Skip balanced pair
                } else {
                    res++; // Adding ')'
                    i++;
                }
            }
        }

        if (count != 0) {
            return res + count * 2;
        }

        return res;
    }
}

/*
    public int minInsertions(String s) {
        int count = 0;
        int missingOpen = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                if(count % 2 == 1) {
                    count--;
                }
                
                count += 2;
            } else {
                count--;

                if(count < 0) {
                    missingOpen++;
                    count = 1;
                }
            }
        }

        return count + missingOpen;
    }
*/

/*
    s = ")))))))"

    - We only need to increase 1 when '(' not 2 and when we encounter ')' and if  next char is ')' then we just skip it else we insert one character
    if(s[i + 1] == ')' && count > 0) {
        i += 2;
    } else {
        res += 1;
        i++;
    }

    - The third case where there is no opening bracket
    if(count > 0) {  // We had '('
        count--;
    } else {        // We have to add '('
        res += 1;
    }

    if(s[i + 1] == ')') {
        i += 2;
    } else {
        res += 1;
        i++;
    }

    s = "))())("

    count = 1
    res = 1

    - In the last if count is not 0 then it tells that we need insert no.of count * 2 close parentheses
    so we return
    return count * 2 + res;

    1. '('   => count++, i++
    2. ')'   => count > 0, count--
                                /  i + 2
                if i + 1 == ')'  
                                \ res++, i++
    3. count != 0 then return count * 2 + res or return res;
*/