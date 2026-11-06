class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;

        // Sort ascending based on the 2nd term (index 1)
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        int len = queries.length;
        int[] res = new int[len];

        // Create a 2D array pairing each element with its index {{value, index}, ...}
        int[][] paired = new int[len][2];
        for (int i = 0; i < len; i++) {
            paired[i][0] = queries[i];
            paired[i][1] = i;
        }

        // Sort by the 1st term (the value at index 0)
        Arrays.sort(paired, Comparator.comparingInt(a -> a[0]));

        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0;

        for (int[] pair : paired) {
            int time = pair[0];
            int idx = pair[1];

            int start = time - x;
            int end = time;

            // Expansion
            while (j < m && logs[j][1] <= end) {
                map.put(logs[j][0], map.getOrDefault(logs[j][0], 0) + 1);
                j++;
            }

            // Shrinking
            while (i < m && logs[i][1] < start) {
                map.put(logs[i][0], map.get(logs[i][0]) - 1);

                if (map.get(logs[i][0]) == 0) {
                    map.remove(logs[i][0]);
                }

                i++;
            }

            // map.size() = no.of unique servers getting request in [start, end]
            res[idx] = n - map.size();
        }

        return res;
    }
}

/*
    n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]

    query 1: [10 - x, 10] => [5, 10] timestamps = {5, 6, 7, 8, 9, 10} we should search whether timestamp range falls in given logs or not and keep count of servers that did not receive request during this query.

        => [1, 3]  no,   [2, 6] yes,  [1, 5] yes   =>  count = 1, [1, 3] log didn't recieve request
    
    query 2: [11 - x, 11] => [6, 11]  count = 2

    Brute Force: 
    1. Range: [q - x, q]
    2. Check each logs and count which did'nt receive request.

    It takes => Q * L ^ 2  = 10 ^ 10

    Since everything is about time lets take no.line:

        n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]

                       s1 s2    s2
    time =       s1    _________________         
           1  2  3  4  5  6  7  8   9  10  11  12 

                => In this window of 5 to 10 the no.of servers who receive requests are count = 2;
                zeroRequest = (n - count);
                * Though server 2 is present in range twice but it should be counted twice only so count = 2 and request = n - 2 = 1

        [a, b] => count = Unique Servers who got requests in this range
        zeroRequest = (n - count);

    -> We have to find count of unique servers who receive request:

        n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]

        query 1 = 10 => [5, 10]

                       s1 s2    
    time =       s1    _________________         
           1  2  3  4  5  6  7  8   9  10  11  12 

        * Since everything in no. line is sorted fashion so we have to sort logs by timestamps and find answer.

        logs = [[1, 3], [1, 5], [2, 6]]
                           s1 s2    
    time =       s1    _________________         
           1  2  3  4  5  6  7  8   9  10  11  12 

        - We start with first element if time is greater than or equal to start of range then count it else remove that server log for this query. So we remove [1, 3] from track, then we go ahead and check [1, 5] which lies count = 1, then [2, 6] lies count = 2,  we will continue until end or till we find the log which fails condition of range since we have sorted logs. To keep unique servers we can maintain map or set

            logs[j][1] <= end of range

    query 2: 11 => [6, 11]:

      logs = [[1, 3], [1, 5], [2, 6]]

                         s2    
    time =                __________________         
           1  2  3  4  5  6  7  8   9  10  11  12 

      - In second query since logs are sorted and queries sorted and also keep it its previous index info it is gurantee that the window query range will move forward always so we can use previous active servers and remove the servers whose time is less that start of range where [1, 5] 5 < 6 so remove [1, 5] count = 1. Since 6 >= 6 we can keep it and consider if there were other logs we would traverse and put unique servers until timestamp <= end. and in next query we would use this info.

    - ** This is similar to sliding window technique where we kept a window at first query and in next query we shrink based on condition. where sliding window is applied on logs array.

    Points:
    1. query -> [query, i] -> sort based on query time  and store its idx info
    2. logs -> sort based on time
    3. sliding window on logs
    4. Unique servers -> map or set, but prefer map bcz we need to also remove server based on shrinking by decreasing count of that server and we can remove server when it is 0.

    Dry run:
        n = 3, logs = {{1, 3}, {1, 5}, {2, 6}} queries = {{10, 1}, {11, 0}} x = 5

        for query 1: 10 => [5, 10], s = 5 e = 10
        m = logs.length;
        // current window or expansion
        while(j < m && logs[j][1] <= e) {
            map.put(logs[j][0], map.getOrDefault(logs[j][0], 0), 1);  // s1 -> 2, s2 -> 1 window is 0to2
            j++;
        }

        // Window on left side which is less than start then remove it or shrinking logic
        while(i < m && logs[i][1] < start) {
            map.put(logs[i][0], map.get(logs[i][0]) - 1); // s1 -> 1, s2 -> 1 window is 1to2
            if(map.get(logs[i][0]) == 0) {
                map.remove(logs[i][0]);
            }

            i++;
        }

        - Now i and j correct points that are in range we can get count using size of map bcz we only need unique servers
        zeroRequest = (n - count);  // 3 - 2 = 1

        res[1(original idx after sorting)] = zeroRequest;
*/