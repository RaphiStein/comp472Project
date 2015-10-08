
public class Gameboard {
	
	private static Gameboard gameboard = null;
	
	
	
	private Gameboard(){
		
	}
	// Implemented as a Singleton (there can only be one gameboard)
	public static Gameboard init(){
		if (gameboard == null){
			gameboard = new Gameboard();
		}
		// Set Up Initial Bird and Larva Positions
//		 initially, the larva is located at position D2
//		 (on line 2), and the 4 birds are located at positions
//		 A1, C1, E1, G1. 

		
		return gameboard;
	}
	 
}
