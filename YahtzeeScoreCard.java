public class YahtzeeScoreCard
{
	private int [] score;
	
	public YahtzeeScoreCard()
	{
		score = new int[14];
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
	public boolean changeScore(int choice, DiceGroup dg) {return false;}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int total = 0;
		for (int i = 0; i < 5; i++) {
        int dieValue = dg.getDie(i).getValue();  
		//System.out.println("Die: " + dieValue);
        if (dieValue == choice) {
            total += dieValue;
        }
    }
		setScore(total, choice);
		//System.out.println(total);
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) 
	{
		int [] counters = new int[7]; 
		int total = 0;
		for(int i = 0; i < 5; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
		}
		
		for(int i = 0; i < counters.length; i++)
		{
			System.out.println("Counters at: " + i + " are: " + counters[i]);
			if(counters[i] == 3)
			{
				total = (i + 1) * 3;
			}
		}
		
		setScore(total, 7);
		
		
	}
	
	public void fourOfAKind(DiceGroup dg) {
		int [] counters = new int[7]; 
		int total = 0;
		for(int i = 0; i < 5; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
		}
		
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
	
	public void fullHouse(DiceGroup dg) {
		int [] counters = new int[7]; 
		int total = 0;
		for(int i = 0; i < 5; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
		}
		
		for(int i = 0; i < counters.length; i++)
		{
			System.out.println("Counters at: " + i + " are: " + counters[i]);
			if(counters[i] == 3)
			{
									System.out.println(counters[i]);

				for(int j = 0; j < counters.length; j++)
				{
					if(counters[j] == 2)
					{
											System.out.println("full house");

						total = 30;
					}
				}
			}
		}
		
		setScore(total, 9);
		}
	
	public void smallStraight(DiceGroup dg) {
		int [] counters = new int[7]; 
		int total = 0;
		for(int i = 0; i < 5; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
			 System.out.println("Counters at: " + i + " are: " + counters[i]);
		}
		
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
	
	public void largeStraight(DiceGroup dg) {
		
		int [] counters = new int[7]; 
		int total = 0;
		for(int i = 0; i < 5; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue - 1]++;
			 System.out.println("Counters at: " + i + " are: " + counters[i]);
		}
		
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
	
	public void chance(DiceGroup dg) {
		int total = 0;
		for(int i = 0; i < 5; i++)
		{
			 int dieValue = dg.getDie(i).getValue();
			 total += dieValue; 
		}
		setScore(total, 12);
	}
	
	public void yahtzeeScore(DiceGroup dg) {
		int [] counters = new int[7]; 
		int total = 0;
		for(int i = 0; i < 5; i++)
		{
			 int dieValue = dg.getDie(i).getValue(); 
			 System.out.println("Die value: " + dieValue); 
			 counters[dieValue]++;
		}
		
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
	
	public void setScore(int scoreIn, int counter)
	{
		score[counter] = scoreIn;
	}
	public int getScore(int index)
	{
		return score[index];
	}
}
