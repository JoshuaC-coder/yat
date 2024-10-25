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
		

		int firstRoll = 0;
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
			}
			else
			{
				System.out.println(playerTwo + ", since your sum was higher, you'll roll first.");
			}
		}while(firstRoll == secondRoll);
		ysc.printCardHeader();
		ysc.printPlayerScore(player1);
		dc.rollDice();
		dc.printDice();
		ysc.numberScore(1,dc);

		
	}
}
