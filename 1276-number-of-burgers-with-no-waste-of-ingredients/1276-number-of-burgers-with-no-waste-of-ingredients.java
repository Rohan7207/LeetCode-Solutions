class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> ans = new ArrayList<>();

        int val1 = tomatoSlices - 2 * cheeseSlices;
        int val2 = 4 * cheeseSlices - tomatoSlices;

        if(val1 < 0 || val1 % 2 != 0 || val2 < 0 || val2 % 2 != 0) {
            return ans;
        }

        ans.add(val1 / 2);
        ans.add(val2 / 2);
        
        return ans;
    }
}

/*
x = (tomatoSlices - 2 * cheeseSlices) / 2
y = (4cheeseSlices - tomatoSlices)/2.

4x + 2y = t
4x + 4y = 4c
    y = 4c - t/2

*/