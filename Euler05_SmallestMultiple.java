/*2520 is the smallest number that can be divided by each of the numbers
from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of
the numbers from 1 to 20?
*/
//import java.lang.Math;
//import java.util.ArrayList;

public class Euler05_SmallestMultiple
{
    public static void main(String args[]) {
        int maxVal=20, prodPrimes=1;
        boolean dividesEvenly = false;
        boolean keepSearching = true;
        //brute force:  check each factor from 1-20 with every integer 20 and higher until one
        //is found into which all factrors 1-20 divide evenly
        
        for(int i=3; i<= maxVal; i++)
            if(isPrime(i))
                prodPrimes *= i;
        
        int i = prodPrimes*2;  //the starting value to search for the LCM
        //System.out.println("ProdPrimes = " + prodPrimes);
        while(true) {  //stop when the first number is found that 1-20 divides into
            dividesEvenly = true;  //it is true until proven otherwise
            for(int j=maxVal; j>=3; j--) {  //must be divisible by 2, so stop looking at 3
                if(i % j != 0) {  //found a factor that does not divide into i
                    dividesEvenly = false;
                    break;  //stop looking at the value of i and move on
                }
            }
            if(dividesEvenly) //is still true
                break;
            else
                i+=2;  //optimization:  only need to look at even numbers
        }
        System.out.println("The LCM of 1-" + maxVal + " is " + i);
    }
    
    public static boolean isPrime(int testVal) {
        int upLim = testVal / 2 + 1;
        if(testVal == 1)
            return false;
        else if(testVal % 2 == 0)
            return false;
        else {
            for(int i=3; i<=upLim; i++)
                if(testVal % i == 0)
                    return false;
            }
            return true;
    }
}
