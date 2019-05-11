//all of the methods required to determine if two numbers are amicable
import java.lang.Math;
public class Amicable {
	public long sumDivisors(long n) {
		long sumDiv = 1;  //1 is always a proper divisor, so seed the sum with 1
		long maxFac=(long)Math.ceil(Math.sqrt(n));  //only need to test for factors up to the square root of n		
        long i=2;
        while(i<=maxFac) {
            if(n % i == 0) {
                sumDiv += i;  //each time a proper divisor < sqrt(n) is found, a divisor > sqrt(n) is also found
                sumDiv += (n/i);  //the corresponding proper divisor to i
            }
            i++;
        }
        //check for perfect square:  If found, subtract the square root of n from the sum to avoid double summing it
        if(Math.ceil(Math.sqrt(n))*Math.ceil(Math.sqrt(n)) == n)
            sumDiv -= Math.sqrt(n);
		
		return sumDiv;
	}
	
	public long getAmicable(long a) {
		long b = sumDivisors(a);
		if(a == sumDivisors(b)) return b;
		return 0L;
	}
}
