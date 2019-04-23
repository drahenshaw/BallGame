/******************************************************************************
 *  Compilation:  javac BallGame.java
 *  Execution:    java BallGame n
 *  Dependencies: BasicBall.java StdDraw.java
 *
 *  Creates a BasicBall and animates it
 *
 *  Part of the animation code is adapted from Computer Science:   An Interdisciplinary Approach Book
 *  
 *  Run the skeleton code with arguments : 1  basic  0.08
 *******************************************************************************/

// Programmer: David Henshaw 11215398
// Date: 4/23/19

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class BallGame { 
	
    public static void main(String[] args) {
  
    	// number of bouncing balls
    	int numBalls = Integer.parseInt(args[0]);
    	//ball types
    	String ballTypes[] = new String[numBalls];
    	//sizes of balls
    	double ballSizes[] = new double[numBalls];
    	
    	//retrieve ball types
    	int index =1;
    	for (int i=0; i<numBalls; i++) {
    		ballTypes[i] = args[index];
    		index = index+2;
    	}
    	//retrieve ball sizes
    	index = 2;
    	for (int i=0; i<numBalls; i++) {
    		ballSizes[i] = Double.parseDouble(args[index]);
    		index = index+2;
    	}
     
    	// Create a Player object and initialize the player game stats.  
    	Player player = new Player();
    	
    	//number of active balls
    	int numBallsinGame = 0;
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(800, 800);
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // create colored balls 
        // Create "numBalls" balls (of types given in "ballTypes" with sizes given in "ballSizes") and store them in an Arraylist
        ArrayList<BasicBall> gameBalls  = new ArrayList<BasicBall>(numBalls);
        ArrayList<BasicBall> ballsToAdd = new ArrayList<BasicBall>();
        
        // Iterate through Command Line Arguments to Create Initial Ball Set for Game
        for (int i = 0; i < numBalls; i++)
        {
        	switch(ballTypes[i].toLowerCase())
        	{
        	case "basic":
        		gameBalls.add(new BasicBall(ballSizes[i],  Color.RED));
        		break;
        	case "shrink":
        		gameBalls.add(new ShrinkBall(ballSizes[i], Color.BLUE));
        		break;
        	case "bounce":
        		gameBalls.add(new BounceBall(ballSizes[i], Color.GREEN));
        		break;
        	case "split":
        		gameBalls.add(new SplitBall(ballSizes[i],  Color.YELLOW));
        		break;
        	default:
        		return;
        	}
        }
        
   		// Initialize the numBallsinGame
   		numBallsinGame = gameBalls.size();
        
        // do the animation loop
        StdDraw.enableDoubleBuffering();
        while (numBallsinGame > 0) {

        	// Move all balls
        	for (BasicBall ball : gameBalls)
        	{
        		ball.move();
        	}
            
            // Check if the mouse is clicked
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                
                // Check whether a ball is hit. Check each ball.            
                for (BasicBall ball : gameBalls)
                {
                	if (ball.isHit(x,y)) {
                    	ball.reset();
                    	
                    	// If the Ball was a SplitBall
                    	if (ball instanceof SplitBall)
                    	{
                    		// Create a new SplitBall
                    		ballsToAdd.add(new SplitBall(ball.getRadius(), ball.getColor()));
                    	}
                    	
                    	// Update Player Statistics
                    	player.TrackHits(ball);
                    	player.IncrementScore(ball.getScore());
                	}
                }  
                
                // Add the Additional Balls to the Game
                for (BasicBall ball : ballsToAdd)
                {
                	gameBalls.add(ball);
                }
                
                // Clear Split Balls Added During this Loop
                ballsToAdd.clear();
            }
                         
            numBallsinGame = 0;
            // draw the n balls
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);
            
            // Check each ball and see if they are still visible. numBallsinGame should hold the number of visible balls in the game. 
            for (BasicBall ball : gameBalls)
            {
            	 if (ball.isOut == false) { 
                     ball.draw();
                     numBallsinGame++;
            	 }
            }
            
            //Print the game progress
            StdDraw.setPenColor(StdDraw.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.65, 0.90, "Number of balls in game: "+ String.valueOf(numBallsinGame));
            
            //Print the rest of the player statistics            
            StdDraw.text(-0.76, 0.80, "Number of hits: "+ String.valueOf(player.SuccessfulHits()));
            StdDraw.text(-0.65, 0.70, "Ball Most Hit: "+ String.valueOf(player.BallMostHit().getKey() + ": " + player.BallMostHit().getValue()));
            StdDraw.text(-0.86, 0.60, "Score: "+ String.valueOf(player.CurrentScore()));
            
            StdDraw.show();
            StdDraw.pause(20);
        }
        
        // End Game
        while (true) {
            StdDraw.setPenColor(StdDraw.BLUE);
            Font font = new Font("Arial", Font.BOLD, 60);
            StdDraw.setFont(font);
            StdDraw.text(0, 0, "GAME OVER");
            
            //Print the rest of the player statistics           
            StdDraw.setPenColor(StdDraw.YELLOW);
            font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(0, -.10, "Number of hits: "+ String.valueOf(player.SuccessfulHits()));
            StdDraw.text(0, -.20, "Ball Most Hit: "+ String.valueOf(player.BallMostHit().getKey() + ": " + player.BallMostHit().getValue()));
            StdDraw.text(0, -.30, "Score: "+ String.valueOf(player.CurrentScore()));
            
            
            StdDraw.show();
            StdDraw.pause(10);           
        }        
    }
}
