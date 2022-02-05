/*A palindromic number reads the same both ways. The largest palindrome made
from the product of two 2-digit numbers is 9009 = 91 × 99.
Find the largest palindrome made from the product of two 3-digit numbers.
*/
    
import java.util.ArrayList;

public class LargestPalindrome
{
    public static void main(String args[]) {
        int startVal=999, largestPal=0, i, j, fac1=0, fac2=0;
        
        //start at the highest 3-digit number and iterate downward to find the
        //FIRST palindrome which  must be the largest since it will have
        //been made with the two largest factors
        for(i=100; i<=999; i++) {
            for(j=i; j<=999; j++) {  //start j at i because largest can't be with j < i
                if(isPalindrome(i*j) && i*j > largestPal) {
                    largestPal = i*j;
                    fac1 = i;
                    fac2 = j;
                }
            }
        }
        System.out.println(fac1 + " and " + fac2 + " makes largest " + largestPal);
    }

    public static boolean isPalindrome(int testVal) {
        ArrayList digits = new ArrayList();  //to store the digits of testVal

        //put the digits of testVal into an array list
        while(testVal > 0) {
            //System.out.println(testVal%10);
            digits.add(testVal % 10);  //adds the digits to the list in reverse order
            testVal /= 10;
        }
        
        //determine if the digits form a palindrome by iterating over the
        //list and checking pairwise digits from the outside in until
        //a NON-EQUAL pair is found (NOT a palindrome); otherwise it is
        //a palindrome
        int numDigits = digits.size();
        for(int i=0; i<numDigits/2; i++) {  //if numDigits is odd, middle value doesn't matter anyway
            if(digits.get(i) != digits.get(numDigits-1-i))
                return false;  //found a pair of non-equal digits
        }
        return true;  //all pairs of digits were equal
    }
}
