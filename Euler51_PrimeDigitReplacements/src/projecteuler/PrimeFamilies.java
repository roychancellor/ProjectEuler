/**
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers,
yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.

Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
 */

package projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import royutils.RoyUtils;

public class PrimeFamilies {

	public static void main(String[] args) {
		int[] onesDigits = {1, 3, 7, 9};
		//Generate numbers with varying ones digits and test for prime family size
		int targetFamilySize = 8;
		for(int n = 1; n <= 100000; n++) {
			int numDigitsInNum = RoyUtils.numberOfDigitsIn(10 * n);
			int tenN = 10 * n;
			for(int ones = 0; ones < 4; ones++) {
				int num = tenN + onesDigits[ones];
				int maxDigitsToReplace = numDigitsInNum - 1;
				for(int d = 1; d <= maxDigitsToReplace; d++) {
					int minOfTargetFamily = sizeOfPrimePatternReplaceN(num, d, targetFamilySize);
					if(minOfTargetFamily > targetFamilySize) {
						System.out.println("Found it! " + minOfTargetFamily);
						return;
					}
				}
			}
		}
	}
	
	//Searches for the target prime family size found by replacing any N digits with the digits 0-9
	//If the target is met exactly, returns the minimum family member
	//If the target is not met exactly, returns the maximum family size
	public static int sizeOfPrimePatternReplaceN(int num, int numDigitsToReplace, int targetFamilySize) {
		int numDigitsInNum = RoyUtils.numberOfDigitsIn(num);
		if(num >= 10 && numDigitsToReplace < numDigitsInNum && targetFamilySize <= 10) {
			//Replace the specified number of digits in all combinations of positions in the number with 0...9, except the ones place,
			//and test each new number for primeness. If prime, increment the counter.
			List<Integer> digitsOfN = RoyUtils.numberToDigits(num);
			List<Integer> playground = new ArrayList<Integer>();
			List<Integer> family = new ArrayList<Integer>();
			playground.addAll(digitsOfN);
			int countPrimes = 0;
			int maxFamilySize = 0;
			int[][] indexesToReplace = getIndexesToReplace(numDigitsInNum, numDigitsToReplace);
			for(int r = 0; r < indexesToReplace.length; r++) {
				countPrimes = 0;
				for(int newDigit = (indexesToReplace[r][0] > 0 ? 0 : 1); newDigit < 10; newDigit++) {
					for(int i = 0; i < indexesToReplace[r].length; i++) {
						playground.set(indexesToReplace[r][i], newDigit);
					}
					int newNumber = RoyUtils.digitsToNumber(playground);
					if(RoyUtils.isPrime(newNumber)) {
						family.add(newNumber);
						countPrimes++;
					}
					if(countPrimes > maxFamilySize) {
						maxFamilySize = countPrimes;
					}
				}
				if(countPrimes == targetFamilySize) {
					printList(family);
					return Collections.min(family);
				} else {
					family.clear();
					playground.clear();
					playground.addAll(digitsOfN);
				}
			}
			return maxFamilySize;
		}
		return 0;
	}
	
	public static int[][] getIndexesToReplace(int numDigits, int digitsToReplace) {
		List<int[]> indexes = new ArrayList<>();
		indexes = RoyUtils.generateCombos(numDigits - 1, digitsToReplace);
		int[][] toReturn = new int[indexes.size()][digitsToReplace];
		for(int i = 0; i < indexes.size(); i++) {
			for(int j = 0; j < indexes.get(i).length; j++) {
				toReturn[i][j] = indexes.get(i)[j];
			}
		}
		return toReturn;		
	}
	
	public static void printList(List<Integer> listToPrint) {
		for(int i = 0; i < listToPrint.size(); i++) {
			System.out.print(listToPrint.get(i) + " ");
		}
		System.out.println();
	}
	
	public static int sizeOfPrimePatternReplaceOne(int n, int targetFamilySize) {
		if(n >= 10) {
			//Replace each digit from the most significant down to the tens place with all the digits 0...9 inclusive
			//and test each new number for primeness. If prime, increment the counter.
			//Return the count of primes.
			List<Integer> digitsOfN = RoyUtils.numberToDigits(n);
			List<Integer> playground = new ArrayList<Integer>();
			List<Integer> family = new ArrayList<Integer>();
			playground.addAll(digitsOfN);
			int countPrimes = 0;
			int maxFamilySize = 0;
			int numDigitsToReplace = RoyUtils.numberOfDigitsIn(n) - 1; //don't replace the ones place
			for(int indexOfDigitToReplace = 0; indexOfDigitToReplace < numDigitsToReplace; indexOfDigitToReplace++) {
				countPrimes = 0;
				for(int newDigit = (indexOfDigitToReplace > 0 ? 0 : 1); newDigit < 10; newDigit++) {
					playground.set(indexOfDigitToReplace, newDigit);
					int newNumber = RoyUtils.digitsToNumber(playground);
					if(RoyUtils.isPrime(newNumber)) {
						family.add(newNumber);
						countPrimes++;
					}
					if(countPrimes > maxFamilySize) {
						maxFamilySize = countPrimes;
					}
				}
				if(countPrimes == targetFamilySize) {
					return Collections.min(family);
				} else {
					family.clear();
					playground.clear();
					playground.addAll(digitsOfN);
				}
			}
			return maxFamilySize;
		}
		return 0;
	}	

