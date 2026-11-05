class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;

        for (int i = 0; i < arr1.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr2.length; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}

// Time Complexity: O(n * m)
// Space Complexity: O(1)

// This is a good example of converting a definition directly into code and using break once the answer for the current element is known.

/*
    🔑 Key Observation

An element from arr1 is valid only if no element of arr2 is within distance d.

✨ Magic Line
if (Math.abs(arr1[i] - arr2[j]) <= d)

This directly checks the condition that makes an arr1 element invalid.

💡 How We Thought About It

Start from the definition of the distance value:

For each arr1[i], there must not exist an arr2[j] whose difference is ≤ d.

That naturally gives a nested loop:

arr1[i]
   ↓
check all arr2[j]
   ↓
found close element?
   ↓
yes → reject
no  → count
🎤 Interview Importance

This is a good example of converting a definition directly into code and using break once the answer for the current element is known.

🧩 Pattern Recognition

For every element, check whether any conflicting element exists → Nested Loop + Early Break

📚 Similar Questions
LeetCode 217 — Contains Duplicate
LeetCode 349 — Intersection of Two Arrays
LeetCode 1 — Two Sum

🚀 What Can Be Optimized

For a large input, the O(n × m) nested loop can be optimized by sorting arr2 and using binary search to find the closest candidate.

I’ll keep this optimization separate from the approach in your code.
*/

/*
    Arrays.sort(arr2);
        int count = 0;

        for (int i = 0; i < arr1.length; i++) {
            int nearestElement = helper(arr2, arr1[i], 0, arr2.length - 1);

            if (Math.abs(arr1[i] - nearestElement) > d) {
                count++;
            }
        }

        return count;
    }

    private int helper(int[] arr, int x, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (low == 0) {
            return arr[low];
        } else if (low == arr.length) {
            return arr[low - 1];
        }

        int left = Math.abs(x - arr[low - 1]);
        int right = Math.abs(x - arr[low]);

        if (left < right) {
            return arr[low - 1];
        }

        return arr[low];
    }
*/
/*
    // Use Binary search bcz if nearest point of arr1 in arr2 is <= means it is invalid, if nearest point to arr1 fails means there is no chance to other element.
    // So apply binary search on arr2 to find nearst point to arr1 elements
    // Brute force => O(m * n) and O(1)
*/