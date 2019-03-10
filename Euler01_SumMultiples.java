/*Project Euler #1:  If we list all the natural numbers below 10 that are multiples of 3 or 5,
we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.*/

public class SumMultiples
{
    public static void main( String args[] ) {
        int i, sum = 0;
        
        //brute force:  iterate from 3 to 999 and test whether each number is
        //a multiple of 3 or 5 and, if so, add the multiple to the sum
        for(i=3; i<1000; i++) {
            if(i % 3 == 0 || i % 5 == 0)
                sum += i;
        }
        System.out.println("The sum of the multiples of 3 or 5 below 1000 is " + sum);
    }  //end main
}
