
import java.util.HashMap;
import java.util.Map;

import misc.PlayerType;
import misc.RowColumnTuple;


public class Gameboard {

	private static Gameboard gameboard = null;

	Player[][] board; //Player Board
	Map<String, Player> players; //Map containing the players
	Player currentPlayer;

	private Gameboard() {
		board = new Player[8][8];
		players = new HashMap<String, Player>();

		
		players.put("L", new Player(PlayerType.LARVA, new RowColumnTuple(1, 3),
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

	// Implemented as a Singleton (there can only be one gameboard)
	public static Gameboard init() {
		if (gameboard == null) {
			gameboard = new Gameboard();
		}
		// Set Up Initial Bird and Larva Positions
		// initially, the larva is located at position D2
		// (on line 2), and the 4 birds are located at positions
		// A1, C1, E1, G1.

		return gameboard;
	}

	public boolean checkMove(RowColumnTuple position, String p) {
		Player player = players.get(p);

		int col = position.getColumn();
		int row = position.getRow();

		int c = player.getPosition().getColumn();
		int currentRow = player.getPosition().getRow();

		if (player.getPlayerType() == PlayerType.LARVA) {
			if (col == (c + 1) || col == (c - 1) && col < 8) { // Check column
																// for larva
				if (row == (currentRow + 1) || row == (currentRow - 1)
						&& row < 8) // Check row for larva
					makeMove(position, player);
				return true;
			}
		} else { // Check Birds
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
	}

	public void drawBoard() {
		System.out.println("    A    B    C    D    E    F    G    H   ");
		int rowNum = 8;
		for (int i = board.length * 2; i >= 0; i--) {
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
				// System.out.println(((i/2)+1)+"| A1 | B1 | C1 | D1 | E1 | F1 | G1 | H1 |");

			}
		}
	}

}
