package misc;

public class LarvaPosition {
	private static LarvaPosition larvaPosition = null;

	private RowColumnTuple lPos;

	private LarvaPosition(){
		lPos = new RowColumnTuple(1, 3);
	}
	public static LarvaPosition getInstance(){
		if (larvaPosition == null){
			larvaPosition = new LarvaPosition();
		}
		return larvaPosition;
	}
	public RowColumnTuple getPosition() {
		return lPos;
	}
	public void setlPos(RowColumnTuple lPos) {
		this.lPos = lPos;
	}
	public char[][] insertLarva(char[][] board) {
		board[lPos.getRow()][lPos.getColumn()] = 'L';
		return board;
	}
	public boolean isLarvaAtPosition(RowColumnTuple from) {
		if (lPos.getRow() == from.getRow() && lPos.getColumn() == from.getColumn()){
			return true;
		}
		return false;
	}
	public boolean move(RowColumnTuple from, RowColumnTuple to) {
		if (isLarvaAtPosition(from)){
			lPos.setRow(to.getRow());
			lPos.setColumn(to.getColumn());
			return true;
		}
		else {
			return false;
		}
	}



}
