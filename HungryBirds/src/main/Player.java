package main;
import misc.PlayerType;
import misc.RowColumnTuple;

/**
 * Superclass of the two Player classes
 * @author Raphi
 *
 */
public abstract class Player {
	
	private PlayerType playerType = null;
	private boolean isAI; // is this player being run by Artificial Intelligence?
	
	public Player(PlayerType type, boolean isAI){
		playerType = type;
		this.isAI = isAI;
	}
	
	public PlayerType getPlayerType() {
		return playerType;
	}

	public boolean isAI() {
		return isAI;
	}
	
	public abstract boolean isLegalMove(RowColumnTuple from, RowColumnTuple to);
	public abstract boolean move();
	public abstract boolean move(RowColumnTuple from, RowColumnTuple to);
	public abstract RowColumnTuple getPosition();
	

}
