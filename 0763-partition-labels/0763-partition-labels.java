class Solution {
    public List<Integer> partitionLabels(String s) {
        //O(n) and O(1 or 26)
        //Store the last occurence of each character
        int[] lastOccur = new int[26]; //Since input is lowercase
        for (int i = 0; i < s.length(); i++) {
            lastOccur[s.charAt(i) - 'a'] = i;
        }
        // last = {8, 5, 7, 14, 15, 11, 19, 22, 23, 20, 21}
        //Iterate through string to determine partitions
        List<Integer> res = new ArrayList<>();
        int parEnd = 0;
        int parStart = 0;

        for (int i = 0; i < s.length(); i++) {
            parEnd = Math.max(parEnd, lastOccur[s.charAt(i) - 'a']);

            //When we reach end of current position
            if (i == parEnd) {
                res.add(parEnd - parStart + 1);
                parStart = i + 1; //Move to next position
            }
        }

        return res;
    }
}

/*
    We create the array  of last ocuurence of each chararcter in string
    and use two pointers where position end which assigned to first element of array
    and position start is 0, loops starts from index 1
    Consider s={a b a c d x c y z} with a = {2,1,6,4,5,7,8} initially pe=2 and ps=0
    1.For index 1=b 1<2 so pe=2 ps=0 and also we have res array
    2.for index 2=a 2=2 so pe and ps must be updated pe=6 ps=3 and res=pe-ps+1 i.e. res=3
    3.for index 3=c 3<6 till index 6 pe=6 ps=3
    4.for index 6=c 6=6 so pe=7 ps=7 and res=3,4
    5.for index 7=y 7=7 so pe=8 ps=8 and res=3,4,1 and for 8 res=3,4,1,1
    
*/