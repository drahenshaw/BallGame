import java.awt.Color;

public class ShrinkBall extends BasicBall {

	// Fields
	
	// Store Initial Radius when Constructed
	private double initialRadius;
	
	// Shrink Ball by ShrinkAmmount (33%)
	private double shrinkAmmount;
	
	// Minimum Ball Size (25%)
	private double smallestRadius;
	
	// Constructor
	public ShrinkBall(double r, Color c) {
		super(r, c);
		initialRadius = r;
		shrinkAmmount = Math.sqrt(2.0/3.0);
		smallestRadius = r * .50;
		name = "ShrinkBall";	// inherited name
	}
	
	// Reset the Ball When Clicked
	// Reduce size by 33%. If size becomes less than 25%, reset to original size.
	public int reset()
	{
		// Halving the Radius decreases area of a circle to 25%
		if (radius <= smallestRadius)
		{
			// Reset the radius to the original size at circle construction
			radius = initialRadius;
		}
		else
		{
			// Shrink the radius by square root(2/3) to decrease circles area by 33%
			radius = radius * shrinkAmmount;
		}
		
		// Call Inherited Reset Method to move ball to center of screen with random speed.
		return super.reset();
	}
	
	// Return 20 Score if Shrink Ball is hit
	public int getScore()
	{
		return 20;
	}
}
