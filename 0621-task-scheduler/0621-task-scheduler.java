class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxfreq = 0;
        int countfreq = 0;

        // freq = {2, 2, 1, 1} maxfreq = 2 countfreq = 2
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        for (int i = 0; i < 26; i++) {
            if (maxfreq < freq[i]) {
                maxfreq = freq[i];
            }
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] == maxfreq) {
                countfreq++;
            }
        }

        int formula = (maxfreq - 1) * (n + 1) + countfreq;
        return Math.max(tasks.length, formula);
    }
}

/*
   //Step 1=COunt no.of freq of each task
    Map<Character, Integer> freqmap = new HashMap<>();
    for (char task : tasks) {
        freqmap.put(task, freqmap.getOrDefault(task, 0) + 1);
    }

    //Step 2=Build a maxheap based on frequency
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    maxHeap.addAll(freqmap.values());

    //Step 3=Process the task
    int interval = 0;
    while (!maxHeap.isEmpty()) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            if (!maxHeap.isEmpty()) {
                temp.add(maxHeap.poll());
            }
        }

        for (int freq : temp) {
            if (--freq > 0) {
                maxHeap.add(freq);
            }
        }

        //Step 4=Update the interval
        interval += maxHeap.isEmpty() ? temp.size() : n + 1;
    }

    return interval;
*/