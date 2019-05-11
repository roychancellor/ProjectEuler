//all of the methods required to determine if two numbers are amicable
import java.lang.Math;
public class Amicable {
	public long sumDivisors(long n) {  //computes the sum of the proper divisors of n
		long sumDiv = 1;  //1 is always a proper divisor, so seed the sum with 1
		long maxFac=(long)Math.ceil(Math.sqrt(n));  //only need to test for factors up to the square root of n
        for(long f=2; f<=maxFac; f++) {
            if(n % f == 0) {
                sumDiv += f;  //each time a proper divisor < sqrt(n) is found, a divisor > sqrt(n) is also found
                sumDiv += (n/f);  //...the corresponding proper divisor to f
            }
        }
        //check for perfect square:  If found, subtract the square root of n from the sum to avoid double summing it
        if(Math.ceil(Math.sqrt(n))*Math.ceil(Math.sqrt(n)) == n)
            sumDiv -= Math.sqrt(n);
		
		return sumDiv;
	}
	
	public long getAmicable(long a) {  //returns the amicable number to a, if it exists; otherwise returns 0
		//definition of amicable numbers:  if d(a) = b and d(b) = a for a != b, then a and b are amicable, where d(n) is the sum of proper divisors of n
		long b = sumDivisors(a);
		if(a == sumDivisors(b)) return b;
		return 0L;
	}
}
