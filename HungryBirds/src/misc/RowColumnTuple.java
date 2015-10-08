package misc;

public class RowColumnTuple {
	
	private int row; // 0-7
	private int column; // 0-7
	
	// CONSTRUCTOR
	public RowColumnTuple(){
		
	}
	public RowColumnTuple(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	public RowColumnTuple(String alphanumericPosition){
		// Converts an alphanumeric parameter (i.e. D3) to RowColumnTuple object with row/column (i.e. 3, 2)
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
