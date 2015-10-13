import misc.PlayerType;
import misc.RowColumnTuple;

public class Player {
	
	private PlayerType playerType;
	private RowColumnTuple position;
	private String name;
	
	public Player(PlayerType playerType, RowColumnTuple position, String name){
		this.playerType = playerType;
		this.position = position;
		this.name = name;
	}
	
	public PlayerType getPlayerType(){
		return playerType;
	}
	
	public RowColumnTuple getPosition()
	{
		return position;
	}
	
	public void setPosition(RowColumnTuple p)
	{
		position = p;
	}

	public String getName() {
		return name;
	}

}
