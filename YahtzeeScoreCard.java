/**
 *	YahtzeeScoreCard.java
 *
 *	This class prints out each of the player's score cards. It is also where
 *  all 13 categories where the user can score points is calculated.
 *
 *	@author	 Joshua Cao
 *	@since   10/23/2024
 */ 

public class YahtzeeScoreCard
{
	private int [] score;       // scores of each of the 13 categories
	private int [] usedScores;  // keep track if the categories have been
	                            // chosen already or not
	private final int NUM_DICE = 5;	// number of dice
	
	public YahtzeeScoreCard()
	{
		score = new int[14];
		usedScores = new int[14];
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */ 
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		boolean notUsed = true;    // if the category has been used before
		
		// If the category has been "flagged" by changed to a 1 it has been
		// used already
		if(usedScores[choice] == 1)
		{
			notUsed = false;
		}	
		
		return notUsed;	
		}
	
	/**
	 *  Change the scorecard for a number score 1 to 6.
	 *  If the value of the dice matches the user's choice, then add it
	 *  to the score of the category.
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int total = 0;  // total points
		
		// Loops through all of the dices and check if any are the user's
		// choice if so, add it to the total score.
		for (int i = 0; i < NUM_DICE; i++)
		{
        int dieValue = dg.getDie(i).getValue();  
        if (dieValue == choice) {
            total += dieValue;
        }
		}
		setScore(total, choice);
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice. Checks and counts
	 *  each time a value has been rolled, and checks for any of the values
	 *  being rolled three times.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) 
	{
		int [] counters = new int[7]; // how many times 1,2,3,4,5,6 has been rolled
		int total = 0;                // total points
		
		// Loop through the dice and count how many times each value has been rolled
		for(int i = 0; i < NUM_DICE; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
		}
		
		// If any of the values were rolled three times mult the value by 3
		for(int i = 0; i < counters.length; i++)
		{
			if(counters[i] == 3)
			{
				total = (i + 1) * 3;
			}
		}
		
		setScore(total, 7);	
	}
	
	/**
	 *	Updates the scorecard for Four Of A Kind choice. Checks and counts
	 *  each time a value has been rolled, and checks for any of the values
	 *  being rolled four times.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fourOfAKind(DiceGroup dg) {
		int [] counters = new int[7]; // how many times 1,2,3,4,5,6 has been rolled
		int total = 0;                // total points
		
		// Loop through the dice and count how many times each value has been rolled
		for(int i = 0; i < NUM_DICE; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
		}
		
		// If any of the values were rolled four times mult the value by 4
		for(int i = 0; i < counters.length; i++)
		{
			System.out.println("Counters at: " + i + " are: " + counters[i]);
			if(counters[i] == 4)
			{
				total = (i + 1) * 4;
			}
		}
		
		setScore(total, 8);	
		
	}
	
	/**
	 *	Updates the scorecard for Full House choice. Checks and counts
	 *  each time a value has been rolled, and checks for any of the values
	 *  being rolled three times, then checks if any of the values have been
	 *  rolled exactly two times.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fullHouse(DiceGroup dg) {
		int [] counters = new int[7]; // how many times 1,2,3,4,5,6 has been rolled
		int total = 0;                // total points
		
		// Loop through the dice and count how many times each value has been rolled
		for(int i = 0; i < NUM_DICE; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 counters[dieValue - 1]++;
		}
		
		// If any of the values were rolled three times
		for(int i = 0; i < counters.length; i++)
		{
			if(counters[i] == 3)
			{
				// Now check if any of the values where rolled exactly 2 times
				for(int j = 0; j < counters.length; j++)
				{
					// If so, it is a full house so set score to 25 points
					if(counters[j] == 2)
					{
						total = 25;
					}
				}
			}
		}
		
		setScore(total, 9);
		}
	
	/**
	 *	Updates the scorecard for Small Straight choice. Checks and counts
	 *  each time a value has been rolled, and checks to see if the values
	 *  match one of the three possibilities of small straight, which are
	 *  1234, 2345, 3456.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void smallStraight(DiceGroup dg) {
		int [] counters = new int[7]; // how many times 1,2,3,4,5,6 has been rolled
		int total = 0;                // total points
		
		// Loop through the dice and count how many times each value has been rolled
		for(int i = 0; i < NUM_DICE; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 counters[dieValue - 1]++;
		}
		
		// Three cases where there could be a small straight, 1234, 2345, 3456.
		// Checks if there is at least one value in all of these value patterns.
		if(counters[0] >= 1 && counters[1] >= 1 && counters[2] >= 1 && counters[3] >= 1)
		{			
			total = 30;
		}			
		else if(counters[1] >= 1 && counters[2] >= 1 && counters[3] >= 1 && counters[4] >= 1)
		{
			total = 30;
		}
		else if(counters[2] >= 1 && counters[3] >= 1 && counters[4] >= 1 && counters[5] >= 1)
		{
			total = 30;
		}
		setScore(total, 10);
	}
	
	/**
	 *	Updates the scorecard for Large Straight choice. Checks and counts
	 *  each time a value has been rolled, and checks to see if the values
	 *  match one of the two possibilities of large straight, which are
	 *  12345, 23456
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void largeStraight(DiceGroup dg) {
		
		int [] counters = new int[7]; // how many times 1,2,3,4,5,6 has been rolled
		int total = 0;                // total points
		
		// Loop through the dice and count how many times each value has been rolled
		for(int i = 0; i < NUM_DICE; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
			 System.out.println("Counters at: " + i + " are: " + counters[i]);
		}
		
		// Two cases where there could be a large straight, 12345, 23456.
		// Checks if there is at least one value in all of these value patterns.
		if(counters[0] >= 1 && counters[1] >= 1 && counters[2] >= 1 && counters[3] >= 1
		   && counters[4] >= 1)
		{			
			total = 40;
		}			
		else if(counters[1] >= 1 && counters[2] >= 1 && counters[3] >= 1 && counters[4] >= 1
		        && counters[5] >= 1)
		{
			total = 40;
		}
		setScore(total, 11);			
	}
	
	/**
	 *	Updates the scorecard for Chance choice. Sums up all faces of
	 *  the five die rolled.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void chance(DiceGroup dg) {
		int total = 0;   // total score
		
		// Adds up all the scores of the dice
		for(int i = 0; i < NUM_DICE; i++)
		{
			 int dieValue = dg.getDie(i).getValue();
			 total += dieValue; 
		}
		setScore(total, 12);
	}
	
	/**
	 *	Updates the scorecard for Yahtzee choice. Checks and counts
	 *  each time a value has been rolled, and checks to see if one of the
	 *  values has been rolled 5 times. Or in other words, all faces of
	 *  each die are the same.
	 *
	 *	@param dg	The DiceGroup to score
	 */
	public void yahtzeeScore(DiceGroup dg) {
		int [] counters = new int[7]; // how many times 1,2,3,4,5,6 has been rolled
		int total = 0;                // total points
		
		// Loop through the dice and count how many times each value has been rolled
		for(int i = 0; i < NUM_DICE; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue]++;
		}
		
		// If any of the values were rolled five times give the user 50 points
		for(int i = 0; i < counters.length; i++)
		{
			System.out.println("Counters at: " + i + " are: " + counters[i]);
			if(counters[i] == 5)
			{
				total = 50;
			}
		}
		
		setScore(total, 13);
		
		}
		
	// Sets the value of the chosen category
	public void setScore(int scoreIn, int counter)
	{
		score[counter] = scoreIn;
		usedScores[counter] = 1;
	}
	
	// Gets the score of category given
	public int getScore(int index)
	{
		return score[index];
	}
}
