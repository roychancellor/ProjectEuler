/*n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!*/

import java.util.ArrayList;
public class Factorial100  //this is a modification of the technique used in Project Euler #16 that computed 2^1000 using successive multiplications by 2 with carries
{
    public static void main(String args[]) {
        ArrayList<Integer> digits = new ArrayList<Integer>();  //stores the digits of 100!
        int nFact=100;
        
        digits.add(1);  //element 0
        for(int n=2; n<=nFact; n++) {
	        if(n<10) digits = multOneDigit(digits, n);  //multiply by a one digit number
	        else if(n<100) digits = multTwoDigits(digits, n);  //the original value of digits goes in, gets multiplied by n, and the product gets returned
	        //don't need to multiply by 100 because that just adds two zeros to the end which don't contribute to the sum of digits
	    }

        //output the result to the user
        System.out.println("The factorial of " + nFact + " has " + digits.size() + " digits");
        System.out.println("and " + nFact + "! = " + printFact(digits));
        System.out.println("The sum of its digits is " + sumDigits(digits));
    }
    
    public static long sumDigits(ArrayList<Integer> digits) {
        long sum=0;
        for(int i=0; i<digits.size(); i++)
            sum += digits.get(i);
    	return sum;
    }
    
    public static String printFact(ArrayList<Integer> digits) {
    	String digitsToStr = new String();
    	//digits are stored least significant to most significant in the array list, but need to be added to the string most to least significant
    	for(int i=digits.size()-1; i>=0; i--) 
    		digitsToStr += digits.get(i);
    	return digitsToStr;
    }
    
    public static ArrayList<Integer> multOneDigit(ArrayList<Integer> number, int x) {
        int carry = 0;
        int tempProd;
        ArrayList<Integer> product = new ArrayList<Integer> ();
        
    	for(int j=0; j<number.size(); j++) {  //perform the multiplication of each of the current factorial digits with the next multiplier
        	tempProd=number.get(j)*x + carry;  //the multiplication will be at most TWO digits, so set the ones place, then set the tens place
            carry=0;  //reset the carry after each multiplication
            if(tempProd<10)
                product.add(tempProd);
            else {  //the product exceeded 10, so set the carry value
                product.add(tempProd%10);
                carry=(tempProd/10)%10;
            }
        }
        if(carry>0)
            product.add(carry);  //make the next digit the carry digit
        return product;
    }
    
    public static ArrayList<Integer> multTwoDigits(ArrayList<Integer> number, int x) {
    	ArrayList<Integer> onesProd = new ArrayList<Integer> ();
    	ArrayList<Integer> tensProd = new ArrayList<Integer> ();
    	ArrayList<Integer> factProd = new ArrayList<Integer> ();
    	int prod, carry=0;
    	
    	//MULTIPLY the number array list by the ONES place of x and store in onesProd
    	for(int j=0; j<number.size(); j++) {
        	prod=number.get(j)*(x%10) + carry;
            carry=0;  //reset the carry after each multiplication
            if(prod<10)
                onesProd.add(prod);  //update the jth digit
            else {  //the product exceeded 10, so set the carry value
                onesProd.add(prod%10);
                carry=(prod/10)%10;
            }
    	}
        if(carry > 0) onesProd.add(carry);  //add the final carry to the number (leading zero looks weird but doesn't affect result)

        //MULTIPLY the number array list by the TENS place of x and store in tensProd with a 0 tagged onto the end for "10"
    	prod = 0;
    	carry = 0;
    	tensProd.add(0);  //trailing 0 due to multiplication by 10
    	for(int j = 0; j<number.size(); j++) {
        	prod=number.get(j)*((x/10)%10) + carry;
            carry=0;  //reset the carry after each multiplication
            if(prod<10)
                tensProd.add(prod);  //set the (j+1)th digit
            else {  //the product exceeded 10, so set the carry value
                tensProd.add(prod%10);
                carry=(prod/10)%10;
            }
    	}
    	if(carry>0) tensProd.add(carry);

    	//ADD the onesProd digits with the tensProd digits and store the digits in the "factProd" array list which will get returned to the main routine
    	carry = 0;
    	int tempSum = 0;
    	for(int j=0; j<onesProd.size(); j++) {  //size of tensProd will always be one greater or equal to onesProd
    		tempSum = onesProd.get(j) + tensProd.get(j) + carry;
    		carry = 0;  //reset carry after using it
    		if(tempSum >= 10) {
    			carry = (tempSum/10)%10;
    			tempSum %= 10;  //write the ones place of the tempSum into the factProd array
    		}
    		factProd.add(tempSum);
    	}
    	if(onesProd.size() == tensProd.size() && carry > 0)  factProd.add(carry);
    	else {
        	for(int j=onesProd.size(); j<tensProd.size(); j++) {
        		factProd.add(tensProd.get(j) + carry);  //the value of carry is from the previous addition operation, if any
        		carry = 0;  //reset carry since at this point just adding 0 + tensProd so no carrying necessary
        	}
    	}
    	return factProd;
    }
}
