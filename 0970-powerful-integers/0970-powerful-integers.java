class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set  = new HashSet<>();

        int powerX = 1;

        while(powerX <= bound) {
            int powerY = 1;

            while(powerY <= bound) {
                int sum = powerX + powerY;

                if(sum <= bound) {
                    set.add(sum);
                }

                if(y == 1) {
                    break;
                }

                powerY *= y;
            }

            if(x == 1) break;

            powerX *= x;
        }

        return new ArrayList(set);
    }
}