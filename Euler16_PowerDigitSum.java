/*2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
What is the sum of the digits of the number 2^1000?
 */
//Try this by multiplying by two 1000 times and storing the digits in an array.
//There should be 1000log2 digits (approx 302)

import java.util.ArrayList;
public class Euler16_PowerDigitSum
{
    public static void main(String args[]) {
        //int [] digits = new int[302];
        ArrayList<Integer> digits = new ArrayList<Integer>();
        int prod=1;
        
        System.out.println("\n*****");
        digits.add(1);  //element 0
        int numDigits;
        for(int i=1; i<=1000; i++) {
            numDigits=digits.size();
            int j=0;
            int carry=0;
            while(j<numDigits) {
                prod=digits.get(j)*2 + carry;
                carry=0;
                if(prod<10)
                    digits.set(j,prod);  //update the jth digit
                else {  //the product exceeded 10, so set the carry value
                    digits.set(j,prod%10);
                    carry=(prod/10)%10;
                }
                j++;
            }
            if(carry>0)
                digits.add(carry);  //make the next digit the carry digit
            /*for(int k=digits.size()-1; k>=0; k--)
                System.out.print(digits.get(k));
            System.out.println();*/
        }
        //find the sum of the digits
        long sum=0;
        numDigits=digits.size();
        for(int i=0; i<numDigits; i++)
            sum += digits.get(i);
        System.out.println("The sum of " + numDigits + " digits is " + sum);
    }
}
