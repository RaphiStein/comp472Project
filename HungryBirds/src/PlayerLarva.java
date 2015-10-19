import misc.LarvaPosition;
import misc.PlayerType;
import misc.RowColumnTuple;

public class PlayerLarva extends Player {

	private static LarvaPosition larvaPosition = null;

	public PlayerLarva(boolean isAI) {
		super(PlayerType.LARVA, isAI);
		larvaPosition = larvaPosition.getInstance();
	}
	public RowColumnTuple getPosition(){
		return larvaPosition.getPosition();
	}
	@Override
	public boolean move() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean move(RowColumnTuple from, RowColumnTuple to) {
		// Check that a piece is at from position
		if (larvaPosition.isLarvaAtPosition(from)){
			return larvaPosition.move(from, to);
		}
		else {
			// ILLEGAL MOVE. THERE ARE NO BIRDS THERE
			System.out.println("ILLEGAL. The Larva is not there");
			return false;
		}
	}
}
