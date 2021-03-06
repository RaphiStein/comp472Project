import java.util.Scanner;

import misc.GameMode;
import misc.PlayerType;

public class Game {

	GameMode gameMode;
	
	public Game(GameMode gameMode){
		this.gameMode = gameMode;
	};

	public static void main(String[] args) {
		Game.welcomeMessage();
		GameMode gameMode = Game.getGameMode();
		Game game = new Game(gameMode);
		
		// SET UP GAME BOARD AND PLAY!
		boolean gameOver = false;
		PlayerName currentPlayer = PlayerName.PLAYER_ONE;
		GameBoard gameBoard = GameBoard.init();
		gameBoard.drawBoard();
		while (!gameOver){
			System.out.println(currentPlayer + "'s Turn");
		}
		
		

		

		// Create Players
	//	Player playerLarva = new Player(PlayerType.LARVA);
	//	Player playerBirds = new Player(PlayerType.BIRDS);
		// Initialize Gameboard
		GameBoard gb = GameBoard.init();

	}

	private static void welcomeMessage(){
		System.out.println("**************************************");
		System.out.println("  Welcome to Hungry Birds - The Game  ");
		System.out.println("**************************************");
	}
	private static GameMode getGameMode(){
		// PROMPT FOR GAME TYPE
		System.out.println("Do you want to play in \n1) two-player mode\n2) against the computer? \n(enter 1 or 2)");
		boolean validGameModeReceived = false;
		while (!validGameModeReceived){
			Scanner scanner = new Scanner(System.in);
			int gameType = scanner.nextInt();
			// Validate
			if (gameType != 1 && gameType != 2){
				System.out.println("\n ERROR - the input was not valid. Please enter a 1 or a 2");
			}
			else {
				validGameModeReceived = true;
				scanner.close();
				if (gameType == 1){
					System.out.println("Manual Mode Selected");
					return GameMode.MANUAL_MODE;
				}
				else {
					System.out.println("Automatic Mode Selected");
					return GameMode.AUTOMATIC_MODE;
				}
			}
		}
		return GameMode.MANUAL_MODE; //default
	}
}
