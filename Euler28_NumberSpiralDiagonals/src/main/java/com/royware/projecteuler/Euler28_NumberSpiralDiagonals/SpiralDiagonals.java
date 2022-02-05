package com.royware.projecteuler.Euler28_NumberSpiralDiagonals;

public class SpiralDiagonals {
	
	public long sumFourCornersOfRing(int N) {
		//Computes the sum of the four corner numbers of a ring in the number spiral
		//Formula comes from N^2 + N^2 - 1*(N-1) + N^2 - 2*(N-1) + N^2 - 3*(N-1)
		//which reduces algebraically to 4*N^2 - (N-1)*sum(0, 1, 2, 3)
		//which reduces to 4N^2 - 6(N-1) = 4N^2 - 6N + 6 = 2(N(2N-3) + 3)
		return 2 * (N * (2 * N - 3) + 3);
	}
}
