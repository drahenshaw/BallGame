import java.awt.Color;

public class SplitBall extends BasicBall {
	
	public SplitBall(double r, Color c)
	{
		super(r,c);
		name = "SplitBall";
	}
	
	// Award 10 Points for Clicking
	public int getScore()
	{
		return 10;
	}
}
