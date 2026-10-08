class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] count = new int[1001];

        for(int num : target) {
            count[num]++;
        }

        for(int num : arr) {
            if(count[num] == 0) {
                return false;
            } 
            
            count[num]--;
        }

        return true;
    }
}

/*
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : target) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(int num : arr) {
            if(!map.containsKey(num)) {
                return false;
            } else {
                map.put(num, map.get(num) - 1);

                if(map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        return true;
    }
*/