	//Searches for the target prime family size found by replacing any TWO digits with the digits 0-9
	//If the target is met exactly, returns the minimum family member
	//If the target is not met exactly, returns the maximum family size
	public static int sizeOfPrimePatternReplaceTwo(int n, int targetFamilySize) {
		if(n >= 100) {
			//Replace each PAIR of digits from the most significant down to the tens place with all the digits 0...9 inclusive
			//and test each new number for primeness. If prime, increment the counter.
			//Return the count of primes.
			List<Integer> digitsOfN = RoyUtils.numberToDigits(n);
			List<Integer> playground = new ArrayList<Integer>();
			List<Integer> family = new ArrayList<Integer>();
			playground.addAll(digitsOfN);
			int countPrimes = 0;
			int maxFamilySize = 0;
			int highestIndexToReplace = RoyUtils.numberOfDigitsIn(n) - 2;
			for(int indexOfFirstDigit = 0; indexOfFirstDigit <= highestIndexToReplace - 1; indexOfFirstDigit++) {
				for(int indexOfSecondDigit = indexOfFirstDigit + 1; indexOfSecondDigit <= highestIndexToReplace; indexOfSecondDigit++) {
					countPrimes = 0;
					for(int newDigit = (indexOfFirstDigit > 0 ? 0 : 1); newDigit < 10; newDigit++) {
						playground.set(indexOfFirstDigit, newDigit);
						playground.set(indexOfSecondDigit, newDigit);
						int newNumber = RoyUtils.digitsToNumber(playground);
						if(RoyUtils.isPrime(newNumber)) {
							family.add(newNumber);
							countPrimes++;
						}
						if(countPrimes > maxFamilySize) {
							maxFamilySize = countPrimes;
						}
					}
					if(countPrimes == targetFamilySize) {
						return Collections.min(family);
					} else {
						family.clear();
						playground.clear();
						playground.addAll(digitsOfN);
					}
				}
			}
			return maxFamilySize;
		}
		return 0;
	}	

	//Searches for the target prime family size found by replacing any THREE digits with the digits 0-9
	//If the target is met exactly, returns the minimum family member
	//If the target is not met exactly, returns the maximum family size
	public static int sizeOfPrimePatternReplaceThree(int n, int targetFamilySize) {
		if(n >= 1000) {
			//Replace each TRIPLE of digits from the most significant down to the tens place with all the digits 0...9 inclusive
			//and test each new number for primeness. If prime, increment the counter.
			//Return the count of primes.
			List<Integer> digitsOfN = RoyUtils.numberToDigits(n);
			List<Integer> playground = new ArrayList<Integer>();
			List<Integer> family = new ArrayList<Integer>();
			playground.addAll(digitsOfN);
			int countPrimes = 0;
			int maxFamilySize = 0;
			int highestIndexToReplace = RoyUtils.numberOfDigitsIn(n) - 2;
			for(int indexOfFirstDigit = 0; indexOfFirstDigit <= highestIndexToReplace - 2; indexOfFirstDigit++) {
				for(int indexOfSecondDigit = indexOfFirstDigit + 1; indexOfSecondDigit <= highestIndexToReplace - 1; indexOfSecondDigit++) {
					for(int indexOfThirdDigit = indexOfSecondDigit + 1; indexOfThirdDigit <= highestIndexToReplace; indexOfThirdDigit++) {
						countPrimes = 0;
						for(int newDigit = (indexOfFirstDigit > 0 ? 0 : 1); newDigit < 10; newDigit++) {
							playground.set(indexOfFirstDigit, newDigit);
							playground.set(indexOfSecondDigit, newDigit);
							int newNumber = RoyUtils.digitsToNumber(playground);
							if(RoyUtils.isPrime(newNumber)) {
								family.add(newNumber);
								countPrimes++;
							}
							if(countPrimes > maxFamilySize) {
								maxFamilySize = countPrimes;
							}
						}
						if(countPrimes == targetFamilySize) {
							return Collections.min(family);
						} else {
							family.clear();
							playground.clear();
							playground.addAll(digitsOfN);
						}
					}
				}
			}
			return maxFamilySize;
		}
		return 0;
	}	

	
}
