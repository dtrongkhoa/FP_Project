package model;


import java.util.Collection;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
public class GameEngineImpl implements GameEngine {
	
	private Map<String, Player> Player = new HashMap<String, Player>();
	private Set<GameEngineCallback> gec = new HashSet<GameEngineCallback>();
	private Deque<PlayingCard> deck = new LinkedList<PlayingCard>();

	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (delay<0 || delay >1000)
			throw new IllegalArgumentException();
		player.setResult(0);
		this.getShuffledHalfDeck();
		PlayingCard newCard = null;
		while (player.getResult() <= BUST_LEVEL)
		{
			newCard = deck.poll();
			player.setResult(player.getResult() + newCard.getScore());
			for (GameEngineCallback call : gec)
			{
				if(player.getResult()>BUST_LEVEL)
				{
						call.bustCard(player, newCard, this);
				}
				else
				{
						call.nextCard(player, newCard, this);
				}
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		player.setResult(player.getResult() - newCard.getScore());
		for(GameEngineCallback call : gec)
			call.result(player, player.getResult(), this);
		
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (delay<0 )
			throw new IllegalArgumentException();
		Player house = new SimplePlayer("0", "House", 0);
		house.setResult(0);
		this.getShuffledHalfDeck();
		PlayingCard newCard = null;
		while(house.getResult() <= BUST_LEVEL )
		{
			newCard = deck.poll();
			house.setResult(house.getResult() + newCard.getScore());
			for (GameEngineCallback call : gec)
			{
				if (house.getResult() > BUST_LEVEL)
					call.houseBustCard(newCard, this);
				else
					call.nextHouseCard(newCard, this);
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		house.setResult(house.getResult() - newCard.getScore());
		for(Player Player : Player.values()) 
		{
			applyWinLoss(Player, house.getResult());
		}
		for(GameEngineCallback call : gec)
			call.houseResult(house.getResult(), this);
	}

	@Override
	public void applyWinLoss(Player player, int houseResult) {
		// TODO Auto-generated method stub
		for(Player Player : Player.values()) {
			if(houseResult > Player.getResult()) {
				Player.setPoints(Player.getPoints() - Player.getBet());
			}
			else if(houseResult < Player.getResult()) {
				Player.setPoints(Player.getPoints() + Player.getBet());
			}
			Player.resetBet();
		}
	}

	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		Player.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		// TODO Auto-generated method stub
		if (Player.containsKey(id))
		{
			return Player.get(id);
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		if (Player.containsValue(player))
		{
			Player.remove(player.getPlayerId());
			return true;
		}
		return false;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		// TODO Auto-generated method stub
		if (Player.containsValue(player))
		{
			return player.setBet(bet);
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub
		gec.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub
		if (gec.contains(gameEngineCallback))
		{
			gec.remove(gameEngineCallback);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		//Collections.sort(Player.values());
		Collection<Player> unmodifiable =  Collections.unmodifiableCollection(Player.values());
		//unmodifiable.sort(unmodifiable(Player.values()));
		return unmodifiable;
	}

	@Override
	public Deque<PlayingCard> getShuffledHalfDeck() {
		// TODO Auto-generated method stub
		Deque<PlayingCard> create_deck = new LinkedList<PlayingCard>();
		for (PlayingCard.Suit suit : PlayingCard.Suit.values())
			for (PlayingCard.Value value : PlayingCard.Value.values())
				create_deck.add(new PlayingCardImpl(suit,value));
		Collections.shuffle((List<?>) create_deck);
		deck = create_deck;
		return deck;
	}

}
