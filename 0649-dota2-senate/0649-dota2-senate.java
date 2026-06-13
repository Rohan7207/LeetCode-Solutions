class Solution {
    public String predictPartyVictory(String senate) {
        Queue<Integer> r = new ArrayDeque<>();
        Queue<Integer> d = new ArrayDeque<>();
        int n = senate.length();

        //Put indices in each array
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R')
                r.offer(i);
            else
                d.offer(i);
        }

        //Compare indices of both array and the smaller one will knock out the bigger one
        while (!r.isEmpty() && !d.isEmpty()) {
            int r1 = r.poll();
            int d1 = d.poll();
            if (r1 < d1) {
                //Radiant acts first, bans the dier senator
                r.offer(r1 + n);
            } else {
                //Dire acts first
                d.offer(d1 + n);
            }
        }

        return r.isEmpty() ? "Dire" : "Radiant";
    }
}