package projecteuler;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import royutils.RoyUtils;

public class PermutedTest {

	@Test
	public void testTestPermutationsOfX() {
		assertTrue(true);
	}

	@Test
	public void testIsPermutationEqual() {
		List<Integer> listOne = RoyUtils.numberToDigits(125874);
		List<Integer> listTwo = RoyUtils.numberToDigits(251748);
		assertTrue(Permuted.isPermutationEqual(listOne, listTwo));
	}

}
