package model;

import java.util.Objects;

import model.interfaces.PlayingCard;
public class PlayingCardImpl implements PlayingCard {

	private Suit suit;
	private Value value;
	private int score;
	
	public PlayingCardImpl (Suit suit, Value value )
	{
		this.suit = suit;
		this.value = value;
		if (value == Value.EIGHT)
		{
			score = 8;
		}
		else if (value == Value.NINE)
		{
			score = 9;
		}
		else if (value == Value.TEN)
		{
			score = 10;
		}
		else if (value == Value.JACK || value == Value.QUEEN || value == Value.KING)
		{
			score = 10;
		}
		else if (value == Value.ACE)
		{
			score = 11;
		}
		else 
			score =0;
	}
	@Override
	public Suit getSuit() {
		// TODO Auto-generated method stub
		return suit;
	}

	@Override
	public Value getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	@Override
	public boolean equals(PlayingCard card) {
		// TODO Auto-generated method stub
		if (card.getValue() == this.value)
		{
			if (card.getSuit() == this.suit)
				return true;
		}
		return false;
	}
	
	//"Suit: Clubs, Value: Five, Score: 5"
	@Override
	public String toString()
	{
		return String.format("Suit: %s, Value: %s, Score: %d", suit, value, score);
	}
	
	@Override
	public boolean equals(Object card)
	{
		if (card == null)
			return false;
		if (this == card)
			return true;
		PlayingCardImpl dif_card = (PlayingCardImpl) card;
		if (suit != dif_card.getSuit() && value!= dif_card.getValue())
			return false;
		else 
			return true;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(suit, value);
	}

}
