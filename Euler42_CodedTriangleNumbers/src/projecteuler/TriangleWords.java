/**
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value.
For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?
 */
package projecteuler;

import java.io.*;
import java.util.*;

public class TriangleWords {

	public static void main(String[] args) {
		String[] words = getWordsFromFile("/Users/roy/Dropbox/2-Programs_C_Java_BASIC/JAVA/ProjectEuler/p042_words.txt");
		
		int numTriangleWords = 0;
		for(String word : words) {
			if(isTriangleNumber(wordToValue(word))) {
				numTriangleWords++;
			}
		}
		
		System.out.println("The number of triangle words in the file is " + numTriangleWords);
	}

	public static String[] getWordsFromFile(String path) {
		StringBuilder sbWords = new StringBuilder();
		
		try {
			Scanner sc = new Scanner(new File(path));
			while(sc.hasNextLine()) {
				sbWords.append(sc.nextLine());
			}
			sc.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return sbWords.toString().split(",");
	}
	
	public static void printWords(String[] words) {
		for(String word : words) {
			System.out.println(word);
		}
	}
	
	public static int wordToValue(String word) {
		int wordValue = 0;
		for(char c : word.toCharArray()) {
			wordValue += (int)c - (int)'A' + 1;
		}
		
		return wordValue;
	}
	
	public static boolean isTriangleNumber(int num) {
		double twoN = -1 + Math.sqrt(1 + 8 * num);  //comes from the solution to n^2 + n - 2tn = 0, where n is the triangle number index and tn is a triangle number
		//If twoN is an integer, then tn is a triangle number
		if(isInteger(twoN)) {
			return true;
		}
		return false;
	}
	
	public static boolean isInteger(double num) {
		if(Math.abs(num - (int)num) < 0.0000001) {
			return true;
		}
		return false;
	}
}
