package com.royware.projecteuler.Euler38_PandigitalMultiples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 Take the number 192 and multiply it by each of 1, 2, and 3:

192 × 1 = 192
192 × 2 = 384
192 × 3 = 576

By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
 *
 */
public class App {
	private static int LOWPAN = 1;
	private static int HIGHPAN = 9;

	public static void main( String[] args ) {
		int x = 2;
		int n = 2;
		int conProd = 0;
		int maxProd = 0;
		do {  //outer loop for checking different integers
			n = 2;
			conProd = concatProduct(x, n);
			while(conProd <= 987654321) {  //inner loop for checking different values of n for each integer x
				if(isPandigital(digitsToSet(conProd))) {
					System.out.println("\tFound pandigital 1-9: " + conProd + " with x = " + x + " and n = " + n);
					if(conProd > maxProd) {
						maxProd = conProd;
						System.out.println("Found a 1-9 pandigital concatendated product: " + maxProd + " with x = " + x + " and n = " + n);
					}
				}
				n++;
				conProd = concatProduct(x, n);
			}
			x += 1;
		} while(x < 10000);
		
		System.out.println("The maximum 1-9 pandigital concatenated product is " + maxProd);
	}
	
	//Computes the concatenated product of x with the numbers 1, 2, ..., n where n > 1
	public static int concatProduct(int x, int n) {
		if(x > 0 && n > 1) {
			List<Integer> digits = new ArrayList<Integer>();
			for(int i = 1; i <= n; i++) {
				concatNewDigits(digits, x * i);
			}
			return digitsToNumber(digits);
		} else {
			return 0;
		}
	}
	
	//Concatenates the digits of num to the end of the digits list, always with most significant digit first
	public static void concatNewDigits(List<Integer> digits, int num) {
		List<Integer> tempList = new ArrayList<Integer>();
		int digit = 0;
		while(num > 0) {
			digit = num % 10;
			tempList.add(digit);
			num /= 10;
		}
		//Make the most significant digit the first element of the digit list for the number being concatenated
		//This will always make the list in the correct numeric order
		Collections.reverse(tempList);
		//Concatenate the new digits to the existing digits
		digits.addAll(tempList);
	}
	
	//Converts a list of digits assumed to be in numeric order into an integer
	public static int digitsToNumber(List<Integer> digits) {
		int num = 0;
		//Assumes element 0 is the most significant digit
		int maxPower = digits.size() - 1;
		for(int i = maxPower; i >= 0; i--) {
			num += digits.get(maxPower - i) * Math.pow(10, i);
		}
		return num;
	}
	
	//Determines if a set of digits represents a 1-9 pandigital number
	public static boolean isPandigital(Set<Integer> digits) {		
		Integer[] checkArray = {1,2,3,4,5,6,7,8,9};
		Set<Integer> panCheck = new HashSet<Integer>(Arrays.asList(checkArray));
		
		if(digits != null) {
			//Check for complete intersection of the set of digits and the panCheck set
			return (digits.containsAll(panCheck) && panCheck.containsAll(digits));
		} else {
			return false;
		}
	}
	
	//Converts a number into a set of numbers for testing if it is pandigital 1-9
	//Optimizes by stopping the process when a duplicate digit occurs, since that automatically won't work
	//(multiplying by 1 gives the same number which, if it has duplicate digits, is automatically not pandigital)
	public static Set<Integer> digitsToSet(int val) {
		Set<Integer> digitSet = new HashSet<Integer>();
		int digit = 0;
		while(val > 0) {
			digit = val % 10;
			if(digit >= LOWPAN && digit <= HIGHPAN && !digitSet.contains((Integer)digit)) {
				digitSet.add(digit);
			}
			else {
				return null;
			}
			val /= 10;
		}
		
		return digitSet;
	}

}
