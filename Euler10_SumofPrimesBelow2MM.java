/*The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.*/
import java.lang.Math;
public class Euler10_SumofPrimesBelow2MM
{
    public static void main(String args[]) {
        long sumPrimes=2;  //2 is always prime
        int maxVal=2000000;
        for(int i=3; i<maxVal; i+=2) {  //only check odds (all primes are odd; not all odds are prime)
            if(isPrime(i))
                sumPrimes += i;
        }
        System.out.println("The sum of the primes below " + maxVal + " is " + sumPrimes);
    }
    
    public static boolean isPrime(int testVal) {
        //int upLim = testVal / 2 + 1;
        int upLim = (int)Math.sqrt(testVal);
        long multNotFac=1;
        if(testVal == 1)
            return false;
        else if(testVal == 2)
            return true;
        else if(testVal % 2 == 0)
            return false;
        else {
            for(int i=3; i<=upLim; i+=2)  //don't check even numbers for being a factor
                if(testVal % i == 0)
                    return false;
            }
        return true;
    }
}
