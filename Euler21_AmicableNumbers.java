/*
Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.
*/
public class Euler21_AmicableNumbers {

	public static void main(String[] args) {
		long sumAmic = 0;
		long b=0;
		long maxAmic = 10000;
		Amicable am = new Amicable();  //didn't really need to create a separate class, but did so for practice
		for(int a=2; a<=maxAmic; a++) {  //iterate from 2 to the maximum amicable number required
			b = am.getAmicable(a);
			//getAmicable will return 0 if a and b are not amicable, otherwise b is amicable to a
			//by rule, do not count amicable pairs where a = b
			//each amicable pair will occur twice as (a, b) and (b, a), but only count this as one pair, so choose the pair with b > a
			if(b > 0 && a != b && b > a) {
				sumAmic += a;
				sumAmic += b;
				System.out.println(a + " and " + b + " are amicable");
			}
		}
		System.out.println("The sum of amicable numbers under " + maxAmic + " is " + sumAmic);
	}
}
