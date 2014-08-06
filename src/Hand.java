

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

class Hand {

	ArrayList<Card> hand;

	public Hand(String cardsSet) {
		hand = new ArrayList<Card>();
		createHand(cardsSet);
	}

	private void createHand(String cardsSet) {
		String[] cards = cardsSet.toUpperCase().split(",");
		for (String card : cards) {
			hand.add(new Card(card.trim()));
		}
	}

	public ArrayList<Card> sortByRank() {

		Collections.sort(hand, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return (o1.getRankValue()-o2.getRankValue());
			}
		});
		return hand;
	}

	public ArrayList<Card> sortByScore() {
		Collections.sort(hand, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return (o1.getScore()-o2.getScore());
			}
		});
		return hand;
	}

	public boolean isRoyalFlush() {
		return isStraightFlush() && hand.get(4).getRankValue() == 13
				&& hand.get(0).getRankValue() == 1;
	}

	public boolean isStraightFlush() {
		return isFlush() && isStraight();
	}

	public boolean isFourOfAKind() {

		int commonRank = hand.get(2).getRankValue();
		if (hand.get(0).getRankValue() == commonRank
				|| hand.get(4).getRankValue() == commonRank) {
			if (hand.get(1).getRankValue() == commonRank
					&& hand.get(3).getRankValue() == commonRank) {
				return true;
			}
		}
		return false;
	}

	public boolean isFullHouse() {
		// Sort cards by rank while implementation
		if (hand.get(0).getRankValue() == hand.get(1).getRankValue()
				&& hand.get(3).getRankValue() == hand.get(4).getRankValue()) {
			if (hand.get(2).getRankValue() == hand.get(1).getRankValue()
					|| hand.get(2).getRankValue() == hand.get(3).getRankValue()) {
				return true;
			}
		}
		return false;
	}

	public boolean isFlush() {
		int commonSuit = hand.get(0).getSuitValue();
		if (hand.get(1).getSuitValue() == commonSuit
				&& hand.get(2).getSuitValue() == commonSuit
				&& hand.get(3).getSuitValue() == commonSuit
				&& hand.get(4).getSuitValue() == commonSuit) {
			return true;
		}
		return false;
	}

	public boolean isStraight() {
		int start = 0;
		if (hand.get(0).getRankValue() == 1 && hand.get(1).getRankValue() == 10) {
			start = 2;
		}
		for (int i = start; i < hand.size() - 1; i++) {
			if (hand.get(i + 1).getRankValue() - hand.get(i).getRankValue() != 1) {
				return false;
			}
		}
		return true;
	}

	public boolean isThreeOfAKind() {
		for (int i = 0; i < hand.size() - 2; i++) {
			if (hand.get(i).getRankValue() == hand.get(i + 2).getRankValue()) {
				return true;
			}
		}

		return false;

	}

	public boolean isTwoPair() {
		int two_pair_check = 0;
		for (int i = 0; i < hand.size() - 1; i++) {
			if (hand.get(i).getRankValue() == hand.get(i + 1).getRankValue()) {
				two_pair_check += 1;
			}
		}
		if (two_pair_check == 2) {
			return true;
		}
		return false;

	}

	public boolean isOnePair() {
		for (int i = 0; i < hand.size() - 1; i++) {
			if (hand.get(i).getRankValue() == hand.get(i + 1).getRankValue()) {
				return true;
			}
		}
		return false;

	}

	public String getPokerRank() {
		sortByRank();
		if (isRoyalFlush()) {
			return "ROYAL FLUSH";
		} else if (isStraightFlush()) {
			return "STRAIGHT FLUSH";
		} else if (isFourOfAKind()) {
			return "FOUR OF A KIND";
		} else if (isFullHouse()) {
			return "FULL HOUSE";
		} else if (isFlush()) {
			return "FLUSH";
		} else if (isStraight()) {
			return "STRAIGHT";
		} else if (isThreeOfAKind()) {
			return "THREE OF A KIND";
		} else if (isTwoPair()) {
			return "TWO PAIR";
		} else if (isOnePair()) {
			return "ONE PAIR";
		} else {
			return "HIGH CARD";
		}
	}
	
	public String checkValidityOfHand(String inputLine)
	{
		final String invalidInput = "INVALID INPUT";
		String[] splitLine = inputLine.split(";");
    	if(splitLine.length != 2){
    		return invalidInput;
    	}
    	String[] cardList = splitLine[0].split(",");
    	Set<String> cardSet = new HashSet<String>(Arrays.asList(cardList));
    	if(cardSet.size()!= 5){
    		return invalidInput;
    	}
    	for (String card: cardSet)
    	{
    		if (card.length() != 2)
    		{
    			return invalidInput;
    		}
    		char rank = card.toUpperCase().charAt(0);
    		char suit = card.toUpperCase().charAt(1);
    		if(!"A23456789TJQK".contains(""+rank)){
    			return invalidInput;
    		}
    		if(!"SHDC".contains(""+suit)){
    			return invalidInput;
    		}
    	}
    	
    	return "";

	}
}