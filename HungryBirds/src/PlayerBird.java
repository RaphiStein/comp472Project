import misc.BirdPositions;
import misc.PlayerType;
import misc.RowColumnTuple;

/**
 * One of the two types of Players
 * @author Raphi
 *
 */
public class PlayerBird extends Player {

	BirdPositions birdPositions;

	public PlayerBird(boolean isAI){
		super(PlayerType.BIRDS, isAI);
		birdPositions = BirdPositions.getInstance();
	}

	public boolean move(RowColumnTuple from, RowColumnTuple to){
		if (isLegalMove(from, to)){
			// Check that a piece is at from position
			if (birdPositions.isBirdAtPosition(from)){
				return birdPositions.move(from, to);
			}
			else {
				// ILLEGAL MOVE. THERE ARE NO BIRDS THERE
				System.out.println("ILLEGAL. No bird there");
				return false;
			}
		}
		else{
			try {
				throw new Exception("Illegal Move. Birds can only move forward diagonally");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}


	@Override
	public RowColumnTuple getPosition() {
		return null;
	}
	/**
	 * The AI version of the move() function 
	 */
	@Override
	public boolean move() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLegalMove(RowColumnTuple from, RowColumnTuple to) {
		if ((to.getRow() - from.getRow()) == 1){
			if (Math.abs(from.getColumn() - to.getColumn()) == 1){
				return true;
			}
			return false;
		}
		else{
			return false;
		}
	}
}
