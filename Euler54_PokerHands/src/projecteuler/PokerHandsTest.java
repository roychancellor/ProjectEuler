package projecteuler;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import projecteuler.PokerHands.Hand;
import projecteuler.PokerHands.Player;

public class PokerHandsTest {
	PokerHands ph;
	Player p1;
	Player p2;

	@Before
	public void setupHands() {
		ph = new PokerHands();
		p1 = ph.new Player();
		p2 = ph.new Player();
	}
	
	//This test is after solving the problem to prevent regressions...the correct answer is player 1 wins 376 times
	@Test
	public void testMain() {
		assertEquals(376, PokerHands.main(null));
	}
	
	@Test
	public void testConvertCardValuesAndSuits() {
		assertEquals(10, PokerHands.getCardValue("TD".substring(0, 1)));
		assertEquals(11, PokerHands.getCardValue("JD".substring(0, 1)));
		assertEquals(12, PokerHands.getCardValue("QD".substring(0, 1)));
		assertEquals(13, PokerHands.getCardValue("KD".substring(0, 1)));
		assertEquals(14, PokerHands.getCardValue("AD".substring(0, 1)));
	}
	
	@Test
	public void testIsStraight() {
		Hand h = ph.new Hand();
		assertTrue(h.isStraight(Arrays.asList(2,3,4,5,6)));
		assertFalse(h.isStraight(Arrays.asList(2,3,4,5,7)));
	}
	
	@Test
	public void testIsFlush() {
		Hand h = ph.new Hand();
		Map<String, Integer> suitCounts = new HashMap<>();
		suitCounts.put("H", 5);
		suitCounts.put("D", 0);
		suitCounts.put("C", 0);
		suitCounts.put("S", 0);
		assertTrue(h.isFlush(suitCounts));
		suitCounts.put("H", 4);
		assertFalse(h.isFlush(suitCounts));
	}
	
	@Test
	public void testIsRoyalStraight() {
		Hand h = ph.new Hand();
		Map<Integer, Integer> valueCounts = new HashMap<>();
		valueCounts.put(10, 1);
		valueCounts.put(11, 1);
		valueCounts.put(12, 1);
		valueCounts.put(13, 1);
		valueCounts.put(14, 1);
		assertTrue(h.isRoyalStraight(valueCounts));
		valueCounts.put(14, 0);
		assertFalse(h.isRoyalStraight(valueCounts));
	}
	
	@Test
	public void testDetermineWinner() {
		String test1 = "5H 5C 6S 7S KD 2C 3S 8S 8D TD";
		PokerHands.makeHandsFromString(ph, p1, p2, test1);
		int i = 0;
		assertEquals(5, p1.getHands().get(i).getCards().get(0).getValue());
		assertEquals(5, p1.getHands().get(i).getCards().get(1).getValue());
		assertEquals(6, p1.getHands().get(i).getCards().get(2).getValue());
		assertEquals(7, p1.getHands().get(i).getCards().get(3).getValue());
		assertEquals(13, p1.getHands().get(i).getCards().get(4).getValue());
		assertEquals(2, p2.getHands().get(i).getCards().get(0).getValue());
		assertEquals(3, p2.getHands().get(i).getCards().get(1).getValue());
		assertEquals(8, p2.getHands().get(i).getCards().get(2).getValue());
		assertEquals(8, p2.getHands().get(i).getCards().get(3).getValue());
		assertEquals(10, p2.getHands().get(i).getCards().get(4).getValue());
		assertEquals(2, PokerHands.determineWinner(p1.getHands().get(i), p2.getHands().get(i)));

		String test2 = "5D 8C 9S JS AC 2C 5C 7D 8S QH";
		PokerHands.makeHandsFromString(ph, p1, p2, test2);
		i = 1;
		assertEquals(1, PokerHands.determineWinner(p1.getHands().get(i), p2.getHands().get(i)));

		String test3 = "2D 9C AS AH AC 3D 6D 7D TD QD";
		PokerHands.makeHandsFromString(ph, p1, p2, test3);
		i = 2;
		assertEquals(2, PokerHands.determineWinner(p1.getHands().get(i), p2.getHands().get(i)));

		String test4 = "4D 6S 9H QH QC 3D 6D 7H QD QS";
		PokerHands.makeHandsFromString(ph, p1, p2, test4);
		i = 3;
		assertEquals(1, PokerHands.determineWinner(p1.getHands().get(i), p2.getHands().get(i)));

		String test5 = "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D";
		PokerHands.makeHandsFromString(ph, p1, p2, test5);
		i = 4;
		assertEquals(1, PokerHands.determineWinner(p1.getHands().get(i), p2.getHands().get(i)));

		String test6 = "8C TS KC 9H 4S 7D 2S 5D 3S AC";
		PokerHands.makeHandsFromString(ph, p1, p2, test6);
		i = 5;
		assertEquals(2, PokerHands.determineWinner(p1.getHands().get(i), p2.getHands().get(i)));

		String test7 = "3S KS QS 7H KH 7D 5H 5D JD AD";
		PokerHands.makeHandsFromString(ph, p1, p2, test7);
		i = 6;
		assertEquals(1, PokerHands.determineWinner(p1.getHands().get(i), p2.getHands().get(i)));
	}
	
