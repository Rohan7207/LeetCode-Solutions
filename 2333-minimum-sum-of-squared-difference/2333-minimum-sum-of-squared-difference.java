class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long ans = 0;
        int maxDiff = -1;
        long sumDiff = 0;
        int k = k1 + k2;

        for(int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sumDiff += diff;

            maxDiff = Math.max(maxDiff, diff);
        }

        if(sumDiff <= k) {
            return 0;
        }

        int[] freq = new int[maxDiff + 1];
        
        for(int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            freq[diff]++;
        }

        for(int i = maxDiff; i >= 0 && k > 0; i--) {
            if(i != 0 && k >= freq[i]) {
                k -= freq[i];
                freq[i - 1] += freq[i];
                freq[i] = 0;
            } else if (i != 0){
                freq[i - 1] += k;
                freq[i] -= k;
                k = 0;
            }
        }

        for(int diff = 0; diff < freq.length; diff++) {
            if(freq[diff] > 0) {
                ans += (long) diff * diff * freq[diff];
            }
        }

        return ans;
    }
}

/*
    int k = k1 + k2;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        int n = nums1.length;
        for(int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            maxHeap.add(diff);
        }

        while(k > 0 && !maxHeap.isEmpty()) {
            int newValue = maxHeap.poll() - 1;

            if(newValue > 0) {
                maxHeap.add(newValue);
            }

            k--;
        }

        while(!maxHeap.isEmpty()) {
            int val = maxHeap.poll();
            ans += val * val;
        }

        return ans;
*/

/*
    nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
    Here we need (2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43.
    Where we are allowed to +1 or -1 atmost k1 in nums1 and k2 in nums2.
    - But these doesn't matter that we should k1 in nums1 only if we take k1 + k2 as k then we can perform k opeations on both array bcz for ex there is 10 and 6 if we decrease from 10 it 9 and diff is 3 and also if we increase 6 to 7 then also diff is 3, so it doesn't matter in which we doing operation we should change whose differenc is high. So we need store the difference of nums1[i] - nums2[i] and whose difference is high we will perform operation. 
    For above diff = [4, 4, 4, 3]
    Now k = 2, we will need to perform operation on maximum diff so first thing will come to sort but if reduce 4 to 3 by 1 operation then we should again sort to get next highest so instead of that maintain maxheap and store diff values and perform k operations on max diffs and in end remove from maxheap and return ans.
*/