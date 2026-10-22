class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int ascCount = 0;
        int dscCount = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == (nums[i - 1] + 1) % n) {
                ascCount++;
            }

            if (nums[i - 1] == (nums[i] + 1) % n) {
                dscCount++;
            }
        }

        // If in ascending nature and if already sorted then no steps requried
        if (ascCount == n - 1 && nums[0] == 0) {
            return 0;
        }

        if (ascCount == n - 1) {
            int leftRot = n - nums[0];
            int rightRot = 1 + (nums[n - 1] + 1) + 1;

            return Math.min(leftRot, rightRot);
        }

        if (dscCount == n - 1) {
            // Mandatory one reverse
            int leftRot = n - nums[n - 1] + 1;
            int rightRot = ((nums[0] + 1) % n) + 1;

            return Math.min(leftRot, rightRot);
        }
        
        return -1; // Bad order
    }
}

/*
    nums = {3, 4, 0, 1, 2}

    There are only 2 operations are allowed:
    1. rev = {2, 1, 0, 4, 3}
    2. rot = {4, 0, 1, 2, 3}

    If we visulaize above nums array in circle then
nums:   3            
      /   \
     2     4
     \     /
      1 - 0

    - First in nums the order was 3 -> 4 -> 0 -> 1 -> 2 -> 3
    - But when we reversed it only the directions will change, but not the numbers the order is same position if we check it, where order is 2 -> 1 -> 0 -> 4 -> 3 -> 2
    - And if we do rotate left then only the first elements changes no direction change and there is same ordering is same means for ex 4 as 3 and 0 as its neighbours in both condition and order is,
    4 -> 0 -> 1 -> 2 -> 3 -> 4

    - So there is no change in element neighbours elements which tells that both operations can only change either starting point and direction and there is no change in numbers position.
    - So we should find the starting point of nums in circle in our case 0 and if traverse clock-wise direction then we get sorted order 0 1 2 3 4 and if there is change in element position like if there was 3 instead of 2 then we could'nt get sorted order means,
   *  If nums is already in the bad order then we cannot sort it, so we return -1;

    //////////////////////
    Ascending Nature:
   * - Good Order:    nums= {2, 3, 4, 0, 1}

        0            
      /   \
     4     1
     \     /
      3 - 2

    - So we can say that array is in good if every position of every possible array neighbours after operations is exact as same as that element neighbours in cycle representatio,
    nums = 2 -> 3, 1     cycle = 2 -> 3, 1
    nums = 4 -> 3, 0     cycle = 2 -> 3, 0
    nums = 0 -> 4, 1     cycle = 2 -> 4, 1

    nums= {2, 3, 4, 0, 1}  => rotate left => {3, 4, 0, 1, 2}  so if we check with cycle the neighbours are same and this works for all possible operations performed on array

    - Imp:
        nums = {2, 3, 4, 0, 1}
     - Notice that every ele is + 1 after other means 2 then 3 then 4 then 0 no, we should do like this 
     (nums[i] + 1) % n == nums[i + 1]
    1. (2 + 1) % 5  == 3   => true
    2. (3 + 1) % 5  == 4   => true
    3. (4 + 1) % 5  == 0   => true
    4. (0 + 1) % 5  == 1   => true
    5. (1 + 1) % 5  == 2   => true   here we should check with nums[0]

    - So the sorted nature of this array also follows this and this above also follows this nature, so if the given array follows this condition, 

    *    x -> (x + 1) % 5 => means Already increasing order only starting point has changed.
    
   * Checking "Already Increasing" :
        int countAscending = 0;
        for(i = 1; i < n; i++) {
            countAscending += nums[i] == (nums[i - 1] + 1) % n;
        }

        if(countAscending == n - 1) {
            // Follows Already Increasing Order
        }

   - Now lets check how many operation for all possible cases:
   * 1. nums = {0, 1, 2, 3, 4} => this is already sorted and follows increasing order check and if 
    nums[0] = 0 then the given array is sorted so return 0 operations:
        if(countAscending == n - 1 && nums[0] == 0) {
            return 0;
        }
   * 2.  nums = {2, 3, 4, 0, 1}  given array:
      - In this to get correct order one possibility is bring 0 back to 0th position, but to bring by seeing it we can say we need 3 operations by keeping count.
      - Now how to remove count in general all cases, so if above condition did'nt met like nums[0] == 0 then we are certain that there is other no. then 0 which should follow increasing order, Now if given array follows increasing order then it should be of this type like if first nums[0] = x then 
         
         x,  x + 1,  x + 2,   n - 1,  0,
      bcz every no. is following increasing order and why n - 1 bcz given nums is permutation of 0 to n - 1 so if 0 is not present in nums[0] and other element is present then n - 1 element should  be present before 0 then 0 will be there like in above ex 2, 2 + 1=3, 2 + 2=4(n - 1), 0, So we need to know count of left shift, we can do this like, 

            count = (n - 1) - x + 1 
                =>  n - 1 - x + 1
            steps  => n - x 

    * To move 0 to left(0th) pos = (n - x) left rotations.
                                    = (5 - 2)  => 3
    *** LeftRotation = n - nums[0]

      - But the 3 is not minimum operations to sort array ans is to rotate right which could give us 2 operations. So ideally we should check both left and right rotate and then take min.
      - But the issue is we have not given right rotate, we can only do rev

      Reverse:
            nums = {2, 3, 4, 0, 1}
        Rev = {1, 0, 4, 3, 2}
        left = {0, 4, 3, 2, 1}
        Rev = {1, 2, 3, 4, 0}

        So, the above three steps actually resembles the right rotation in nums array, we can see that in final 3rd step 1 is at 0th pos and 0 is at n - 1th pos which actually is a right rotation.
            1 Right Rotation = Reverse + left_Rotate + Reverse

        Again one right rotation: of Rev in previous step = {1, 2, 3, 4, 0}
            Rev = {0, 4, 3, 2, 1}
            left = {4, 3, 2, 1, 0}
            Rev = {0, 1, 2, 3, 4} which is sorted order 

       *In General for x Right Rotation:
            x Right Rotation = Reverse + x left_Rotate + Reverse

        In above case for nums = {2, 3, 4, 0, 1}:  We had need to do 2 right rotations, then 
            2 Right Rotation = Reverse + 2 left_Rotate + Reverse 

            Rev = {1, 0, 4, 3, 2}
            first_left_Rotate = {0, 4, 3, 2, 1}
            first_left_Rotate = {4, 3, 2, 1, 0}
            Rev = {0, 1, 2, 3, 4}   => which is correct  sorted array

  * To count how much right rotation we need:
        nums = {2, 3, 4, 0, 1}  => so we need to bring 0 and 1 at front that are 2 no.s
    - The count can be calculated as in this problem 1 - 0 + 1 =>(a - b + 1), 
    * In general, count of right rotation is nums[n - 1] + 1

        For nums = {3, 4, 0, 1, 2}   then right rotation is nums[n - 1] + 1 => 2 + 1 = 3

        x Right = Rev + x Left +  Rev
        where x is actually count of right rotation right which is nums[n - 1] + 1, then

      **  nums[n - 1] + 1 Right Rotations = Rev + nums[n - 1] + 1 Left Rotate + Rev

      *** RightRotation = 1 + (nums[n - 1] + 1) + 1
                        = (nums[n - 1] + 1)(left)  + 2 (2 Rev)

      *** LeftRotation = n - nums[0]

      This above two calculated when if it is in ascending natur

      ** In final res would min of this two => res = Math.min(LeftRotation, RightRotation)
          or   
    
    Last Writing line when given array is in ascending nature.
    ****  res = Math.min(n - nums[0], (nums[n - 1] + 1)  + 2)  

    ////////////////////

    //////////////////////
    Descending Nature:

        nums = {3, 2, 1, 0, 4}  this is in descending nature bcz every ele is - 1 after other
     
    - So we can check this like,   
        if(nums[i - 1] == (nums[i] + 1) % n), we can remove the count as ascending 

    * Checking "Already Increasing" :
        int countDescending = 0;
        for(int i = 1; i < n; i++) {
            if(nums[i - 1] == (nums[i] + 1) % n)  {
                countDescending++;
            }
        }

        if(countDescending == n - 1) {
            // Descending nature
        }

    - Now lets check how many operation for all possible cases:
   * 1. nums = {4, 3, 2, 1, 0} => this is already sorted in descending order and follows decreasing  order check and if 
    nums[0] = n - 1 then the given array is sorted in descending order so we need to return 1 operations, bcz we can directly apply reverse operation so return 1.
        if(countAscending == n - 1 && nums[0] == n - 1) {
            return 1;
        } 

    2. Now lets check for other cases so whenever it is in descending nature to make it in ascending nature we only need to reverse it right like for ex:

        nums = {3, 2, 1, 0, 4}
        Rev = {4, 0, 1, 2, 3}
        The above reverse part is actually a ascending nature which follows our increasing nature bcz we already know how to handle ascencding nature so we can directly use it here by only adding +1 to our case
    
        - But since we cannot actually reverse the array we just need to change few things in above equation which handles ascending nature

        Ascending:
            *** RightRotation = 1 + (nums[n - 1] + 1) + 1
                        = (nums[n - 1] + 1)(left)  + 2 (2 Rev)

            *** LeftRotation = n - nums[0]

            This above two calculated when if it is in ascending natur

            ** In final res would min of this two => res = Math.min(LeftRotation, RightRotation)
                or   
            
            Last Writing line when given array is in ascending nature.
            ****  res = Math.min(n - nums[0], (nums[n - 1] + 1)  + 2)

                Descending Nature:
                    *** RightRotation = 1 + (nums[n - 1] + 1) + 1
                                = (nums[n - 1] + 1)(left)  + 2 (2 Rev)

            *** LeftRotation = n - nums[0]

            This above two calculated when if it is in ascending natur

            ** In final res would min of this two => res = Math.min(LeftRotation, RightRotation)
                or   
            
            Last Writing line when given array is in ascending nature.
            ****  res = Math.min(n - nums[0], (nums[n - 1] + 1)  + 2)

        
        Descending Nature: nums = {3, 2, 1, 0, 4}
            After rev = {4, 0, 1, 2, 3},  which costs + 1 operation in res

            - Now we in left we need to do n - nums[0], but in rev case nums[0] = 4 which we want but in actual it is present in last position by reversing it then it came to first position, so we need to do n - nums[n - 1]
            *** LeftRotation = n - nums[n - 1]


            - right = Rev + xLeftRot + Rev, but to bring in ascending nature we are also doing one extra must rev operation so, the total right rotation operation would be

            ** RightRotation = 1 (Rev-must to bring in ASC) [1Rev + xleftRot + 1Rev]
                -- Here the 1st rev which is must and the 1rev which is to rotate in right will cancel out each other so,
                        = xLeftRot + 1Rev

                *Lets check whether this works and calculate x value from it,
                    nums = {3, 2, 1, 0, 4}

                    After 3LeftRot = {0, 4, 3, 2, 1}
                    After Rev = {1, 2, 3, 4, 0}

                    but this is not sorted array, to get sorted we need to one more left rotate
                    =>  After 4LeftRot = {4, 3, 2, 1, 0}
                        After Rev = {0, 1, 2, 3, 4}

                    ** The reason is simple that what we need to xleft + rev so we don't need 0 to be at 0th position we need it in last pos so 1 extra left rotation will do that
                    So x = 4times LeftRotation + 1Rev

                    x = nums[0] + 1, bcz length of left rotation is till nums[0]...0 which is nums[0] - 0, which is nums[0], but now we need 1 extra leftRot so nums[0] + 1.

                ***total Right rotation = (nums[0] + 1(left Rotations)) + 1(Rev)
                         ****    = (nums[0] + 1) + 1

             One Edge Case like what if nums is already sorted in descending which i check it like if(nums[0] == n-1) return 1;  but according to video the reason is bcz
             for nums = {4, 3, 2, 1, 0}
                right rotation = nums[0] + 1 + 1
                                4 + 1 + 1
                                5 + 1
                    - So when do 5 left rotation then we actually return to same back old nums so in this case we just need to do 1 rev, we can check this like, 
                    if(nums[0] + 1 % n == 0) {
                        return 1;  // nums is descending so we need to only reverse it
                    }
                - But instead checking condition we can right final total right rotations like

                    **** total Right rotation = ((nums[0] + 1) % n) + 1

            This above two calculated when if it is in ascending natur

            ** In final res would min of this two => res = Math.min(LeftRotation, RightRotation)
                or   
            
            Last Writing line when given array is in ascending nature.
            ****  res = Math.min(n - nums[n - 1], ((nums[0] + 1) % n) + 1)
    ////////////////////////

    Summarize Story Points:
    1. ASC / DSC
    2. ASC ->    nums[0]  == 0 ->  return 0
           ->    min(leftRot, rightRot)    

    3. DSC ->    rev -> ASC
           ->    min(leftRot, rightRot) 
*/