public class Yahtzee
{	
	
	public static void main(String [] args)
	{
		Yahtzee yh = new Yahtzee();
		yh.printHeader();
		yh.runIt();
	}
	
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
	
	public void runIt()
	{
		namesAndFirstInput();
	}
	
	public void namesAndFirstInput()
	{
		DiceGroup dc = new DiceGroup();
		DiceGroup dc2 = new DiceGroup();
		YahtzeeScoreCard ysc = new YahtzeeScoreCard();
				YahtzeeScoreCard ysc2 = new YahtzeeScoreCard();
		YahtzeePlayer player1 = new YahtzeePlayer();
		YahtzeePlayer player2 = new YahtzeePlayer();
		int round = 1;
		boolean first = false;
		int firstRoll = 0;
		int counter = 0;
		
		String valueChosen = "";
		int secondRoll = 0;
		String playerOne = Prompt.getString("Player 1, please enter your first name");
		
		String playerTwo = Prompt.getString("\nPlayer 2, please enter your first name");
		player1.setName(playerOne);
		player2.setName(playerTwo);
		do
		{
			Prompt.getString("Let's see who will go first. " + playerOne + " , please hit enter to roll the dice");
			dc.rollDice();
			dc.printDice();
			firstRoll = dc.getTotal();
			
			Prompt.getString(playerTwo + " it's your turn. Please hit enter to roll the dice");
			dc2.rollDice();
			dc2.printDice();
			secondRoll = dc2.getTotal();

			System.out.println(playerOne + ", you rolled a sum of " + firstRoll + ", and " 
				+ playerTwo + ", you rolled a sum of " + secondRoll);
				
			if(firstRoll > secondRoll)
			{			
				System.out.println(playerOne + ", since your sum was higher, you'll roll first.");
				first = true;
			}
			else
			{
				System.out.println(playerTwo + ", since your sum was higher, you'll roll first.");
				first = false;
			}
		}while(firstRoll == secondRoll);
		while (round <= 13)
		{
			boolean printRound = true;
			if(first)
			{
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);
				if (printRound && counter % 2 == 0)
        {
            System.out.println("Round " + round + " of 13 rounds.");
            printRound = false;  // Print round info only once
        }
				Prompt.getString(playerOne + ", it's your turn to play. Please hit enter to roll the dice");
				dc.rollDice();
				dc.printDice();
				int turns = 0;
				boolean quit = true;
				int choice = 0;
				while(turns < 2 && quit)
				{
					valueChosen = Prompt.getString("Which di(c)e would you like to keep? Enter"
						+ " the values you'd like to 'hold' without spaces. For examples,"
						+ " if you'd like to 'hold' die 1, 2, and 5, enter 125"
						+ " (enter -1 if you'd like to end the turn)");
					if(valueChosen.equals("-1"))
					{
						quit = false;
												System.out.println("in");

					}
					else if(valueChosen.length() > 0)
					{
						dc.rollDice(valueChosen);
						dc.printDice();
					}
					else
					{
						dc.rollDice();
						dc.printDice();
					}
					turns++;
				}
				first = false;
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);
				System.out.println("      \t\t  1    2    3    4    5    6    7    8    9   10   11   12   13");
				choice = Prompt.getInt(playerOne +  ", now you need to make a choice. Pick a valid integer from the list above ", 1, 13);
				if(choice >= 1 && choice <= 6)
				{
					ysc.numberScore(choice ,dc);
				}
				else if(choice == 7)
				{
					ysc.threeOfAKind(dc);
				}
				else if(choice == 8)
				{
					ysc.fourOfAKind(dc);
				}
				else if(choice == 9)
				{
					ysc.fullHouse(dc);
				}
				else if(choice == 10)
				{
					ysc.smallStraight(dc);
				}
				else if(choice == 11)
				{
					ysc.largeStraight(dc);
				}
				else if(choice == 12)
				{
					ysc.chance(dc);
				}
				else if(choice == 13)
				{
					ysc.yahtzeeScore(dc);
				}
				
			}
			
			else
			{
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);				
				if (printRound  && counter % 2 == 0)
        {
            System.out.println("Round " + round + " of 13 rounds.");
            printRound = false;  // Print round info only once
        }
				Prompt.getString(playerTwo + ", it's your turn to play. Please hit enter to roll the dice");
				dc2.rollDice();
				dc2.printDice();
				int turns = 0;
				boolean quit = true;
								int choice = 0;

				while(turns < 2 && quit)
				{
					valueChosen = Prompt.getString("Which di(c)e would you like to keep? Enter"
						+ " the values you'd like to 'hold' without spaces. For examples,"
						+ " if you'd like to 'hold' die 1, 2, and 5, enter 125"
						+ " (enter -1 if you'd like to end the turn)");
					if(valueChosen.equals("-1"))
					{
						quit = false;
						System.out.println("in");
					}
					else if(valueChosen.length() > 0)
					{
						dc2.rollDice(valueChosen);
						dc2.printDice();
					}
					else
					{
						dc2.rollDice();
						dc2.printDice();
					}
					turns++;
				}
				first = true;
				ysc.printCardHeader();
				ysc.printPlayerScore(player1);
				ysc2.printPlayerScore(player2);
				System.out.println("      \t\t  1    2    3    4    5    6    7    8    9   10   11   12   13");
				choice = Prompt.getInt(playerTwo + ", now you need to make a choice. Pick a valid integer from the list above ", 1, 13);
				if(choice >= 1 && choice <= 6)
				{
					ysc2.numberScore(choice ,dc2);
				}
				else if(choice == 7)
				{
					ysc2.threeOfAKind(dc2);
				}
				else if(choice == 8)
				{
					ysc2.fourOfAKind(dc2);
				}
				else if(choice == 9)
				{
					ysc2.fullHouse(dc2);
				}
				else if(choice == 10)
				{
					ysc2.smallStraight(dc2);
				}
				else if(choice == 11)
				{
					ysc2.largeStraight(dc2);
				}
				else if(choice == 12)
				{
					ysc2.chance(dc2);
				}
				else if(choice == 13)
				{
					ysc2.yahtzeeScore(dc2);
				}
			round++;

			}
			counter++;
		}
		
		
	}
}
