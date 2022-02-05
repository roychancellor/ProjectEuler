/**
 * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
package projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import royutils.RoyUtils;

public class Permuted {

	public static void main(String[] args) {
		int x = 11;
		boolean sameDigits = false;
		while(!sameDigits) {
			sameDigits = testPermutationsOfX(x);
			if(sameDigits) {
				System.out.println("Found it! x = " + x);
			} else {
				x++;
			}
		}
	}
	
	public static boolean testPermutationsOfX(int x) {
		//Get digits of x into a list
		//Loop through 2x, 3x, ..., 6x and put each in a list and test equality with digits of x
		//If none fails, return true
		List<Integer> digitsOfX = RoyUtils.numberToDigits(x);
		List<Integer> checkDigits = new ArrayList<Integer>();
		for(int m = 2; m <= 6; m++) {
			checkDigits = RoyUtils.numberToDigits(m*x);
			if(!isPermutationEqual(digitsOfX, checkDigits)) {
				return false;
			}
			checkDigits.clear();
		}
		return true;
	}
	
	public static boolean isPermutationEqual(List<Integer> listOne, List<Integer> listTwo) {
		Collections.sort(listOne);
		Collections.sort(listTwo);
		return listOne.equals(listTwo);
	}

}
