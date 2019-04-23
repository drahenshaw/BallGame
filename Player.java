import java.util.Map;
import java.util.HashMap;

public class Player {
	
	// Fields
	
	// Number of Successful Hits Across all Ball Types
	private int successfulHits;
	
	// Current Score
	private int currentScore;
	
	// Key Value Pair for Ball Type, Number of Hits
	private Map.Entry<String, Integer> ballMostHit;
	
	// HashMap to store all Ball Types, Number of Hits
	private HashMap<String, Integer> ballHitTracker;
	
	// Properties
	public int SuccessfulHits()
	{
		return this.successfulHits;
	}
	
	public int CurrentScore()
	{
		return this.currentScore;
	}
	
	public Map.Entry<String, Integer> BallMostHit()
	{
		this.ballMostHit = FindBallMostHit();
		return this.ballMostHit;
	}
	
	
	// Methods
	
	// Constructor
	public Player()
	{
		this.successfulHits = 0;
		this.currentScore = 0;
		this.ballMostHit = null;
		this.ballHitTracker = new HashMap<String, Integer>();
		this.InitializeHitTracker();
	}
	
	// Set the Number of Hits per Ball to Zero
	private void InitializeHitTracker()
	{
		this.ballHitTracker.put("BasicBall",   0);
		this.ballHitTracker.put("ShrinkBall",  0);
		this.ballHitTracker.put("BounceBall",  0);
		this.ballHitTracker.put("SplitBall",   0);
	}
	
	// Increment Score with Ball is Hit
	public void IncrementScore(int score)
	{
		this.currentScore += score;
	}
	
	// Increment Successful Hits
	private void IncrementHits()
	{
		this.successfulHits++;
	}

	// Increment Ball Hits in the HashMap
	public void TrackHits(BasicBall ball)
	{
		this.ballHitTracker.put(ball.name, this.ballHitTracker.get(ball.name) + 1);
		this.IncrementHits();
	}

	// Returns Key, Value Pair of Ball Type with Most Hits
	private Map.Entry<String, Integer> FindBallMostHit()
	{
		// Get the First HashMap Entry
		Map.Entry<String, Integer> maxHits = this.ballHitTracker.entrySet().iterator().next();
		
		// Iterate though the HashMap
		for (Map.Entry<String, Integer> entry : this.ballHitTracker.entrySet())
		{
			// Set New Max if Found
			if ((int)maxHits.getValue() < (int)entry.getValue())
			{
				maxHits = entry;
			}
		}

		// Return Key, Value Pair
		return maxHits;
	}
}
