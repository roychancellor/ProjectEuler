/*The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143?
*/

import java.lang.Math;
public class LargestPrime
{
    public static void main(String args[]) {
        //check each number from 1 to the square root of the number to see
        //if it is a factor.  If so, determine if it is prime.  If so, update
        //the largest prime.
        long largestPrime = 1, testVal = 600851475143L, upLim;
        
        upLim = (long)Math.sqrt(testVal);
        for(long i=2; i<=upLim; i++) {
            if( testVal % i == 0)  //then i is a factor of the testVal
                if( isPrime(i) )  //then i is a prime factor of the testVal
                    largestPrime = i;
        }
        System.out.println("The largest prime number of " + testVal + " is " + largestPrime);
    } //end main
    
    public static boolean isPrime(long testVal) {
        long upLim = testVal / 2 + 1;
        for( long i = 2; i <= upLim; i++) {
            if( testVal % i == 0 )
                return(false);
            }
        return(true);
    } //end isPrime
}