	@Test
	public void testComputeScoreOfHand() {
		assertEquals(1, PokerHands.computeScoreOfHand(makeHighCard()));
		assertEquals(2, PokerHands.computeScoreOfHand(makeOnePair()));
		assertEquals(3, PokerHands.computeScoreOfHand(makeTwoPairs()));
		assertEquals(4, PokerHands.computeScoreOfHand(makeThreeOfAKind()));
		assertEquals(5, PokerHands.computeScoreOfHand(makeStraight()));
		assertEquals(6, PokerHands.computeScoreOfHand(makeFlush()));
		assertEquals(7, PokerHands.computeScoreOfHand(makeFullHouse()));
		assertEquals(8, PokerHands.computeScoreOfHand(makeFourOfAKind()));
		assertEquals(9, PokerHands.computeScoreOfHand(makeStraightFlush()));
		assertEquals(10, PokerHands.computeScoreOfHand(makeRoyalFlush()));
	}

	@Test
	public void testClassifyHand() {
		assertEquals("highCard", PokerHands.classifyHand(makeHighCard()));
		assertEquals("onePair", PokerHands.classifyHand(makeOnePair()));
		assertEquals("twoPairs", PokerHands.classifyHand(makeTwoPairs()));
		assertEquals("threeOfAKind", PokerHands.classifyHand(makeThreeOfAKind()));
		assertEquals("straight", PokerHands.classifyHand(makeStraight()));
		assertEquals("flush", PokerHands.classifyHand(makeFlush()));
		assertEquals("fullHouse", PokerHands.classifyHand(makeFullHouse()));
		assertEquals("fourOfAKind", PokerHands.classifyHand(makeFourOfAKind()));
		assertEquals("straightFlush", PokerHands.classifyHand(makeStraightFlush()));
		assertEquals("royalFlush", PokerHands.classifyHand(makeRoyalFlush()));
	}

	@Test
	public void testGetHighCard() {
		Hand h = makeHighCard(14);
		assertEquals(14, h.getHighCard());
		h = makeStraight(6);
		assertEquals(6, h.getHighCard());
	}
	
