import misc.BirdPositions;
import misc.PlayerType;
import misc.RowColumnTuple;

public class PlayerBird extends Player {

	BirdPositions birdPositions;
	
	public PlayerBird(boolean isAI){
		super(PlayerType.BIRDS, isAI);
		birdPositions = BirdPositions.getInstance();
	}
	
	public boolean move(RowColumnTuple from, RowColumnTuple to){
		// Check that a piece is at from position
		if (birdPositions.isBirdAtPosition(from)){
			// TODO
			
		}
		else {
			// ILLEGAL MOVE. THERE ARE NO BIRDS THERE
			System.out.println("ILLEGAL. No bird there");
			return false;
		}
		return false;
	}

	@Override
	public RowColumnTuple getPosition() {
		return null;
	}
}
