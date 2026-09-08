class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        int count = 0;
        int size = 0;

        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            size++;

            while (size > k) {
                size--;
                sum -= arr[left];
                left++;
            }

            if (size == k) {
                int avg = sum / k;

                if (avg >= threshold) {
                    count++;
                }
            }
        }

        return count;
    }
}