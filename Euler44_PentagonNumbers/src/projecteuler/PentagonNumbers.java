package projecteuler;

public class PentagonNumbers {

	public static void main(String[] args) {
		long minDiff = (long)Double.POSITIVE_INFINITY;
		long pj = 0;
		long pk = 0;
		long pjMin = 0;
		long pkMin = 0;
		long sum = 0;
		long diff = 0;
		for(long nj = 1; nj <= 10000; nj++) {
			pj = makePentagonalNumber(nj);
			for(long nk = nj + 1; nk <= 10001; nk++) {
				pk = makePentagonalNumber(nk);
				sum = pj + pk;
				diff = pk - pj;
				if(isPentagonalNumber(diff) && isPentagonalNumber(sum)) {
					System.out.println("Found a pair: P(" + nj + ") = " + pj + ", P(" + nk + ") = " + pk + ", D = " + diff);
					if(diff < minDiff) {
						minDiff = diff;
						pjMin = pj;
						pkMin = pk;
					}
				}
			}
		}
		
		System.out.println("The pair of pentagonal numbers whose sum & difference is pentagonal and distance is minimum is: (" + pjMin + ", " + pkMin + ")");
		System.out.println("The minimum difference is D = " + minDiff);
	}

	public static boolean isPentagonalNumber(long num) {
		//comes from the solution to 3n^2 - n - 2Pn = 0, where n is the pentagonal number index and Pn is a pentagonal number
		//If the solution, n, is an integer, then num is a pentagonal number
		if(isInteger((1 + Math.sqrt(1 + 24 * num)) / 6)) {
			return true;
		}
		return false;
	}
	
	public static boolean isInteger(double num) {
		if(Math.abs(num - (long)num) < 0.000000001) {
			return true;
		}
		return false;
	}

	//Generates the nth pentagonal number
	public static long makePentagonalNumber(long n) {
		return n * (3 * n - 1) / 2;
	}
}
