package com.royware.projecteuler.Euler40_ChampernownesConstant;

/**
 An irrational decimal fraction is created by concatenating the positive integers:

0.123456789101112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
 *
 */
public class App {
    public static void main( String[] args ) {
    	StringBuilder num = new StringBuilder(".1234567891011121314151617181920");
    	for(int i = 21; i <= 1000000; i++) {
    		num.append(i + "");
    	}
    	
    	System.out.println("The product is: " + (1 * d(num, 10) * d(num, 100) * d(num, 1000) * d(num, 10000) * d(num, 100000) * d(num, 1000000)));
    }
    
    public static int d(StringBuilder num, int digitPosition) {
    	return Integer.parseInt(num.substring(digitPosition, digitPosition + 1));
    }
}
