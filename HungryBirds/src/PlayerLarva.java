import misc.LarvaPosition;
import misc.PlayerType;
import misc.RowColumnTuple;

public class PlayerLarva extends Player {

	private static LarvaPosition larvaPositions = null;
	
	public PlayerLarva(boolean isAI) {
		super(PlayerType.LARVA, isAI);
		larvaPositions = larvaPositions.getInstance();
	}
	public RowColumnTuple getPosition(){
		return larvaPositions.getPosition();
	}
}
