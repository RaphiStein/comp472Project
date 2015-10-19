package misc;

public class LarvaPosition {
	private static LarvaPosition larvaPosition = null;
	
	private RowColumnTuple lPos;
	
	private LarvaPosition(){
		lPos = new RowColumnTuple(1, 3);
	}
	public static LarvaPosition getInstance(){
		if (larvaPosition == null){
			return new LarvaPosition();
		}
		else {
			return larvaPosition;
		}
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
	
	
	
}
