package main;
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
		if (isLegalMove(from, to)){
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
		else {
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
	public boolean isLegalMove(RowColumnTuple from, RowColumnTuple to) {
		if (Math.abs(from.getColumn() - to.getColumn()) == 1){
			if (Math.abs(from.getRow() - to.getRow()) == 1){
				return true;				
			}
			return false;
		}
		return false;
	}
}
