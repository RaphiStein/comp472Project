
public enum PlayerName {
	PLAYER_ONE,
	PLAYER_TWO; // in the event of playing against the computer, player_two is the computer
	
	public String toString(){
		switch(this){
		case PLAYER_ONE:
			return "Player 1";
		case PLAYER_TWO:
			return "Player 2";
		}
		return null;
	}
}
