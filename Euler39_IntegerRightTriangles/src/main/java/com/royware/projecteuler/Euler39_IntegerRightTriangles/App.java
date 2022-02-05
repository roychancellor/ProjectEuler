package com.royware.projecteuler.Euler39_IntegerRightTriangles;

import java.util.HashMap;
import java.util.Map;

/**
 If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?
 *
 */
public class App {
    public static void main( String[] args ) {
    	int perim = 0;
    	Map<Integer, Integer> perimMap = new HashMap<Integer, Integer>();
    	for(int m = 2; m < 100; m++) {
    		for(int n = 1; n < m; n++) {
				//If n has opposite parity to m and GCD(m,n) = 1, then compute
				//This is so the triple that is generated is primitive only to avoid counting duplicates
    			//Visit http://mathforum.org/library/drmath/view/55811.html for an explanation
				if(((m % 2 == 0 && n % 2 == 1) || (m % 2 == 1 && n % 2 == 0)) && gcd(m, n) == 1) {
					for(int k = 1; k <= 20; k++) {
	    				perim = computePerimeterOfTriple(m, n, k);
						if(perim <= 1000) {
							if(perimMap.containsKey(perim)) {
								perimMap.put(perim, perimMap.get(perim) + 1);
							} else {
								perimMap.put(perim, 1);
							}
						} 
    				}
				}
    		}
    	}
    	
    	//Find the perimeter having the maximum number of triples
    	perim = 1000;
    	int numTriples = 0;
    	int maxTriples = 0;
    	int maxPerim = 0;
    	while(perim > 0) {
    		if(perimMap.containsKey(perim)) {
    			numTriples = perimMap.get(perim);
    			if(numTriples > maxTriples) {
    				maxTriples = numTriples;
    				maxPerim = perim;
    			}
    		}
    		perim--;
    	}
    	
    	System.out.println("The perimeter with maximum number of triples is " + maxPerim + " with " + perimMap.get(maxPerim) + " triples.");
    }
    
    //Uses Euclid's formula to compute the sides of a Pythagorean triple, then returns its perimeter
    public static int computePerimeterOfTriple(int m, int n, int k) {
    	int a = 0;
    	int b = 0;
    	int c = 0;
    	
    	a = k*(m*m - n*n);
    	b = k*(2*m*n);
    	c = k*(m*m + n*n);
    	
    	return a + b + c;
    }
    
 // Recursive function to return gcd of a and b 
    //Retrieved from https://www.geeksforgeeks.org/c-program-find-gcd-hcf-two-numbers/
    public static int gcd(int a, int b) 
    { 
        // Everything divides 0  
        if (a == 0) 
          return b; 
        if (b == 0) 
          return a; 
       
        // base case 
        if (a == b) 
            return a; 
       
        // a is greater 
        if (a > b) 
            return gcd(a-b, b); 
        return gcd(a, b-a); 
    } 
}
