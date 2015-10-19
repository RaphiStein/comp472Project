
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import misc.BirdPositions;
import misc.LarvaPosition;
import misc.PlayerType;
import misc.RowColumnTuple;


public class GameBoard {

	private static GameBoard gameboard = null;
	
	private BirdPositions birdPositions;
	private LarvaPosition larvaPositions;

	Player[][] board; // Need to change type to Player. For setting row number
						// do 8-(rowNum) to get proper index

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
		
		birdPositions = BirdPositions.getInstance();
		larvaPositions = LarvaPosition.getInstance();
	}
	
	public Player checkWinner(PlayerType lastMove, LinkedList<Player> players){
		PlayerLarva larva = null;
		PlayerBird birds = null;
		
		// Get Players from players Queue
		if (lastMove == PlayerType.BIRDS){
			larva = (PlayerLarva) players.getFirst(); 
			birds = (PlayerBird)  players.getLast();
		}
		else {
			birds = (PlayerBird)  players.getFirst(); 
			larva = (PlayerLarva) players.getLast();
		}
		
		// ANALYZE
		
		// IF LAST MOVE WAS THE BIRDS
		if (lastMove == PlayerType.BIRDS){
			// BIRDS WIN...
			// BIRD LANDED ON LARVA
			if (birdPositions.isBirdAtPosition(larva.getPosition())){
				return birds;
			}
			// BIRDS LOSE...
			// BIRDS ALL ABOVE THE LARVA ROW
			if (birdPositions.allBirdsAreAbove(larva.getPosition().getRow())){
				return larva;
			}
			
		}
		
		// IF LASTMOVE WAS THE LARVA
		else if (lastMove == PlayerType.LARVA){
			// LARVA WINS....
			// IF LARVA IS ON ROW 0... 
			if (larva.getPosition().getRow() <= 0){
				return larva;
			}
		}
		
		else {
			try {
				throw new Exception("Strange Error in checkWinner");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null; //No winner
	}

	

	public boolean checkMove(RowColumnTuple position, String p) {
		/*
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
		}*/
		return false;
		
	}

	public void makeMove(RowColumnTuple pos, Player player) {
		/*
		// change the player position in board

		// First erase the old position
		board[player.getPosition().getRow()][player.getPosition().getColumn()] = null;

		// Set the new position and then change the board
		player.setPosition(pos);
		board[player.getPosition().getRow()][player.getPosition().getColumn()] = player;
		players.put(player.getName(), player);
		
		//Check if theres a winner
		gameboard.checkWinner(player.getPlayerType());
		*/
	}

	public void drawBoard() {
		char[][] board = new char[8][8];

		// POPULATE
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = ' ';
			}
		}
		board = larvaPositions.insertLarva(board);
		board = birdPositions.insertBirds(board);
		
		
		System.out.println("    A    B    C    D    E    F    G    H   ");
		int rowNum = 8;
		for (int i = board.length * 2; i >=0; i--) {
			if (i % 2 == 0) {
				System.out
						.println("  -----------------------------------------");
			} else {
				String line = String.valueOf(rowNum) + " ";

				for (int j = 0; j < board.length; j++)
					line += (board[i / 2][j] == ' ' ? "|    " : "|  "
							+ board[i / 2][j] + " ");
				line += "|";
				System.out.println(line);
				rowNum--;
			}
		}
		System.out.println();
		System.out.println();
	}

}