	@Test
	public void testBreakTheTie() {
		assertEquals(1, PokerHands.breakTheTie(makeHighCard(14), makeHighCard(13), 1));
		assertEquals(2, PokerHands.breakTheTie(makeHighCard(13), makeHighCard(14), 1));
		assertEquals(0, PokerHands.breakTheTie(makeHighCard(14), makeHighCard(14), 1));

		assertEquals(1, PokerHands.breakTheTie(makeOnePair(14, 13), makeOnePair(13, 12), 2));
		assertEquals(1, PokerHands.breakTheTie(makeOnePair(14, 13), makeOnePair(14, 12), 2));
		assertEquals(2, PokerHands.breakTheTie(makeOnePair(13, 12), makeOnePair(14, 13), 2));
		assertEquals(2, PokerHands.breakTheTie(makeOnePair(14, 12), makeOnePair(14, 13), 2));
		assertEquals(0, PokerHands.breakTheTie(makeOnePair(14, 13), makeOnePair(14, 13), 2));

		assertEquals(1, PokerHands.breakTheTie(makeTwoPairs(14, 13, 12), makeTwoPairs(13, 12, 11), 3));
		assertEquals(1, PokerHands.breakTheTie(makeTwoPairs(14, 13, 12), makeTwoPairs(14, 12, 11), 3));
		assertEquals(1, PokerHands.breakTheTie(makeTwoPairs(14, 13, 12), makeTwoPairs(14, 13, 11), 3));
		assertEquals(2, PokerHands.breakTheTie(makeTwoPairs(13, 12, 11), makeTwoPairs(14, 13, 12), 3));
		assertEquals(2, PokerHands.breakTheTie(makeTwoPairs(14, 12, 11), makeTwoPairs(14, 13, 12), 3));
		assertEquals(2, PokerHands.breakTheTie(makeTwoPairs(14, 13, 11), makeTwoPairs(14, 13, 12), 3));
		assertEquals(0, PokerHands.breakTheTie(makeTwoPairs(14, 13, 12), makeTwoPairs(14, 13, 12), 3));

		assertEquals(1, PokerHands.breakTheTie(makeThreeOfAKind(14, 13), makeThreeOfAKind(13, 12), 4));
		assertEquals(2, PokerHands.breakTheTie(makeThreeOfAKind(13, 12), makeThreeOfAKind(14, 13), 4));

		assertEquals(1, PokerHands.breakTheTie(makeStraight(10), makeStraight(9), 5));
		assertEquals(2, PokerHands.breakTheTie(makeStraight(9), makeStraight(10), 5));
		assertEquals(0, PokerHands.breakTheTie(makeStraight(10), makeStraight(10), 5));

		assertEquals(1, PokerHands.breakTheTie(makeFlush("D", 14), makeFlush("H", 13), 6));
		assertEquals(2, PokerHands.breakTheTie(makeFlush("D", 13), makeFlush("H", 14), 6));
		assertEquals(0, PokerHands.breakTheTie(makeFlush("D", 14), makeFlush("H", 14), 6));

		assertEquals(1, PokerHands.breakTheTie(makeFullHouse(14, 13), makeFullHouse(12, 13), 7));
		assertEquals(2, PokerHands.breakTheTie(makeFullHouse(12, 13), makeFullHouse(14, 13), 7));

		assertEquals(1, PokerHands.breakTheTie(makeFourOfAKind(14), makeFourOfAKind(13), 8));
		assertEquals(2, PokerHands.breakTheTie(makeFourOfAKind(13), makeFourOfAKind(14), 8));

		assertEquals(1, PokerHands.breakTheTie(makeStraightFlush(14, "D"), makeStraightFlush(13, "H"), 9));
		assertEquals(2, PokerHands.breakTheTie(makeStraightFlush(13, "D"), makeStraightFlush(14, "H"), 9));
		assertEquals(0, PokerHands.breakTheTie(makeStraightFlush(14, "D"), makeStraightFlush(14, "H"), 9));

		assertEquals(0, PokerHands.breakTheTie(makeRoyalFlush("D"), makeRoyalFlush("H"), 10));
	}
	
