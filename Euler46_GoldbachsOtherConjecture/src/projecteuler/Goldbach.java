/**
 * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

9 = 7 + 2×12
15 = 7 + 2×22
21 = 3 + 2×32
25 = 7 + 2×32
27 = 19 + 2×22
33 = 31 + 2×12

It turns out that the conjecture was false.

What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */

package projecteuler;

public class Goldbach {

	//The conjecture is that for every odd composite (non-prime) number C, there exits a prime P and a square S such that C = P + 2 * S
	//Solving for P gives P = C - 2 * S. To prove the conjecture false, find C such that there is NO value of S such that C - 2 * S is prime.
	//The first C found is the answer.
	public static void main(String[] args) {
		for(int c = 35; c < 10001; c += 2) {
			if(!isPrime(c)) {  //that is, c is composite
				long upLim = (long)Math.sqrt(c / 2);  //ensures c - 2 * S is positive
				boolean foundPrime = false;
				for(int i = 1; i <= upLim; i++) {
					if(isPrime(c - 2 * i * i)) {
						foundPrime = true;
						break;
					}
				}
				if(!foundPrime) {
					System.out.println("Found it: " + c);
					break;
				}
			}
		}
	}

	public static boolean isPrime(long testVal) {
		if(testVal < 0) {
			testVal = Math.abs(testVal);
		}
        long upperTestLimit = (long)Math.sqrt(testVal);
        //because each time a factor < sqrt(testVal) is found, a factor > sqrt(testVal) is also found

        if(testVal % 2 != 0) {  //only test ODD values for primeness
	        if(testVal >= 3) {  //only look for factors of test values >= 3
	            for(long i = 3; i <= upperTestLimit; i += 2 )  //i += 2:  don't check even numbers for being a factor
	                if(testVal % i == 0)
	                    return false;  //a factor was found, so NOT prime
	        }
	        else if(testVal == 1)  //1 is not prime
	            return false;
	    }
        else { //test value is EVEN
	        if(testVal == 2)  //2 is prime
	            return true;
	        else  //evens are not prime
	        	return false;
        }
        return true;  //NO factors up to the square root of the test value were found, so test value is prime
        //otherwise the return would have been from the if block
	}

}
