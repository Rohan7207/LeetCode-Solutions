class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]); // 1st = wt
            return Integer.compare(a[1], b[1]); // 2nd = idx
        });

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]); // 1st = finishTime
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]); // 2nd = wt
            return Integer.compare(a[2], b[2]); // 3rd = idx
        });

        for (int i = 0; i < n; i++) {
            free.add(new int[] { servers[i], i });
        }

        int[] res = new int[m];

        // int time = 0;

        // for(int j = 0; j < m; j++) {
        //     // Advance time to at least task arrival j
        //     time = Math.max(time, j);

        //     // If no servers are free, jump time forward to the earliest finishing server
        //     if(free.isEmpty()) {
        //         time = busy.peek()[0];
        //     }

        //     // Move ALL servers that finish at or before 'time' into 'free'
        //     while(!busy.isEmpty() && busy.peek()[0] <= time) {
        //         int[] curr = busy.poll();

        //         free.add(new int[] {curr[1], curr[2]});
        //     }

        //     // Assign the best available free server
        //     int[] curr = free.poll();
        //     res[j] = curr[1];
        //     busy.add(new int[] {time + tasks[j], curr[0], curr[1]});
        // }

        for (int i = 0; i < m; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= i) {
                int[] curr = busy.poll();
                free.add(new int[] { curr[1], curr[2] });
            }

            if (free.isEmpty()) {
                int[] curr = busy.poll();
                res[i] = curr[2];

                busy.add(new int[] { curr[0] + tasks[i], curr[1], curr[2] });
            } else {
                int[] curr = free.poll();
                res[i] = curr[1];

                busy.add(new int[] { i + tasks[i], curr[0], curr[1] });
            }
        }

        return res;
    }
}

/*
    - In  this problem our goal is to determine

        tasks[j] => we need to know at what time server is availabe for task, t + task[j]


    Approach:

        Servers = {3, 3, 2},  tasks= {1, 2, 3, 2, 1, 2}

        We need to find smallest wt with smallest index on same smallest, we can keep looking in array so we need a ds for that, where we need both index and weight giving our requirements

        ds = {{3, 0}, {3, 1}, {2, 2}}
                    Free

            - And if that servers is assigned then we need remove it and add in seperate ds and add it back when it is avialabel in free servers ds. So we need add, delete and need min element which can be achieved using minHeap

             {{3, 0} *(not availabel), {3, 1} *(not availabel), {2, 2}}         
                    Free                            

             {{3, 0} -> 10, {3, 1} -> 5}

                Occupied

        - Occupied heap will store those servers which are assinged task, in this we need also know that in occupied which server will be availabel and which is first one. So,

        occupied -> We want servers which finishes earliest, so in occupied section it should be sorted based on finishtime and must store (finishTime, idx), and when it is free and should be added in free heap.

        - If all servers are busy like {{3,1} -> 7, {3, 0} -> 7, {2, 2} -> 7}, if We should pick like first based on weight, if tie then based on idx.

        Occupied Heap = (finishTime, wt, idx)
        Free Heap = {wt, idx}

        Servers = {3, 3, 2},  tasks= {1, 2, 3, 2, 1, 2}

        Initial: 
        free(wt, idx) = {{2, 2}, {3, 0}, {3, 1}}  assume top is in left
        busy(finish, wt, idx) = {}
             {i + tasks[i], wt, idx}

        Iterate in tasks
        time = i = t = 0:

            - Check by this t time which busy servers are free then move them to free heap.
            - Pop {2, 2}
            wt = 2, idx = 2  res[i] = idx;
            Add it in busy server: busy<finish, wt, idx>
                finish = i + tasks[i]
                busy.add(finish, wt, idx)

                busy.add(0 + 1 = 1, 2, 2)

            free = {{3, 0}, {3, 1}}          
            busy = {{1, 2, 2}}
            res = {2}

        i = 1: t = 1
            - Check at this t = 1 time which server is free, and just check top element bcz it is only best candidate else or greater.

            free = {{3, 0}, {3, 1}}          
            busy = {{1, 2, 2}}
            
            Bcz t = 1 and top of busy finishtime = 1 pop {1, 2, 2}
                finish = 1, wt = 2, idx = 2

            Push in free heap, free.add(wt, idx)

            free = {{2, 2}, {3, 0}, {3, 1}}          
            busy = {}
            Pop the top element of free heap which is next server to be assigned:
                wt = 2, idx = 2 newfinish = i = 1 + tasks[1] = 2 => 3
                busy.add(3, 2, 2)

            free = {{3, 0}, {3, 1}}          
            busy = {{3, 2, 2}}
            res = {2, 2}

        i = 2: t = 2
            free = {{3, 0}, {3, 1}}          
            busy = {{3, 2, 2}}
            // Check in busy whether t = 2 is present, since not there use free heap server

            Pop from free server => {3, 0} wt = 3, idx = 0, newfinsih = i = 2 + tasks[2] = 3 => 5
            busy.add(5, 3, 0)

            free = {{3, 1}}          
            busy = {{3, 2, 2}, {5, 3, 0}}
            res = {2, 2, 0}

        so on...


    // Story points:
    1. 2 heaps 
        Heap (freeSer) -> (wt, idx)
        Heap(busy) -> (finishTime, wt, idx)

    2. Process each task:
        - If we can free servers by time t, free them and move to free servers

        - If All are busy: If free heap is empty means all are empty, and in busy servers if in xth time there is no servers free in busy heap then, abvioulsy we need to wait until first element of busy server is availabel if we remove it we get, finishTime, wt, idx of 1st element of busy heap

        assign this to res, res[i] = idx1, 1 bcz to denote 1st element and since we have assigned it again it will busy so we will add again in busy heap like this,
            newFinishTime = finish1 + tasks[i]
            busy.add(finish1 + tasks[i], wt, idx)

        - I get free servers: res[i] = idx and add in busy as busy.add(i + tasks[i], wt, idx)
*/