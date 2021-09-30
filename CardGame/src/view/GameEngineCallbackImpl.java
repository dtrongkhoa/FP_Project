package view;

import java.util.logging.Handler;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   public static final Logger logger = Logger.getLogger(GameEngineCallbackImpl.class.getName());

   // utility method to set output level of logging handlers
   public static Logger setAllHandlers(Level level, Logger logger, boolean recursive)
   {
      // end recursion?
      if (logger != null)
      {
         logger.setLevel(level);
         for (Handler handler : logger.getHandlers())
            handler.setLevel(level);
         // recursion
         setAllHandlers(level, logger.getParent(), recursive);
      }
      return logger;
   }

   public GameEngineCallbackImpl()
   {
      // NOTE can also set the console to FINE in %JRE_HOME%\lib\logging.properties
      setAllHandlers(Level.FINE, logger, true);
   }

   @Override
   public void nextCard(Player player, PlayingCard card, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
	   //INFO: Card Dealt to The Loser .. Suit: Hearts, Value: Ten, Score: 10
      logger.log(Level.INFO, String.format("Card Dealt to %s .. Suit: %s, Value: %s, Score: %s", player.getPlayerName(), card.getSuit(), card.getValue(), card.getScore()));
      // TODO: complete this method to log results
   }

   @Override
   public void result(Player player, int result, GameEngine engine)
   {
      // final results logged at Level.INFO
	   //Player: id=1, name=The Loser, bet=100, points=500, RESULT .. 40
      logger.log(Level.INFO, String.format("%s, final result= %d",player.getPlayerName(),result));
      // TODO: complete this method to log results
   }

@Override
public void bustCard(Player player, PlayingCard card, GameEngine engine) {
	// TODO Auto-generated method stub
	logger.log(Level.INFO, String.format("Card Dealt to %s .. Suit: %s, Value: %s, Score: %s .. YOU BUSTED", player.getPlayerName(), card.getSuit(), card.getValue(), card.getScore()));
	
}

@Override
public void nextHouseCard(PlayingCard card, GameEngine engine) {
	// TODO Auto-generated method stub
	logger.log(Level.INFO, String.format("Card Dealt to House ..  Suit: %s, Value: %s, Score: %s", card.getSuit(), card.getValue(), card.getScore()));
}

@Override
public void houseBustCard(PlayingCard card, GameEngine engine) {
	// TODO Auto-generated method stub
	logger.log(Level.INFO, String.format("Card Dealt to House .. Suit: %s, Value: %s, Score: %s .. HOUSE BUSTED", card.getSuit(), card.getValue(), card.getScore()));
}

@Override
public void houseResult(int result, GameEngine engine) {
	// TODO Auto-generated method stub
	logger.log(Level.INFO, String.format("House final result= %d",result));
	String s = "";
	for (Player player : engine.getAllPlayers())
	{
		s += String.format("%s \n",player.toString());
	}
	logger.log(Level.FINE, String.format("Final Player Results\n%s",s));
}

   // TODO complete the rest of this interface

}
