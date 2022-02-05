/**
 * Project Euler Problem #25: What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */

package com.royware.projecteuler;

import java.math.BigDecimal;

public class Euler25_1000_Digit_Fibonacci_Index {
	public static void main(String[] args) {		
		BigDecimal nthFib = new BigDecimal("0");
		BigDecimal fibN_2 = new BigDecimal("1");
		BigDecimal fibN_1 = new BigDecimal("1");
		
		int numDigits = 1;
		int fibIndex = 3;
		while(numDigits < 1000) {
			//Make the current Fibonacci number by summing the previous two
			nthFib = fibN_2.add(fibN_1);
			
			//Reset the previous two numbers in the sequence
			fibN_2 = fibN_1;
			fibN_1 = nthFib;
			
			//Check the number of digits
			numDigits = nthFib.precision();
			
			//Only increment the Fibonacci number index when the number of digits is still < 1000
			if(numDigits < 1000)
				fibIndex++;
		}
		System.out.println("Index: " + fibIndex + " has " + nthFib.precision() + " digits and is:\n" + nthFib);
	}
}
