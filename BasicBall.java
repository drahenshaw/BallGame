/******************************************************************************
 *  Compilation:  javac ColoredBall.java
 *  Execution:    java ColoredBall
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d ball moving in square with coordinates
 *  between -1 and +1. Bounces off the walls upon collision.
 *  
 *
 ******************************************************************************/

import java.awt.Color;

public class BasicBall { 
	
	// Fields
    protected double rx, ry;         // position
    protected double vx, vy;         // velocity
    protected double radius;   		 // radius
    protected final Color color;     // color
    public boolean isOut;			 // Is ball off screen?
    public String name;				 // "Basic"
    
    
    // Constructor
    public BasicBall(double r, Color c) {
        rx = 0.0;
        ry = 0.0;
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        radius = r;
        color = c;
        isOut = false;
        name = "BasicBall";
    }
   
    // Move the Ball one Step
    public void move() {
        rx = rx + vx;
        ry = ry + vy;
        if ((Math.abs(rx) > 1.0) || (Math.abs(ry) > 1.0)) 
        	isOut = true;
    }

    // Draw the Ball
    public void draw() { 
    	if ((Math.abs(rx) <= 1.0) && (Math.abs(ry) <= 1.0)) {
    		StdDraw.setPenColor(color);
    		StdDraw.filledCircle(rx, ry, radius);
    	} else
    		isOut = true;  	
    }

    // Reset the Ball to Center of the Screen and Assign Random Speed
    public int reset() {
        rx = 0.0;
        ry = 0.0;  	
        // Assign a random speed 
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        return 1;
    }
    
    // Check if the Ball is Hit
    public boolean isHit(double x, double y) {
    	if ((Math.abs(rx-x)<=radius) && (Math.abs(ry-y)<=radius))
			return true;
		else return false; 
    }
    
    // Award 25 Points for Clicking
    public int getScore() {
    	return 25;
    }
    
    // Return the Ball Radius
    public double getRadius() {
    	return radius;
    }
    
    // Return the Ball Color
    public Color getColor() {
    	return color;
    }
}
