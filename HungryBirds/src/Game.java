import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import misc.GameMode;
import misc.PlayerType;
import misc.RowColumnTuple;

/**
 * Main Class of the program
 * @author Raphi
 *
 */
public class Game {

	GameMode gameMode;
	LinkedList<Player> players;

	public Game(GameMode gameMode, boolean shouldBirdsBeAI, boolean shouldLarvaBeAI) throws Exception{
		if (shouldBirdsBeAI && shouldLarvaBeAI) throw new Exception("Birds and Larva cannot both be played by AI");
		// Set Game Mode
		this.gameMode = gameMode;
		// Create Players
		players = new LinkedList<Player>();
		players.add(new PlayerBird(shouldBirdsBeAI));
		players.add(new PlayerLarva(shouldLarvaBeAI));
	};

	private static void welcomeMessage(){
		System.out.println("**************************************");
		System.out.println("  Welcome to Hungry Birds - The Game  ");
		System.out.println("**************************************");
	}
	private static Game createGame() throws Exception {
		// PROMPT FOR GAME TYPE
		System.out.println("Do you want to play in \n1) two-player mode\n2) against the computer? \n(enter 1 or 2)");
		boolean validGameModeReceived = false;
		Scanner scanner = null;
		while (!validGameModeReceived){
			scanner = new Scanner(System.in);
			int gameType = scanner.nextInt();
			// Validate
			if (gameType != 1 && gameType != 2){
				System.out.println("\n ERROR - the input was not valid. Please enter a 1 or a 2");
			}
			else {
				validGameModeReceived = true;
				if (gameType == 1){
					System.out.println("Manual Mode Selected");
					return new Game(GameMode.MANUAL_MODE, false, false);
				}
				else { // user wants to play against the computer
					System.out.println("Automatic Mode Selected");
					System.out.println("Which role do you want the AI to play? \n1) Birds \n2) Larva \n(enter 1 or 2)");
					boolean validIntReceived = false;
					while (!validIntReceived){
						int birdsOrLarvaSelection = scanner.nextInt();
						// Validate
						if (birdsOrLarvaSelection != 1 &&  birdsOrLarvaSelection != 2){
							System.out.println("\n ERROR - the input was not valid. Please enter a 1 or a 2");
						}
						else {
							if (birdsOrLarvaSelection == 1){
								return new Game(GameMode.AUTOMATIC_MODE, true, false);
							}
							else {
								return new Game(GameMode.AUTOMATIC_MODE, false, true);
							}
						}
					}
				}
			}
		}
		return null; //default
	}
	private Player getNextTurnPlayer(){
		//System.out.println("QUEUE: " + players.getFirst().getPlayerType() + ", " + players.getLast().getPlayerType());
		Player next = players.removeFirst();
		players.addLast(next); //move to the back of the queue
		//System.out.println("QUEUE: " + players.getFirst().getPlayerType() + ", " + players.getLast().getPlayerType());
		return next;
	}
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Game.welcomeMessage();
		Game game = Game.createGame();

		// SET UP GAME BOARD AND PLAY!
		boolean gameOver = false;
		GameBoard gameBoard = GameBoard.init();
		gameBoard.drawBoard();
		
		// GAME PLAY
		while (!gameOver){
			Player currentPlayer = game.getNextTurnPlayer();
			System.out.println("Turn: " + currentPlayer.getPlayerType());
			if (!currentPlayer.isAI()){ // if current player is manual (not played by AI)
				// Enter Move (i.e. A3 D4 will be read as moveFrom = A3, moveTo = D4)
				RowColumnTuple moveFrom = new RowColumnTuple(scanner.next());
				RowColumnTuple moveTo = new RowColumnTuple(scanner.next());
				currentPlayer.move(moveFrom, moveTo);
			}
			else {
				System.out.println("AI Not Implemented Yet");
				currentPlayer.move();
			}
			Player winner = gameBoard.checkWinner(currentPlayer.getPlayerType(), game.players);
			if (winner != null){ // THERE IS A WINNER
				gameOver = true;
				System.out.println("Congratulations! Player " + winner.getPlayerType() + " has won!");
			}
			gameBoard.drawBoard();
		}
	}
}
