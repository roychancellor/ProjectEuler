package projecteuler;

import java.util.ArrayList;
import java.util.List;

public class PandigitalDivisibility {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<Integer> digits = new ArrayList<Integer>();
		long sum = 0;
		for(int a = 0; a < 10; a++) {
			for(int b = 0; b < 10; b++) {
				if(b == a) continue;
				for(int c = 0; c < 10; c++) {
					if(c == a || c == b) continue;
					for(int d = 0; d < 10; d += 2) {  //4th digit must be even
						if(d == c || d == b || d == a) continue;
						for(int e = 0; e < 10; e++) {
							if(e == d || e == c || e == b || e == a) continue;
							for(int f = 0; f <= 5; f += 5) {  //6th digit must be 0 or 5
								if(f == e || f == d || f == c || f == b || f == a) continue;
								for(int g = 0; g < 10; g++) {
									if(g == f || g == e || g == d || g == c || g == b || g == a) continue;
									for(int h = 0; h < 10; h++) {
										if(h == g || h == f || h == e || h == d || h == c || h == b || h == a) continue;
										for(int i = 0; i < 10; i++) {
											if(i == h || i == g || i == f || i == e || i == d || i == c || i == b || i == a) continue;
											for(int j = 0; j < 10; j++) {
												if(j == i || j == h || j == g || j == f || j == e || j == d || j == c || j == b || j == a) continue;
												digits.add(a);
												digits.add(b);
												digits.add(c);
												digits.add(d);
												digits.add(e);
												digits.add(f);
												digits.add(g);
												digits.add(h);
												digits.add(i);
												digits.add(j);
//												System.out.println("Testing " + digits);
												if(isSubStringDivisible(digits)) {
													sum += tenDigitsToNumber(digits);
//													System.out.println("Found one: " + digits);
												}
												digits.clear();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("The sum of all pandigital numbers that have the sub-string divisibility property is " + sum);
		System.out.println("Took " + elapsed + " ms");
	}

	public static boolean isSubStringDivisible(List<Integer> digits) {
		int[] divisors = {2,3,5,7,11,13,17};
		int n = divisors.length;
		for(int i = 0; i < n; i++) {
			if(threeDigitsToNumber(digits, i + 1) % (divisors[i]) != 0) {
				return false;
			}
		}
		return true;
	}
	
	//Creates a three digit number from the digits in a list, starting the the ath digit
	public static int threeDigitsToNumber(List<Integer> digits, int a) {
		//Assumes lowest element is the most significant digit of the three
		return 100 * digits.get(a) + 10 * digits.get(a + 1) + digits.get(a + 2);
	}
	
	//Converts a list of 10 digits assumed to be in numeric order into an integer
	public static long tenDigitsToNumber(List<Integer> digits) {
		long num = 0;
		//Assumes element 0 is the most significant digit
		int maxPower = digits.size() - 1;
		for(int i = maxPower; i >= 0; i--) {
			num += digits.get(maxPower - i) * Math.pow(10, i);
		}
		return num;
	}
}
