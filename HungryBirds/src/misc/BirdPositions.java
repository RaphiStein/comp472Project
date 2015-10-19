package misc;

/**
 * Information about where the birds are located is kept here and Players and Gameboard have a reference to it
 * @author Raphi
 *
 */
public class BirdPositions {
	// Implemented as singleton
	private static BirdPositions birdPositions = null;

	//private final int NUMBER_OF_BIRDS = 4;

	private RowColumnTuple[] birdPositionsArray;

	private BirdPositions(){
		RowColumnTuple b1Pos = new RowColumnTuple(0, 0);
		RowColumnTuple b2Pos = new RowColumnTuple(0, 2);
		RowColumnTuple b3Pos = new RowColumnTuple(0, 4);
		RowColumnTuple b4Pos = new RowColumnTuple(0, 6);
		birdPositionsArray = new RowColumnTuple[]{b1Pos, b2Pos, b3Pos, b4Pos};
	}
	public static BirdPositions getInstance(){
		if (birdPositions == null){
			birdPositions = new BirdPositions();
		}
		return birdPositions;
	}

	public int getNumberOfBirds() {
		return birdPositionsArray.length;
	}
	public boolean isBirdAtPosition(RowColumnTuple location){
		for (int i = 0; i < birdPositionsArray.length; i++) {
			if  (birdPositionsArray[i].getRow() == location.getRow() && birdPositionsArray[i].getColumn() == location.getColumn()){
				return true;
			}
		}
		return false;
	}
	public boolean allBirdsAreAbove(int row) {
		for (int i = 0; i < birdPositionsArray.length; i++) {
			if (birdPositionsArray[i].getRow() < row){
				return false;
			}
		}
		return true;
	}
	public char[][] insertBirds(char[][] board) {
		for (int i = 0; i < birdPositionsArray.length; i++) {
			board[birdPositionsArray[i].getRow()][birdPositionsArray[i].getColumn()] = 'B';
		}
		return board;
	}
	public boolean move(RowColumnTuple from, RowColumnTuple to) {
		// FIND bird at FROM position
		for (int i = 0; i < birdPositionsArray.length; i++) {
			if (birdPositionsArray[i].getRow() == from.getRow() && birdPositionsArray[i].getColumn() == from.getColumn()){
				birdPositionsArray[i].setRow(to.getRow());
				birdPositionsArray[i].setColumn(to.getColumn());
				return true;
			}
		}
		return false;
	}
}
