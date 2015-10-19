package misc;

/**
 * Object to comprehensively store row and column tuples so we don't have confusing ints flying around
 * @author Raphi
 *
 */
public class RowColumnTuple {
	
	private int row; // 0-7
	private int column; // 0-7, represented in game as A, B, C....
	
	// CONSTRUCTOR
	public RowColumnTuple(){
		
	}
	public RowColumnTuple(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	public RowColumnTuple(String alphanumericPosition) throws Exception{
		// Converts an alphanumeric parameter (i.e. D3) to RowColumnTuple object with row/column (i.e. 3, 2)
		char[] asArray = alphanumericPosition.toCharArray();
		row =  Character.getNumericValue(asArray[1])-1; // SUBTRACT 1 since user enters A0 for 0,0
		if (row < 0) throw new Exception("Invalid board location input");
		switch (asArray[0]){
			case 'A':
				column = 0;
				break;
			case 'B':
				column = 1;
				break;
			case 'C':
				column = 2;
				break;
			case 'D':
				column = 3;
				break;
			case 'E':
				column = 4;
				break;
			case 'F':
				column = 5;
				break;
			case 'G':
				column = 6;
				break;
			case 'H':
				column = 7;
				break;
		}
	}

	
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	
	
}
