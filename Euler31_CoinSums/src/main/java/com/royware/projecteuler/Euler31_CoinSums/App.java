package com.royware.projecteuler.Euler31_CoinSums;

/**
 In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?
 */
public class App {
    public static void main( String[] args ) {    	
    	long start = System.currentTimeMillis();
    	System.out.println("The fast way gives " + numWaysFast() + " ways in " + (System.currentTimeMillis() - start) + " ms");
    	
    	start = System.currentTimeMillis();
    	System.out.println("The brute force way gives " + numWaysBruteForce() + " ways in " + (System.currentTimeMillis() - start) + " ms");
    }
    
    public static int valueOfCoins(int one, int two, int five, int ten, int twenty, int fifty, int pound) {
    	return (one + 2*two + 5*five + 10*ten + 20*twenty + 50*fifty + 100*pound);
    }
    
    public static int numWaysBruteForce() {
    	int ways = 0;
    	//Just brute force the calculations
    	for(int pound = 0; pound <= 2; pound++) {
    		for(int fifty = 0; fifty <= 4; fifty++) {
    			for(int twenty = 0; twenty <= 10; twenty++) {
    				for(int ten = 0; ten <= 20; ten++) {
    					for(int five = 0; five <= 40; five ++) {
    						for(int two = 0; two <= 100; two++) {
    							for(int one = 0; one <= 200; one++) {
    								if(valueOfCoins(one, two, five, ten, twenty, fifty, pound) == 200) {
//    									System.out.println(one + "\t" + two + "\t" + five + "\t" + ten + "\t" + twenty + "\t" + fifty + "\t" + pound);
    									ways++;
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	ways++;  //count the two-pound coin by itself    
    	
    	return ways;
    }
    
    //Comes from the write-up on Project Euler problem #31 (after I solved it with brute force)
    public static int numWaysFast() {
    	int[] coins = {1,2,5,10,20,50,100,200};
    	int amount = 200;
    	int[] ways = new int[amount + 1];
    	
    	ways[0] = 1;
    	for(int i = 0; i < 8; i++) {
    		for(int j = coins[i]; j <= amount; j++) {
    			ways[j] = ways[j] + ways[j - coins[i]];
    		}
    	}
    	
    	return ways[amount];
    }
}
