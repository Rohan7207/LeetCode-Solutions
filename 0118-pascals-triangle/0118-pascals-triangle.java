class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.get(0).add(1); //Starting element of pascal's triangle

        for (int row = 1; row < numRows; row++) {
            //The list stores value of current row
            List<Integer> curr = new ArrayList<>();

            //The list stores value of prev row
            List<Integer> prevList = res.get(row - 1); 

            curr.add(1); //Starting element of row must be always 1
            for (int j = 1; j < row; j++) {
                curr.add(prevList.get(j - 1) + prevList.get(j)); //Middle elements
            }
            curr.add(1); //Last element of row must be 1

            res.add(curr);
        }

        return res;
    }
}