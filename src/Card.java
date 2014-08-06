

import java.io.*;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Card {
	private char suit;
	private char rank;
	private int rankValue;
	private int suitValue;
	private int score;
	
	public Card(String card) {

		this.rank = card.charAt(0);
		this.suit = card.charAt(1);
		this.rankValue = " A23456789TJQK".indexOf("" + rank);
		this.suitValue = "SHDC".indexOf("" + suit);
		this.score = 13 * suitValue + rankValue;
	}

	public String toString() {
		return new String("" + rank + suit);
	}

	public int getScore(){
		return this.score;
	}

	public int getRankValue() {
		return this.rankValue;
	}

	public int getSuitValue() {
		return this.suitValue;
	}
}