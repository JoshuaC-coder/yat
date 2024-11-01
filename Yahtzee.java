/**
 *	Yahtzee.java
 *
 *	This class plays the computer generated dice version of Yahtzee.
 *  This is a two player game, and the first play is decided by who 
 *  roles the larger sum of the two dice. Then the game starts and players
 *  try to fufil each of the 13 categories given in the short 13 rounds
 *  each player plays. The player can choose to hold dice when rerolling 
 *  and have up to2 rerolls from their orignal roll before selecting a 
 *  category they want to fufil. The player with the most points total
 *  at the end wins!
 *
 *	@author	 Joshua Cao
 *	@since   10/23/2024
 */ 

public class Yahtzee
{		
	private final int NUM_TURNS = 26;      // total number of turns  
										   // ((turns = number of rounds * 2)
	private final int NUM_CATEGORIES = 13; // total number of categories 
	private final int MAX_REROLLS = 2;     // max number of rerolls
	
	public static void main(String [] args)
	{
		Yahtzee yh = new Yahtzee();
		yh.printHeader();
		yh.playGame();
	}
	
    /**
	 * Prints the game's header before the game starts
	 */
	public void printHeader() 
	{
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}

	/**
	 * This method sets up the entire game and structure of how the game will
	 * be played. It prompts the user for their names and chooses who will go first.
	 * Once the game starts, it iterates through thirteen rounds with each
	 * round giving each player one turn to fufil a category. Also giving
	 * them the option to roll and hold dice if they do not like their
	 * original roll. After thirteen rounds, it calculates who will win
	 * and prints out a corresponding message for the winner.
	 */
	public void playGame()
	{
		DiceGroup dc = new DiceGroup();                // p1's instance of dice group
		DiceGroup dc2 = new DiceGroup();               // p2's instance of dice group
		YahtzeeScoreCard ysc = new YahtzeeScoreCard(); // p1's instance of yahtzee score card
		YahtzeeScoreCard ysc2 = new YahtzeeScoreCard(); // p2's instance of yahtzee score card
		YahtzeePlayer player1 = new YahtzeePlayer();   // p1's informatiion
		YahtzeePlayer player2 = new YahtzeePlayer();   // p2's information
		int round = 1;                // round count
		boolean first = false;        // is the first player going first
		boolean printRound = true;    // print the round for the current player's turn
		int counter = 0;              // total amount of turns (rounds * 2)
		String valueChosen = "";      // dice to be kept unrolled
		
		// Prompts user for names and chooses a first player
		String playerOne = Prompt.getString("Player 1, please enter your first name");
		String playerTwo = Prompt.getString("\nPlayer 2, please enter your first name");
		player1.setName(playerOne);
		player2.setName(playerTwo);	
		first = isPlayerOneFirst(playerOne, playerTwo, dc, dc2);
		
		// Loops through and plays 26 turns or 13 rounds
		while ((counter + 1) <= NUM_TURNS)
		{
			printRound = true;
			
			// If player one goes first
			if(first)
			{
				// Prints scoreboard
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);	
				
				// Ensures printing of the round only occurs once per round
				if (printRound && counter % 2 == 0)
				{
					System.out.println("Round " + round + " of 13 rounds.");
					printRound = false; 
				}
				
				// Plays the roll and allows the user to reroll up to 2x
				Prompt.getString("\n" + playerOne + ", it's your turn to play. Please hit enter to roll the dice");
				dc.rollDice();
				dc.printDice();
				int turns = 0;
				boolean quit = true;
				while(turns < MAX_REROLLS && quit)
				{
					valueChosen = Prompt.getString("Which di(c)e would you like to keep? Enter"
						+ " the values you'd like to 'hold' without spaces. For examples,"
						+ " if you'd like to 'hold' die 1, 2, and 5, enter 125"
						+ " (enter -1 if you'd like to end the turn)");
						
					// Skip their rerolls
					if(valueChosen.equals("-1"))
					{
						quit = false;
												System.out.println("in");

					}
					
					// Reroll their unchosen dice
					else if(valueChosen.length() > 0)
					{
						dc.rollDice(valueChosen);
						dc.printDice();
					}
					
					// Reroll all dice
					else
					{
						dc.rollDice();
						dc.printDice();
					}
					turns++;
				}
				first = false;
				
				// Prints scorecard and calculates their points
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);
				System.out.println("      \t\t  1    2    3    4    5    6    7    8    9   10   11   12   13");
				calculateScore(dc, ysc, playerOne);			
			}
			
			// If player two goes first
			else
			{
				//Prints scorecard
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);	
				
				// Ensures printing of the round only occurs once per round			
				if (printRound  && counter % 2 == 0)
				{
					System.out.println("Round " + round + " of 13 rounds.");
					printRound = false; 
				}
				
				// Plays the roll and allows the user to reroll up to 2x
				Prompt.getString("\n" + playerTwo + ", it's your turn to play. Please hit enter to roll the dice");
				dc2.rollDice();
				dc2.printDice();
				int turns = 0;
				boolean quit = true;
				while(turns < MAX_REROLLS && quit)
				{
					valueChosen = Prompt.getString("Which di(c)e would you like to keep? Enter"
						+ " the values you'd like to 'hold' without spaces. For examples,"
						+ " if you'd like to 'hold' die 1, 2, and 5, enter 125"
						+ " (enter -1 if you'd like to end the turn)");
					// Skip their rerolls
					if(valueChosen.equals("-1"))
					{
						quit = false;
						System.out.println("in");
					}
					// Reroll their unchosen dice
					else if(valueChosen.length() > 0)
					{
						dc2.rollDice(valueChosen);
						dc2.printDice();
					}
					// Reroll all dice
					else
					{
						dc2.rollDice();
						dc2.printDice();
					}
					turns++;
				}
				first = true;
				
