/**
 If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are
 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
how many letters would be used?

NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains
23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing
out numbers is in compliance with British usage.
 */

import java.util.Map;
import java.util.HashMap;
public class Euler17_NumberLetterCounts
{
    public static void main(String args[]) {
        long charCount=0;
        String numStr="";
        for(int i=1; i<=1000; i++) {
            //call NumbersToWords for i
            numStr = NumbersToWords(i);
            //get length of the string and add to total character count
            charCount += numStr.length();  //could just do function name.length()
        }
        //print the total character count
        System.out.println("There are a total of " + charCount + " letters used");
    }
    
    //Converts an integer into its English words, e.g. 342 is three hundred and forty two
    //Returns a string without spaces or hyphens
    //anything below 100 gets "and", for example 115 is one hundred AND fifteen
    public static String NumbersToWords(int numToParse) {
        //could this be done with a regular expression????
        //define the names for the base numbers
        Map<Integer, String> numMap = new HashMap<Integer, String>();
        numMap.put(1, "one");
        numMap.put(2, "two");
        numMap.put(3, "three");
        numMap.put(4, "four");
        numMap.put(5, "five");
        numMap.put(6, "six");
        numMap.put(7, "seven");
        numMap.put(8, "eight");
        numMap.put(9, "nine");
        numMap.put(10, "ten");        
        numMap.put(11, "eleven");        
        numMap.put(12, "twelve");        
        numMap.put(13, "thirteen");        
        numMap.put(14, "fourteen");        
        numMap.put(15, "fifteen");        
        numMap.put(16, "sixteen");        
        numMap.put(17, "seventeen");        
        numMap.put(18, "eighteen");        
        numMap.put(19, "nineteen");        
        numMap.put(20, "twenty");        
        numMap.put(30, "thirty");        
        numMap.put(40, "forty");        
        numMap.put(50, "fifty");        
        numMap.put(60, "sixty");        
        numMap.put(70, "seventy");        
        numMap.put(80, "eighty");        
        numMap.put(90, "ninety");        
        //HUNDREDS just use the numMap definitions with the word "hundred" appended
        //THOUSAND:  there is only one word "onethousand")
        
        //logic to create the word string
        String numStr="";
        if(numToParse <= 20)  //just look up the word directly
            numStr += numMap.get(numToParse);
        else if(numToParse < 100) {
            if(numToParse%10 == 0)  //30, 40, 50, etc.
                numStr = numMap.get(numToParse);
            else {  //get the tens and ones places separately
                numStr += numMap.get(numToParse - numToParse%10);  //tens
                numStr += numMap.get(numToParse%10);  //ones
            }
        }
        else if(numToParse < 1000) {
            numStr += numMap.get((numToParse/100)%10);  //hundreds digit
            numStr += "hundred";
            if(numToParse%100 != 0) {  //not 100, 200, 300, etc.
                numStr += "and";
                //build the tens and ones place words
                if((numToParse%100)%10 == 0)  //30, 40, 50, etc.
                    numStr += numMap.get(numToParse%100);
                else if((numToParse%100)<=20)  //just look up directly
                    numStr += numMap.get(numToParse%100);
                else {  //get the tens and ones place separately
                    numStr += numMap.get(numToParse%100 - (numToParse%100)%10);  //tens
                    numStr += numMap.get((numToParse%100)%10);  //ones
                }
            }
        }
        else //numToParse = 1000
            numStr = "onethousand";
        System.out.println("n=" + numToParse + " is " + numStr);
        return numStr;
    }
}