	private Hand makeHighCard() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(4, "H"));
		h.getCards().add(ph.new Card(6, "S"));
		h.getCards().add(ph.new Card(8, "C"));
		h.getCards().add(ph.new Card(10, "D"));
		return h;
	}

	private Hand makeHighCard(int highCard) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(4, "H"));
		h.getCards().add(ph.new Card(6, "S"));
		h.getCards().add(ph.new Card(8, "C"));
		h.getCards().add(ph.new Card(highCard, "D"));
		h.setHighCard(highCard);
		return h;
	}

	private Hand makeOnePair() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(2, "H"));
		h.getCards().add(ph.new Card(6, "S"));
		h.getCards().add(ph.new Card(8, "C"));
		h.getCards().add(ph.new Card(10, "D"));
		return h;
	}

	private Hand makeOnePair(int pairCard, int highCard) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(pairCard, "D"));
		h.getCards().add(ph.new Card(pairCard, "H"));
		h.getCards().add(ph.new Card(highCard - 3, "S"));
		h.getCards().add(ph.new Card(highCard - 2, "C"));
		h.getCards().add(ph.new Card(highCard, "D"));
		h.classifyHand(h.getCards());
		return h;
	}

	private Hand makeTwoPairs() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(2, "H"));
		h.getCards().add(ph.new Card(6, "S"));
		h.getCards().add(ph.new Card(6, "C"));
		h.getCards().add(ph.new Card(10, "D"));
		return h;
	}

	private Hand makeTwoPairs(int pair1, int pair2, int single) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(pair1, "D"));
		h.getCards().add(ph.new Card(pair1, "H"));
		h.getCards().add(ph.new Card(pair2, "S"));
		h.getCards().add(ph.new Card(pair2, "C"));
		h.getCards().add(ph.new Card(single, "D"));
		h.classifyHand(h.getCards());
		return h;
	}

	private Hand makeThreeOfAKind() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(2, "H"));
		h.getCards().add(ph.new Card(2, "S"));
		h.getCards().add(ph.new Card(6, "C"));
		h.getCards().add(ph.new Card(10, "D"));
		return h;
	}

	private Hand makeThreeOfAKind(int triple, int highCard) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(triple, "D"));
		h.getCards().add(ph.new Card(triple, "H"));
		h.getCards().add(ph.new Card(triple, "S"));
		h.getCards().add(ph.new Card(6, "C"));
		h.getCards().add(ph.new Card(highCard, "D"));
		h.classifyHand(h.getCards());
		return h;
	}

	private Hand makeStraight() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(3, "H"));
		h.getCards().add(ph.new Card(4, "S"));
		h.getCards().add(ph.new Card(5, "C"));
		h.getCards().add(ph.new Card(6, "D"));
		return h;
	}

	private Hand makeStraight(int highCard) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(highCard - 4, "D"));
		h.getCards().add(ph.new Card(highCard - 3, "H"));
		h.getCards().add(ph.new Card(highCard - 2, "S"));
		h.getCards().add(ph.new Card(highCard - 1, "C"));
		h.getCards().add(ph.new Card(highCard, "D"));
		h.classifyHand(h.getCards());
		return h;
	}

	private Hand makeFlush() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(4, "D"));
		h.getCards().add(ph.new Card(6, "D"));
		h.getCards().add(ph.new Card(8, "D"));
		h.getCards().add(ph.new Card(10, "D"));
		return h;
	}
	
	private Hand makeFlush(String suit, int highCard) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, suit));
		h.getCards().add(ph.new Card(4, suit));
		h.getCards().add(ph.new Card(6, suit));
		h.getCards().add(ph.new Card(8, suit));
		h.getCards().add(ph.new Card(highCard, suit));
		h.classifyHand(h.getCards());
		return h;
	}
	
	private Hand makeFullHouse() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(2, "H"));
		h.getCards().add(ph.new Card(2, "S"));
		h.getCards().add(ph.new Card(3, "C"));
		h.getCards().add(ph.new Card(3, "D"));
		return h;
	}

	private Hand makeFullHouse(int triple, int pair) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(triple, "D"));
		h.getCards().add(ph.new Card(triple, "H"));
		h.getCards().add(ph.new Card(triple, "S"));
		h.getCards().add(ph.new Card(pair, "C"));
		h.getCards().add(ph.new Card(pair, "D"));
		h.classifyHand(h.getCards());
		return h;
	}

	private Hand makeFourOfAKind() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(2, "H"));
		h.getCards().add(ph.new Card(2, "S"));
		h.getCards().add(ph.new Card(2, "C"));
		h.getCards().add(ph.new Card(3, "D"));
		return h;
	}

	private Hand makeFourOfAKind(int quad) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(quad, "D"));
		h.getCards().add(ph.new Card(quad, "H"));
		h.getCards().add(ph.new Card(quad, "S"));
		h.getCards().add(ph.new Card(quad, "C"));
		h.getCards().add(ph.new Card(quad - 4, "D"));
		h.classifyHand(h.getCards());
		return h;
	}

	private Hand makeStraightFlush() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(2, "D"));
		h.getCards().add(ph.new Card(3, "D"));
		h.getCards().add(ph.new Card(4, "D"));
		h.getCards().add(ph.new Card(5, "D"));
		h.getCards().add(ph.new Card(6, "D"));
		return h;
	}

	private Hand makeStraightFlush(int highCard, String suit) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(highCard - 4, suit));
		h.getCards().add(ph.new Card(highCard - 3, suit));
		h.getCards().add(ph.new Card(highCard - 2, suit));
		h.getCards().add(ph.new Card(highCard - 1, suit));
		h.getCards().add(ph.new Card(highCard, suit));
		h.classifyHand(h.getCards());
		return h;
	}

	private Hand makeRoyalFlush() {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(10, "D"));
		h.getCards().add(ph.new Card(11, "D"));
		h.getCards().add(ph.new Card(12, "D"));
		h.getCards().add(ph.new Card(13, "D"));
		h.getCards().add(ph.new Card(14, "D"));
		return h;
	}

	private Hand makeRoyalFlush(String suit) {
		Hand h = ph.new Hand();
		h.getCards().add(ph.new Card(10, suit));
		h.getCards().add(ph.new Card(11, suit));
		h.getCards().add(ph.new Card(12, suit));
		h.getCards().add(ph.new Card(13, suit));
		h.getCards().add(ph.new Card(14, suit));
		return h;
	}
}