				// Prints scorecard and calculates their points
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);
				System.out.println("      \t\t  1    2    3    4    5    6    7    8    9   10   11   12   13");
				calculateScore(dc2, ysc2, playerTwo);
				round++;
			}
			counter++;
		}	
		
		//Chooses the winner
		ysc.printCardHeader();
		ysc.printPlayerScore(player1);
		ysc2.printPlayerScore(player2);
		printWinner(playerOne, playerTwo, ysc, ysc2);

		
	}
	
	
	/** 
	 *	Calculates and sums up all the scores in each category for each player.
	 *  Prints out both final scores and gives a message to the winner, or 
	 *  calls a tie based on their final scores.
	 * 
	 *	@param playerOneIn  player one's name
	 *  @param playerTwoIn  player two's name
	 *  @param yscIn        Yahtzee score card for player one
	 *  @param ysc2In       Yahtzee score card for player two
	 */
	public void printWinner(String playerOneIn, String playerTwoIn, YahtzeeScoreCard yscIn,
	                        YahtzeeScoreCard ysc2In)
	{
		int pOneTotal = 0;    // total points for p1
		int pTwoTotal = 0;    // total points for p2
		
		// Adds all 13 categories up
		for(int i = 0; i <= NUM_CATEGORIES; i++)
		{
			pOneTotal += yscIn.getScore(i);
		}
		for(int i = 0; i <= NUM_CATEGORIES; i++)
		{
			pTwoTotal += ysc2In.getScore(i);
		}
		
		System.out.printf("%n%-10s %s = %d", playerOneIn, "score total", pOneTotal);
		System.out.printf("%n%-10s %s = %d", playerTwoIn, "score total", pTwoTotal);
		
		// Prints out a win message based on these scores
		if(pTwoTotal > pOneTotal)
		{
			System.out.println("\n\nCongratulations " + playerTwoIn + ". YOU WON!!!");
		}
		else if(pTwoTotal < pOneTotal)
		{
			System.out.println("\n\nCongratulations " + playerOneIn + ". YOU WON!!!");
		}
		
		// A tie message if both scores were equal to each other
		else if(pTwoTotal == pOneTotal)
		{
			System.out.println("\n\nIt was a tie...");
		}
	}
	
	/** 
	 *	Prompts both players to roll, whichever has the greater roll
	 *  out of the five dice's sum, gets the first turn.
	 * 
	 *	@param playerOneIn  player one's name
	 *  @param playerTwoIn  player two's name
	 *  @param dcIn         Dice grouop for player one
	 *  @param dc2In        Dice group for player two
	 * 	@return             If the player is going first or not
	 */
	public boolean isPlayerOneFirst(String playerOneIn, String playerTwoIn,
									DiceGroup dcIn, DiceGroup dc2In)
	{
		int firstRoll = 0;          // sum of p1's entire roll
		int secondRoll = 0;         // sum if p2's entire roll
		boolean first = false;
		
		// Keeps on rolling if the two players have a tie in their sum
		do
		{
			Prompt.getString("Let's see who will go first. " + playerOneIn + " , please hit enter to roll the dice");
			dcIn.rollDice();
			dcIn.printDice();
			firstRoll = dcIn.getTotal();
			
			Prompt.getString(playerTwoIn + " it's your turn. Please hit enter to roll the dice");
			dc2In.rollDice();
			dc2In.printDice();
			secondRoll = dc2In.getTotal();

			System.out.println(playerOneIn + ", you rolled a sum of " + firstRoll + ", and " 
				+ playerTwoIn + ", you rolled a sum of " + secondRoll);
			
			// If p1 has a greater roll, they get the first turn
			if(firstRoll > secondRoll)
			{			
				System.out.println(playerOneIn + ", since your sum was higher, you'll roll first.");
				first = true;
			}
			
			// if not p2 gets the first roll
			else
			{
				System.out.println(playerTwoIn + ", since your sum was higher, you'll roll first.");
				first = false;
			}
		} while(firstRoll == secondRoll);
		
		return first;
	}
	
	/** 
	 *	Prompts the user until they choose a valid category to pick, which
	 *  is one they have not picked before. Depending on their choice call
	 *  different methods to calculate their score.
	 * 
	 *	@param dcIn         the current player's Dice Group in that turn
	 *  @param yscIn        the current player's Yahtzee score card in that turn
	 *  @param playerIn     the current player's name in that turn
	 */
	public void calculateScore(DiceGroup dcIn, YahtzeeScoreCard yscIn, String playerIn)
	{
		int choice = 0; // the user's choice of category to score
		
		// Asks until they have a valid response (not anything that has
		// been already chosen before)
		do
		{
			choice = Prompt.getInt("\n" + playerIn + ", now you need to make a choice. "
								   + "Pick a valid integer from the list above ", 1, 13);
		} while(!(yscIn.changeScore(choice, dcIn)));	
			
			// Calls the correct method based on their choice that scores 
			// the category accordingly
			if(choice >= 1 && choice <= 6)
			{
				yscIn.numberScore(choice , dcIn);
			}
			else if(choice == 7)
			{
				yscIn.threeOfAKind(dcIn);
			}
			else if(choice == 8)
			{
				yscIn.fourOfAKind(dcIn);
			}
			else if(choice == 9)
			{
				yscIn.fullHouse(dcIn);
			}
			else if(choice == 10)
			{
				yscIn.smallStraight(dcIn);
			}
			else if(choice == 11)
			{
				yscIn.largeStraight(dcIn);
			}
			else if(choice == 12)
			{
				yscIn.chance(dcIn);
			}
			else if(choice == 13)
			{
				yscIn.yahtzeeScore(dcIn);
			}	
	}
}
