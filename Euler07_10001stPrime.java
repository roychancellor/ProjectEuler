/*By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that
 the 6th prime is 13.

What is the 10 001st prime number?
*/
public class Euler07_10001stPrime
{
    public static void main(String args[]) {
        int testVal=2, countPrimes=1;  //count 2 initially
        
        while(countPrimes<10001) {
            testVal++;
            if(isPrime(testVal))
                countPrimes++;
        }
        System.out.println("The 10001st prime is " + testVal);
    }
    
    public static boolean isPrime(int testVal) {
        int upLim=testVal/2;
        
        if(testVal%2 != 0)
            upLim += 1;
            
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
