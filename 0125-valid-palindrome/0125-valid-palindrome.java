class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            //Skip all the all non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            //Skip all the all non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            //Compare the characters
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}