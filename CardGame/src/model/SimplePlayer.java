package model;
import java.util.Objects;

import model.interfaces.Player;
public class SimplePlayer implements Player{

	private String playerName;
	private String playerId;
	private int points;
	private int bet;
	private int result;
	
	public SimplePlayer(String playerId, String playerName, int points) {
		// TODO Auto-generated constructor stub
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = points;
	}
	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		// TODO Auto-generated method stub
		this.playerName= playerName;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}

	@Override
	public void setPoints(int points) {
		// TODO Auto-generated method stub
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		// TODO Auto-generated method stub
		return playerId;
	}

	@Override
	public boolean setBet(int bet) {
		// TODO Auto-generated method stub
		if ( points>=bet && bet>0)
		{
			this.bet=bet;
			return true;
		}
		return false;
	}

	@Override
	public int getBet() {
		// TODO Auto-generated method stub
		return bet;
	}

	@Override
	public void resetBet() {
		// TODO Auto-generated method stub
		this.bet=0;
	}

	@Override
	public int getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public void setResult(int result) {
		// TODO Auto-generated method stub
		this.result=result;
	}

	@Override
	public boolean equals(Player player) {
		// TODO Auto-generated method stub
		if (player.getPlayerId() == this.playerId)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object player) {
		// TODO Auto-generated method stub
		if (player == null)
			return false;
		if (this == player)
			return true;
		SimplePlayer dif_player = (SimplePlayer) player;
		return Objects.equals(dif_player.getPlayerId(), playerId);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hashCode(playerId);
	}

	@Override
	public int compareTo(Player player) {
		// TODO Auto-generated method stub
		if (this.playerId.compareTo(player.getPlayerId()) > 0)
		{
			return 1;
		}
		else if (this.playerId.compareTo(player.getPlayerId()) < 0)
		{
			return -1;
		}
		else
			return 0;
	}
	//Player: id=1, name=The Loser, bet=100, points=500, RESULT .. 40
	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d", this.playerId, this.playerName,this.bet,this.points,this.result);
	}
	
}
