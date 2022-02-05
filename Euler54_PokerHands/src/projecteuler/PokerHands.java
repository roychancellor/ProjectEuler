package projecteuler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PokerHands {

	private static final Map<String, Integer> handRanks = new HashMap<String, Integer>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("highCard", 1);
		put("onePair", 2);
		put("twoPairs", 3);
		put("threeOfAKind", 4);
		put("straight", 5);
		put("flush", 6);
		put("fullHouse", 7);
		put("fourOfAKind", 8);
		put("straightFlush", 9);
		put("royalFlush", 10);
	}};
	
	private static final Map<String, Integer> cardValues = new HashMap<String, Integer>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("2", 2);
		put("3", 3);
		put("4", 4);
		put("5", 5);
		put("6", 6);
		put("7", 7);
		put("8", 8);
		put("9", 9);
		put("T", 10);
		put("J", 11);
		put("Q", 12);
		put("K", 13);
		put("A", 14);
	}};
	
	public static int getCardValue(String value) {
		return cardValues.get(value);
	}
	
	public static int main(String[] args) {
		//Create two Player objects, each of which contains a list of Hand objects,
		//where a Hand consists of an array of (value, suit) mappings
		//Read the hands from poker.txt into the two Player object Hands
		//Iterate through each cards for each player and classify the cards
		//Determine who wins by comparing the relative ranking of each classification
		//that is stored in a static map
		//Count the number of player 1 wins
		PokerHands ph = new PokerHands();
		Player p1 = ph.new Player();
		Player p2 = ph.new Player();
		List<String> handsFromFile = getHandsFromFile("/Users/roy/Desktop/p054_poker.txt");
		int winner = 0;
		if(!handsFromFile.isEmpty() && handsFromFile.size() == 1000) {
			for(int i = 0; i < handsFromFile.size(); i++) {
				makeHandsFromString(ph, p1, p2, handsFromFile.get(i));
				winner = determineWinner(p1.getHands().get(i), p2.getHands().get(i));
				if(winner == 1) {
					p1.setNumberOfWins(1 + p1.getNumberOfWins());
				} else if(winner == 2) {
					p2.setNumberOfWins(1 + p2.getNumberOfWins());
				}
			}
		}
		
		System.out.println("Player 1 has " + p1.getNumberOfWins() + " wins");
		System.out.println("Player 2 has " + p2.getNumberOfWins() + " wins");
		System.out.println("Total wins: " + (p1.getNumberOfWins() + p2.getNumberOfWins()));
		
		return p1.getNumberOfWins();
	}

	public static void makeHandsFromString(PokerHands ph, Player p1, Player p2, String handString) {
		String[] cards = handString.split(" ");
		
		//Get player 1's hand
		Hand h = ph.new Hand();
		for(int c = 0; c < 5; c++) {
			h.getCards().add(ph.new Card(cardValues.get(cards[c].substring(0, 1)), cards[c].substring(1)));
		}
		p1.getHands().add(h);
		
		//Get player 2's hand
		h = ph.new Hand();
		for(int c = 5; c < 10; c++) {
			h.getCards().add(ph.new Card(cardValues.get(cards[c].substring(0, 1)), cards[c].substring(1)));
		}
		p2.getHands().add(h);
	}

	public static int determineWinner(Hand h1, Hand h2) {
		int p1Score = computeScoreOfHand(h1);
		int p2Score = computeScoreOfHand(h2);
		System.out.print("p1: " + p1Score + ", p2: " + p2Score);
		if(p1Score > p2Score) {
			System.out.println(": p1 wins");
			return 1;
		} else if(p2Score > p1Score) {
			System.out.println(": p2 wins");
			return 2;
		} else {
			System.out.print(": Tie - breaking tie...");
			int winningPlayer = breakTheTie(h1, h2, p1Score);
			if(winningPlayer == 1) {
				System.out.println(": p1 wins");
				return 1;
			} else if(winningPlayer == 2) {
				System.out.println(": p2 wins");
				return 2;
			}
		}
		return 0;
	}

	public static int computeScoreOfHand(Hand h) {
		return h.rankHand(classifyHand(h));
	}

	public static String classifyHand(Hand h) {
		return h.classifyHand(getCardsOfHand(h));
	}

	public static List<Card> getCardsOfHand(Hand h) {
		return h.getCards();
	}
	
	private static void printCards(String[] cards) {
		for(String c : cards) {
			System.out.print(c + " ");
		}
		System.out.println();
	}
	
	private static void printHand(Hand h) {
		for(Card c : h.getCards()) {
			System.out.print(c.getValue() + c.getSuit() + " ");
		}
//		System.out.println();
	}
	
	private static List<String> getHandsFromFile(String filePath) {
		List<String> hands = new ArrayList<>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filePath));
			while(sc.hasNextLine()) {
				hands.add(sc.nextLine());
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return hands;
	}
	
	public static int breakTheTie(Hand h1, Hand h2, int handRank) {
		//NOTE: ALL LISTS ARE ALREADY SORTED IN THE Hands.classifyHand method
		int p1Max = h1.getHighCard();
		int p2Max = h2.getHighCard();
		switch(handRank) {
			case 1: //high card (1), straight (5), or straight flush (9): compare high cards only
			case 5:
			case 9:
				if(p1Max > p2Max) {
					return 1;
				} else if(p2Max > p1Max) {
					return 2;
				}
			break;
			case 2: //one pair
				if(h1.getPairs().get(0) > h2.getPairs().get(0)) {
					return 1;
				} else if(h2.getPairs().get(0) > h1.getPairs().get(0)) {
					return 2;
				} else {
					for(int i = h1.getSingles().size() - 1; i >= 0 ; i--) {
						p1Max = h1.getSingles().get(i);
						p2Max = h2.getSingles().get(i);
						if(p1Max > p2Max) {
							return 1;
						} else if(p2Max > p1Max) {
							return 2;
						}
					}
				}
			break;
			case 3: //two pair
				if(h1.getPairs().get(1) > h2.getPairs().get(1)) {
					return 1;
				} else if(h2.getPairs().get(1) > h1.getPairs().get(1)) {
					return 2;
				} else {
					if(h1.getPairs().get(0) > h2.getPairs().get(0)) {
						return 1;
					} else if(h2.getPairs().get(0) > h1.getPairs().get(0)) {
						return 2;
					} else {
						if(h1.getSingles().get(0) > h2.getSingles().get(0)) {
							return 1;
						} else if(h2.getSingles().get(0) > h1.getSingles().get(0)) {
							return 2;
						}
					}
				}
			break;
			case 4: //can't tie on 3 of a kind by value, so just check cards
				if(h1.getTripleCard() > h2.getTripleCard()) {
					return 1;
				} else if(h2.getTripleCard() > h1.getTripleCard()) {
					return 2;
				}
			break;
			case 6: //flush: compare high cards until a winner is found
				for(int i = 4; i >= 0; i--) {
					if(h1.getCardValues().get(i) > h2.getCardValues().get(i)) {
						return 1;
					} else if(h2.getCardValues().get(i) > h1.getCardValues().get(i)) {
						return 2;
					}
				}
			break;
			case 7: //full house: compare the three of a kind - there must be a winner
				if(h1.getTripleCard() > h2.getTripleCard()) {
					return 1;
				} else if(h2.getTripleCard() > h1.getTripleCard()) {
					return 2;
				}
			break;
			case 8: //four of a kind: can't tie on value, so just check the card
				if(h1.getQuadrupleCard() > h2.getQuadrupleCard()) {
					return 1;
				} else if(h2.getQuadrupleCard() > h1.getQuadrupleCard()) {
					return 2;
				}
			break;
			case 10: //two royal flushes - no way to break tie
		}
		return 0;
	}
	
	class Player {
		private List<Hand> hands;
		private int numberOfWins;
		
		public Player() {
			this.hands = new ArrayList<>();
			this.numberOfWins = 0;
		}
		
		public List<Hand> getHands() {
			return hands;
		}
		public int getNumberOfWins() {
			return numberOfWins;
		}
		public void setNumberOfWins(int numberOfWins) {
			this.numberOfWins = numberOfWins;
		}
	}
	
	class Hand {
		private List<Card> cards;
		private String classification;
		private List<Integer> cardValues;
		private List<Integer> singles;
		private List<Integer> pairs;
		private int tripleCard;
		private int quadrupleCard;
		private int highCard;
		
		public Hand() {
			this.cards  = new ArrayList<>();
			this.cardValues = new ArrayList<>();
			this.singles = new ArrayList<>();
			this.pairs = new ArrayList<>();
			this.tripleCard = 0;
			this.quadrupleCard = 0;
			this.highCard = 0;
		}
		
		public List<Card> getCards() {
			return this.cards;
		}
		
		public int getHighCard() {
			return this.highCard;
		}
		
		public void setHighCard(int highCard) {
			this.highCard = highCard;
		}
		
		public List<Integer> getCardValues() {
			return this.cardValues;
		}
		
		public List<Integer> getSingles() {
			return this.singles;
		}
		
		public List<Integer> getPairs() {
			return this.pairs;
		}
		
		public int getTripleCard() {
			return this.tripleCard;
		}
		
		public int getQuadrupleCard() {
			return this.quadrupleCard;
		}
		
		public String getClassification() {
			return this.classification;
		}
		
		public String classifyHand(List<Card> hand) {
			//Count the number of instances of each card value and number of instances of each suit
			//and store each in a map (value, count) and (suit, count)
			Map<Integer, Integer> valueCounts = new HashMap<>();
			Map<String, Integer> suitCounts = new HashMap<>();
			
			for(Card c : hand) {
				int val = c.getValue();
				String suit = c.getSuit();
				if(val > this.highCard) {
					this.highCard = val;
				}
				if(valueCounts.containsKey(val)) {
					valueCounts.put(val, valueCounts.get(val) + 1);
				} else {
					valueCounts.put(val, 1);
				}
				if(suitCounts.containsKey(suit)) {
					suitCounts.put(suit, suitCounts.get(suit) + 1);
				} else {
					suitCounts.put(suit, 1);
				}
				this.cardValues.add(val);
			}
			
			//Iterate through the value map and the suit map and get the frequency of 1, 2, 3, and 4 occurrences
			//From this, determine the type of hand per the below rules.
			int numSingles = 0;
			int numDoubles = 0;
			int numTriples = 0;
			int numQuadruples = 0;
			for(int c = 2; c <= 14; c++) {
				if(valueCounts.containsKey(c)) {
					switch(valueCounts.get(c)) {
						case 1:
							numSingles++;
							this.singles.add(c);
							break;
						case 2:
							numDoubles++;
							this.pairs.add(c);
							break;
						case 3:
							numTriples++;
							this.tripleCard = c;
							break;
						case 4:
							numQuadruples++;
							this.quadrupleCard = c;
							break;
					}
				}
			}
			
			//Sort lists for later use in breaking ties (probably a waste of time, but do it for now)
			Collections.sort(this.cardValues);
			Collections.sort(this.singles);
			Collections.sort(this.pairs);
			
			//VALUE-based hands:
			//High card: 1 1 1 1 1 (could be straight, flush, or straight-flush)
			//One pair: 2 1 1 1
			//Two pair: 2 2 1
			//Three of a kind: 3 1 1
			//Straight: 1 1 1 1 1 (where the map keys are in consecutive numerical order)
			//Full house: 3 2
			//Four of a kind: 4 1
			
			//SUIT-based hands:
			//Flush: 5
			
			//VALUE-SUIT hands:
			//Straight flush: Straight and Flush
			//Royal flush: Straight, Flush, and map keys are 10, 11, 12, 13, 14

			boolean isStraight = this.isStraight(cardValues);			
			boolean isFlush = this.isFlush(suitCounts);
			
			if(isStraight && isFlush) {
				if(isRoyalStraight(valueCounts)) {
					this.classification = "royalFlush";
				} else {
					this.classification = "straightFlush";
				}
			} else if(isStraight) {
				this.classification = "straight";
			} else if(isFlush) {
				this.classification = "flush";
			} else if(numSingles == 5 && numDoubles == 0 && numTriples == 0 && numQuadruples == 0) {
				this.classification = "highCard";
			} else if(numDoubles == 1 && numSingles == 3 && numTriples == 0 && numQuadruples == 0) {
				this.classification = "onePair";
			} else if(numDoubles == 2 && numSingles == 1 && numTriples == 0 && numQuadruples == 0) {
				this.classification = "twoPairs";
			} else if(numTriples == 1 && numSingles == 2 && numDoubles == 0 && numQuadruples == 0) {
				this.classification = "threeOfAKind";
			} else if(numTriples == 1 && numDoubles == 1 && numSingles == 0 && numQuadruples == 0) {
				this.classification = "fullHouse";
			} else if(numQuadruples == 1 && numSingles == 1 && numDoubles == 0 && numTriples == 0) {
				this.classification = "fourOfAKind";
			}
			return this.classification;
		}
		
		private int rankHand(String classificationOfHand) {
			if(classificationOfHand != null) {
				return handRanks.get(classificationOfHand);
			}
			return 0;
		}
		
		public boolean isRoyalStraight(Map<Integer, Integer> valueCounts) {
			return valueCounts.containsKey(10) && valueCounts.containsKey(11) && valueCounts.containsKey(12) && valueCounts.containsKey(13) && valueCounts.containsKey(14) &&
			    (valueCounts.get(10) + valueCounts.get(11) + valueCounts.get(12) + valueCounts.get(13) + valueCounts.get(14) == 5);
		}
		
		public boolean isStraight(List<Integer> cardValues) {
			return cardValues.get(4) == cardValues.get(3) + 1 &&
				cardValues.get(3) == cardValues.get(2) + 1 &&
				cardValues.get(2) == cardValues.get(1) + 1 &&
				cardValues.get(1) == cardValues.get(0) + 1;
		}
		
		public boolean isFlush(Map<String, Integer> suitCounts) {
			return (suitCounts.containsKey("S") && suitCounts.get("S") == 5) ||
				(suitCounts.containsKey("C") && suitCounts.get("C") == 5) ||
				(suitCounts.containsKey("H") && suitCounts.get("H") == 5) ||
				(suitCounts.containsKey("D") && suitCounts.get("D") == 5);
		}
	}

	class Card {
		private int value;
		private String suit;
		
		public Card(int value, String suit) {
			this.value = value;
			this.suit = suit;
		}
		
		public int getValue() {
			return this.value;
		}
		public String getSuit() {
			return this.suit;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public void setSuit(String suit) {
			this.suit = suit;
		}
	}
}
