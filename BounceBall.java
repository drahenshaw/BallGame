import java.awt.Color;

public class BounceBall extends BasicBall {
	
	// Fields
	
	// Number of Bounces Left Before Ball is Out
	private int bouncesLeft;
	
	// Constructor
	BounceBall(double r, Color c)
	{
		super(r,c);
		bouncesLeft = 3;
		name = "BounceBall";
	}
	
	// When Ball Would become Off Screen, Bounce it up to 3 times
	public void move()
	{
		// Position
		rx = rx + vx;
        ry = ry + vy;
        
        // Check if Ball is Off Screen
        if ((Math.abs(rx) > 1.0) || (Math.abs(ry) > 1.0)) 
        {
        	// If Ball can still Bounce
        	if (bouncesLeft > 0)
        	{
        		// Check if Hitting Vertical Edges
        		if (Math.abs(rx)> 1.0)
        		{
        			// Flip Horizontal Speed
        			vx *= -1.0;
        		}
        		// Check if Hitting Horizontal Edges
        		else if (Math.abs(ry) > 1.0)
        		{
        			// Flip Vertical Speed
        			vy *= -1.0;
        		}
        		
        		// Subtract a Bounce
        		bouncesLeft--;
        	}
        	else
        	{
        		isOut = true;
        	}        	        	
        }
	}	
	
	// Super.Draw also checks bounds. Check number of bounces + 1 before isOut = true
	public void draw()
	{
		super.draw();
		if (bouncesLeft + 1 > 0)
		{
			isOut = false;
		}
	}
	
	// Award 15 Score when Clicked
	public int getScore()
	{
		return 15;
	}
}
