/*
 The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?
 */

public class Euler14_LongestCollatz
{
    public static void main(String args[]) {
        //Start with a brute force solution, beginning with n = 1
        long n=13, longestCollatz=0, maxN=1000000, nLongest=0;
        
        while(n < maxN) {  //n is the starting value of the Collatz sequence
            long collatzCount=0, collatz=n;
            while(collatz != 1) { //the Collatz conjecture says all sequences will end in 1 and has not been disproved, sooo...
                //apply the Collatz sequence rules
                if(collatz%2 == 0)
                    collatz /= 2;
                else
                    collatz = 3*collatz + 1;
                collatzCount++;  //count the terms in the sequence for the current value of n
            }
            collatzCount++;  //accounts for the ending value of 1 in the sequence
            if(collatzCount > longestCollatz) { //check for the longest sequence
                longestCollatz = collatzCount;
                nLongest = n;
            }
            n++;  //increment the starting value of the sequence
        }
        System.out.println("The starting number under " + maxN + " that produces the longest collatz sequence is");
        System.out.println(nLongest + " which is " + longestCollatz + " terms");
    }
}
