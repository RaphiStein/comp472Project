
import java.util.HashMap;
import java.util.Map;

import misc.PlayerType;
import misc.RowColumnTuple;


public class GameBoard {

	private static GameBoard gameboard = null;

	Player[][] board; // Need to change type to Player. For setting row number
						// do 8-(rowNum) to get proper index
	Map<String, Player> players;
	Player currentPlayer;

	// Implemented as a Singleton (there can only be one gameboard)
		public static GameBoard init() {
			if (gameboard == null) {
				gameboard = new GameBoard();
			}
			// Set Up Initial Bird and Larva Positions
			// initially, the larva is located at position D2
			// (on line 2), and the 4 birds are located at positions
			// A1, C1, E1, G1.

			return gameboard;
		}
		
	private GameBoard() {
		board = new Player[8][8];
		players = new HashMap<String, Player>();

		
		players.put("L", new Player(PlayerType.LARVA, new RowColumnTuple(1,3),
				"L "));
		players.put("B1", new Player(PlayerType.BIRDS,
				new RowColumnTuple(0,0), "B1"));
		players.put("B2", new Player(PlayerType.BIRDS,
				new RowColumnTuple(0,2), "B2"));
		players.put("B3", new Player(PlayerType.BIRDS,
				new RowColumnTuple(0,4), "B3"));
		players.put("B4", new Player(PlayerType.BIRDS,
				new RowColumnTuple(0,6), "B4"));

		
		/**
		players.put("L",new Player(PlayerType.LARVA,new RowColumnTuple("D2"),
				 "L"));
		players.put("B1", new Player(PlayerType.BIRDS,
				new RowColumnTuple("A1"), "B1"));
		players.put("B2", new Player(PlayerType.BIRDS,
				new RowColumnTuple("C1"), "B2"));
		players.put("B3", new Player(PlayerType.BIRDS,
				new RowColumnTuple("E1"), "B3"));
		players.put("B4", new Player(PlayerType.BIRDS,
				new RowColumnTuple("G1"), "B4"));

		board[players.get("L").getPosition().getRow()][players.get("L")
				.getPosition().getColumn()] = players.get("L");
				**/
		// Set the player positions in the board
		for (Map.Entry<String, Player> entry : players.entrySet()) {
			Player p = entry.getValue();
			 board[p.getPosition().getRow()][p.getPosition().getColumn()] = p;
		}

	}
	
	public  PlayerType checkWinner(PlayerType lastPlayer){
		
		Player larva = players.get("L");
		
		
		if(lastPlayer == PlayerType.LARVA){
			if(larva.getPosition().getRow() == 0) //If the larva made it past the fence
				return PlayerType.LARVA;
			else if(players.get("B1").getPosition().getRow() > larva.getPosition().getRow() ) //These check if all the birds are higher than the larva
				if(players.get("B2").getPosition().getRow() > larva.getPosition().getRow() ) //Automatic win
					if(players.get("B3").getPosition().getRow() > larva.getPosition().getRow() )
						if(players.get("B4").getPosition().getRow() > larva.getPosition().getRow() )
							return PlayerType.LARVA;
		}
		else if(larva.getPosition().getRow() == board.length -1){ //Larva in Top row and trapped
			if(larva.getPosition().getColumn() == 0){
				if(board[larva.getPosition().getRow()-1][larva.getPosition().getColumn()+1] != null)
					return PlayerType.BIRDS;
			}
			else if(larva.getPosition().getColumn() == board.length -1){
				if(board[larva.getPosition().getRow()-1][larva.getPosition().getColumn()-1] != null)
					return PlayerType.BIRDS;
			}
			else {
				if(board[larva.getPosition().getRow()-1][larva.getPosition().getColumn()+1] != null &&
						board[larva.getPosition().getRow()-1][larva.getPosition().getColumn()-1] != null)
					return PlayerType.BIRDS;
			}
			
		}
		else if(larva.getPosition().getColumn() == 0){ //larva at left most column of the board
			if(board[larva.getPosition().getRow()-1][larva.getPosition().getColumn()+1] != null && 
					board[larva.getPosition().getRow()+1][larva.getPosition().getColumn()+1] != null)
				return PlayerType.BIRDS;
		}
		else if(larva.getPosition().getColumn() == board.length - 1){ //if larva at right most column
			if(board[larva.getPosition().getRow()-1][larva.getPosition().getColumn()-1] != null || 
					board[larva.getPosition().getRow()+1][larva.getPosition().getColumn()-1] != null)
				return PlayerType.BIRDS;
		}
				
		return null; //No winner
	}

	

	public boolean checkMove(RowColumnTuple position, String p) {
		Player player = players.get(p);

		int col = position.getColumn();
		int row = position.getRow();

		int c = player.getPosition().getColumn();
		int currentRow = player.getPosition().getRow();

		if (player.getPlayerType() == PlayerType.LARVA) { //Check if valid move for larva
			if (col == (c + 1) || col == (c - 1) && col < 8) { // Check column
																// for larva
				if (row == (currentRow + 1) || row == (currentRow - 1)
						&& row < 8) // Check row for larva
					makeMove(position, player);
				return true;
			}
		} else { // Check if valid move for Birds
			if (col == (c + 1) || col == (c - 1)) { // Check column for birds
				if (row == (currentRow + 1)) { // Check row for birds
					makeMove(position, player);
					return true;
				}
			}
		}
		return false;
	}

	public void makeMove(RowColumnTuple pos, Player player) {
		// change the player position in board

		// First erase the old position
		board[player.getPosition().getRow()][player.getPosition().getColumn()] = null;

		// Set the new position and then change the board
		player.setPosition(pos);
		board[player.getPosition().getRow()][player.getPosition().getColumn()] = player;
		players.put(player.getName(), player);
		
		//Check if theres a winner
		gameboard.checkWinner(player.getPlayerType());
	}

	public void drawBoard() {
		
		System.out.println("    A    B    C    D    E    F    G    H   ");
		int rowNum = 8;
		for (int i = board.length * 2; i >=0; i--) {
			if (i % 2 == 0) {
				System.out
						.println("  -----------------------------------------");
			} else {
				String line = String.valueOf(rowNum) + " ";

				for (int j = 0; j < board.length; j++)
					line += (board[i / 2][j] == null ? "|    " : "| "
							+ board[i / 2][j].getName() + " ");
				line += "|";
				System.out.println(line);
				rowNum--;
			}
		}
		System.out.println();
		System.out.println();
	}

}
