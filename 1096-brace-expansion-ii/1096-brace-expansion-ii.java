class Solution {

    String s;
    int n;
    int idx;

    public List<String> braceExpansionII(String expression) {
        n = expression.length();
        s = expression;
        idx = 0;

        Set<String> st = performUnion();
        List<String> res = new ArrayList<>(st);
        Collections.sort(res);

        return res;
    }

    private Set<String> getUnit() {
        Set<String> result = new HashSet<>();

        if(s.charAt(idx) == '{') {
            idx++;  // '{'
            result = performUnion();
 
            idx++;  // '}'
        } else {  // Alphabet
            result.add(String.valueOf(s.charAt(idx)));
            idx++;  // letter
        }

        return result;
    }

    private Set<String> performConcat() {
        Set<String> result = new HashSet<>();
        result.add("");

        while(idx < n && (s.charAt(idx) == '{' || Character.isLetter(s.charAt(idx)))) {
            Set<String> temp = getUnit();

            Set<String> concatRes = new HashSet<>();

            for(String left : result) {
                for(String right : temp) {
                    concatRes.add(left + right);
                }
            }

            result = concatRes;
        }

        return result;
    }

    private Set<String> performUnion() {
        Set<String> result = new HashSet<>();

        while(true) {
            Set<String> temp = performConcat();

            result.addAll(temp);

            if(idx < n && s.charAt(idx) == ',') {
                idx++;
            } else {
                break;
            }
        }

        return result;
    }
}

/*
    Rules: 
        R("a") = {"a"}
        R("{a,b,c}") = {"a","b","c"}, R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
        R("a{b, c}") = R("ab", "ac")

        Ex: "{a,b}{c,{d,e}}"
            "{a, b}{c, d, e}"
            "{{a,z},a{b,c},{ab,z}}"
            {{a, z} {a, b, c} {ab, z}}

        Thought Process: 
            Ex: "a, b, c {d, e {f, g, h{i,{...}}}}"
            First we should solve deepest brace expression and do concatenation with outer part, which follows recurive structure

        Recursion:
            solve(expression) {
                solve(subExpression);
            }

        We can add answer when there is comma present, so we create union function which creates res of union of comma seperated string

        Ex: "a, b{c}" there are 2 enties since one comma so we should get two entities from helper in this it should do union of a, bc and return union of them as {a, bc}
        performUnion() {
            Set<String> set = helper();
        }

        performUnion("a, b") = {"a", "b"};
        performUnion("a, {b}") = {"a", "b"}
        performUnion("a, {b, c}") = {"a", }
                        |- performUnion(b, c) => {"a", "b", "c"}

        Concatenation:
            Ex: "b{c, d}" = "b"{"c"} => {"b", "c"}
                - getSingleUnit('b') => {"b"} -> we call it when it is normal single lowercase letter which converts into string
                - when we see brace we call performUnion({c, d}) which gives set {"c", "d"}
                left = {"b"} right = {"c", "d"}
                Now call concatenate both part by paring each {"bc", "bd"}
        
        functions:
        1. performUnion
        2. performConcat
        3. getUnit() -> ex = "a, b {c, d}"
                             3|   3|    |1
                            {"a"} {"b"} {"c, d"}
                                    \   /
                                      2(concat)  

    Set<String> getUnit() {
        Set<String> res;
        if(expression[idx] == '{') {
            idx++;
            res = performUnion();
        } else {
            res = {new String(expression[idx])};
        }

        idx++;
        return res;
    }

    Set<String> performConcat() {
        Set<String> res;

        while(idx < n && (expression[idx] == '{' || isaplha(expression[idx]))) {
            Set<String> temp = getUnit();

            Set<String> concatRes;

            for(left : res) {
                for(right : temp) {
                    concatRes.add(left + right);
                }
            }

            res = concatRes;
        }

        return res;
    }

    "a{b{c, e}}"
    - when idx is at 'a' we call getUnit which gives {"a"}
    - At { it called performUnion  and we get {"b"} from getUnit(), now this calles concat which gives
        {"ab"}
    - At second { it calls performUnion() where further we get {"c", "e"} then we perform concat and get final res {"abc", "abe"}

    Set<String> performUnion() {
        Set<String> res;

        while(true) {
            Set<String> temp = performConcat();

            res.add(temp);

            if(expression[idx] == ',') {
                idx++;
            } else {
                break;
            }
        }

        return res;
    }

    Ex: "a{b, c}, d, e, f{g, h{i, j}}"
     - At }, performUnion calls concat to perform concatentaion which gives {ab, ac}
     - At next comma, it calls concat which gives {d} so on {e}, {fgi, fgj, fhi, fhj}

*/      