class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();

        for(int i = 0; i < k; i++) {
            available.add(i);
        }

        // {finishTime, serverId}
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] count = new int[k];
        int maxRequest = 0;

        for(int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int duration = load[i];

            while(!busy.isEmpty() && busy.peek()[0] <= currTime) {
                available.add(busy.poll()[1]);
            }

            // If all servers are busy, drop that request
            if(available.isEmpty()) {
                continue;
            }

            Integer assignedServer = available.ceiling(i % k);
            if(assignedServer == null) {
                assignedServer = available.first();  // Wrap around and start from 0
            }

            available.remove(assignedServer);
            busy.add(new int[] {currTime + duration, assignedServer});

            count[assignedServer]++;
            maxRequest = Math.max(maxRequest, count[assignedServer]);
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            if(count[i] == maxRequest) {
                res.add(i);
            }
        }

        return res;
    }
}