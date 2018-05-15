
///////////////////////////////////////////////////////////////////////////////
// Title:            Program2
// Files:            Sticks.java, Config.java, TestSticks.java
// Semester:         (cs302) Fall 2016
//
// Author:           Hyunho Choi
// Email:            hchoi225@wisc.edu
// CS Login:         hyunho
///////////////////////////////////////////////////////////////////////////////


import java.util.Arrays;
import java.util.Scanner;

/**
 * This class enables users to choose opponents to play against.
 * The user and the opponent each take 1-3 sticks from an initial value of sticks
 * the user input. The one who pick the last stick will lose the game.
 * @author (Hyunho Choi)
 */

public class Sticks {

	/**
	 * This is the main method for the game of Sticks. 
	 * In milestone 1 this contains the whole program for playing
	 * against a friend.
	 * In milestone 2 this contains the welcome, name prompt, 
	 * how many sticks question, menu, calls appropriate methods
	 * and the thank you message at the end.
	 * One method called in multiple places is promptUserForNumber.
	 * When the menu choice to play against a friend is chosen,
	 * then playAgainstFriend method is called.
	 * When the menu choice to play against a computer is chosen,
	 * then playAgainstComputer method is called.  If the
	 * computer with AI option is chosen then trainAI is called
	 * before calling playAgainstComputer.  Finally, 
	 * call strategyTableToString to prepare a strategy table
	 * for printing. 
	 * 
	 * @param args (unused)
	 */
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to the Game of Sticks!");
		System.out.println("==============================");
		System.out.println();
		System.out.print("What is your name? ");

		//The name of the user
		String userName = input.nextLine().trim();

		System.out.println("Hello " + userName + ".");

		//User answer of initial number of sticks
		int initialStick = 0;
		//A string to store when user input is not an integer
		String wrongAnswer = "";

		//Bring the final integer value from Config.java
		final int MIN_STICKS = Config.MIN_STICKS;
		final int MAX_STICKS = Config.MAX_STICKS;

		//A loop to display an error message when user input is wrong
		while (initialStick < MIN_STICKS || initialStick > MAX_STICKS)
		{
			System.out.print("How many sticks are there on the table "
					+ "initially (" + MIN_STICKS + "-" + MAX_STICKS + ")? ");
			//Determine if user input is an integer or not
			if (input.hasNextInt())
			{
				initialStick = input.nextInt();
				input.nextLine();
				//When user input is right
				if (initialStick >= MIN_STICKS && initialStick <= MAX_STICKS)
				{
					//Break the loop to display next message
					break;
				}
				System.out.println("Please enter a number between 10 and 100.");
			}
			//Error message for when user input is not an integer
			else
			{
				wrongAnswer = input.nextLine();
				System.out.println("Error: expected a number between 10 "
						+ "and 100 but found: " + wrongAnswer);
			}
		}

		System.out.println();
		System.out.println("Would you like to:");
		System.out.println(" 1) Play against a friend");
		System.out.println(" 2) Play against computer (basic)");
		System.out.println(" 3) Play against computer with AI");

		//User chooses type of opponent to play against
		int playChoice = 0;
		//A string to store user input when it is not an integer
		String wrongChoice = "";

		//A loop to display error message until user types 1, 2, or 3
		while (playChoice != 1 || playChoice != 2 || playChoice != 3)
		{
			System.out.print("Which do you choose (1,2,3)? ");
			//Determine if user input is an integer or not
			if (input.hasNextInt())
			{
				playChoice = input.nextInt();
				input.nextLine();
				//When user input is 1, 2, or 3
				if (playChoice ==1 || playChoice == 2 || playChoice == 3)
				{
					//Break the loop to display next message
					break;
				}
			}
			//Error message for when user input is not an integer
			else
			{
				wrongChoice = input.nextLine();
				System.out.println("Error: expected a number between 1 and 3 "
						+ "but found: " + wrongChoice);
			}
		}


		//When user chooses to play against a friend
		if (playChoice == 1)
		{
			System.out.println();
			System.out.print("What is your friend's name? ");
			//name input of opponent(friend)
			String friendName = input.nextLine().trim();

			System.out.println("Hello " + friendName + ".");
			System.out.println();
			System.out.println("There are " + initialStick + 
					" sticks on the board.");

			//the number of stick user chooses to take
			int takeStick = 0;
			//to count the number of sticks remaining on the board
			int remainStick = initialStick;
			//Winner of the game
			String winner = "";
			//Store wrong user input
			String wrongStick = "";
			
			//The maximum number of sticks user can take
			final int MAX_TAKE_STICK = 3;
			//Takes sticks while sticks remain on the board
			do
			{
				//A loop to display error message until user types 1, 2, or 3
				while(takeStick != 1 || takeStick != 2 || takeStick != 3)
				{
					System.out.print(friendName + 
							": How many sticks do you take (1-"
							//Chooses minimum value of the two variables
							+ Math.min(remainStick, MAX_TAKE_STICK) + ")? ");
					//Determine whether input is an integer or not
					if (input.hasNextInt())
					{
						takeStick = input.nextInt();
						input.nextLine();
						//When user input is acceptable
						if (takeStick == 1 || takeStick == 2 || takeStick == 3)
						{
							//Exits loop to display next step
							break;
						}
						System.out.println("Please enter a number between 1 and 3.");
					}
					//Error message to dispaly when user input is not an integer
					else
					{
						wrongStick = input.nextLine();
						System.out.println("Error: expected a number between "
								+ "1 and 3 but found: " + wrongStick);
					}

				}

				remainStick -= takeStick;
				//Stops the loop when there are no sticks left to take
				if (remainStick == 0)
				{
					//Exits loop when the user wins
					winner = userName;
					break;
				}

				//Displays a message for a singular stick
				if (remainStick ==1)
				{
					System.out.println("There is " + remainStick + 
							" stick on the board.");
				}
				
				//Stop the loop when there are no sticks left to take
				else if (remainStick == 0)
				{
					winner = userName;
					break;
				}
				//Displays a message for the number of leftover sticks
				else
				{
					System.out.println("There are " + remainStick + 
							" sticks on the board.");
				}


				//When user input is acceptable
				while(takeStick != 1 || takeStick != 2 || takeStick != 3)
				{
					System.out.print(userName + 
							": How many sticks do you take (1-"
							//Chooses minimum value of the two variables
							+ Math.min(remainStick, MAX_TAKE_STICK) + ")? ");
					//Determine whether input is an integer or not
					if (input.hasNextInt())
					{
						takeStick = input.nextInt();
						input.nextLine();
						//
						if (takeStick == 1 || takeStick == 2 || takeStick == 3)
						{
							break;
						}
						System.out.println("Please enter a number between 1 and 3.");
					}
					else
					{
						wrongStick = input.nextLine();
						System.out.println("Error: expected a number between "
								+ "1 and 3 but found: " + wrongStick);
					}

				}

				remainStick -= takeStick;
				
				if (remainStick ==1)
				{
					System.out.println("There is " + remainStick + 
							" stick on the board.");
				}
				else if (remainStick == 0)
				{
					break;
				}
				else
				{
					System.out.println("There are " + remainStick + 
							" sticks on the board.");
				}

			}
			//Stops the loop when there are no sticks left to take
			while (remainStick != 0);

			//Prints results according to the variable "winner"
			if (winner.equals(userName))
			{
				System.out.println(userName + " wins. " + friendName + " loses.");
			}
			else
				System.out.println(friendName + " wins. " + userName + " loses.");

		}

		System.out.println();
		System.out.println("=========================================");
		System.out.println("Thank you for playing the Game of Sticks!");


		input.close();
	}

	/**
	 * This method encapsulates the code for prompting the user for a number and
	 * verifying the number is within the expected bounds.
	 * 
	 * @param input
	 *            The instance of the Scanner reading System.in.
	 * @param prompt
	 *            The prompt to the user requesting a number within a specific
	 *            range.
	 * @param min
	 *            The minimum acceptable number.
	 * @param max
	 *            The maximum acceptable number.
	 * @return The number entered by the user between and including min and max.
	 */
	static int promptUserForNumber(Scanner input, String prompt, int min, int max) {
		return -1; // TODO change to return valid user input.
	}

	/**
	 * This method has one person play the Game of Sticks against another
	 * person.
	 * 
	 * @param input
	 *            An instance of Scanner to read user answers.
	 * @param startSticks
	 *            The number of sticks to start the game with.
	 * @param player1Name
	 *            The name of one player.
	 * @param player2Name
	 *            The name of the other player.
	 * 
	 *            As a courtesy, player2 is considered the friend and gets to
	 *            pick up sticks first.
	 * 
	 */
	static void playAgainstFriend(Scanner input, int startSticks, String player1Name, String player2Name) {
		// TODO
	}

	/**
	 * Make a choice about the number of sticks to pick up when given the number
	 * of sticks remaining.
	 * 
	 * Algorithm: If there are less than Config.MAX_ACTION sticks remaining,
	 * then pick up the minimum number of sticks (Config.MIN_ACTION). If
	 * Config.MAX_ACTION sticks remain, randomly choose a number between
	 * Config.MIN_ACTION and Config.MAX_ACTION. Use Config.RNG.nextInt(?) method
	 * to generate an appropriate random number.
	 * 
	 * @param sticksRemaining
	 *            The number of sticks remaining in the game.
	 * @return The number of sticks to pick up, or 0 if sticksRemaining is <= 0.
	 */
	static int basicChooseAction(int sticksRemaining) {
		return -1; // TODO change to appropriate value
	}

	/**
	 * This method has a person play against a computer. Call the
	 * promptUserForNumber method to obtain user input. Call the aiChooseAction
	 * method with the actionRanking row for the number of sticks remaining.
	 * 
	 * If the strategyTable is null, then this method calls the
	 * basicChooseAction method to make the decision about how many sticks to
	 * pick up. If the strategyTable parameter is not null, this method makes
	 * the decision about how many sticks to pick up by calling the
	 * aiChooseAction method.
	 * 
	 * @param input
	 *            An instance of Scanner to read user answers.
	 * @param startSticks
	 *            The number of sticks to start the game with.
	 * @param playerName
	 *            The name of one player.
	 * @param strategyTable
	 *            An array of action rankings. One action ranking for each stick
	 *            that the game begins with.
	 * 
	 */
	static void playAgainstComputer(Scanner input, int startSticks, String playerName, int[][] strategyTable) {
		// TODO
	}

	/**
	 * This method chooses the number of sticks to pick up based on the
	 * sticksRemaining and actionRanking parameters.
	 * 
	 * Algorithm: If there are less than Config.MAX_ACTION sticks remaining then
	 * the chooser must pick the minimum number of sticks (Config.MIN_ACTION).
	 * For Config.MAX_ACTION or more sticks remaining then pick based on the
	 * actionRanking parameter.
	 * 
	 * The actionRanking array has one element for each possible action. The 0
	 * index corresponds to Config.MIN_ACTION and the highest index corresponds
	 * to Config.MAX_ACTION. For example, if Config.MIN_ACTION is 1 and
	 * Config.MAX_ACTION is 3, an action can be to pick up 1, 2 or 3 sticks.
	 * actionRanking[0] corresponds to 1, actionRanking[1] corresponds to 2,
	 * etc. The higher the element for an action in comparison to other
	 * elements, the more likely the action should be chosen.
	 * 
	 * First calculate the total number of possibilities by summing all the
	 * element values. Then choose a particular action based on the relative
	 * frequency of the various rankings. For example, if Config.MIN_ACTION is 1
	 * and Config.MAX_ACTION is 3: If the action rankings are {9,90,1}, the
	 * total is 100. Since actionRanking[0] is 9, then an action of picking up 1
	 * should be chosen about 9/100 times. 2 should be chosen about 90/100 times
	 * and 1 should be chosen about 1/100 times. Use Config.RNG.nextInt(?)
	 * method to generate appropriate random numbers.
	 * 
	 * @param sticksRemaining
	 *            The number of sticks remaining to be picked up.
	 * @param actionRanking
	 *            The counts of each action to take. The 0 index corresponds to
	 *            Config.MIN_ACTION and the highest index corresponds to
	 *            Config.MAX_ACTION.
	 * @return The number of sticks to pick up. 0 is returned for the following
	 *         conditions: actionRanking is null, actionRanking has a length of
	 *         0, or sticksRemaining is <= 0.
	 * 
	 */
	static int aiChooseAction(int sticksRemaining, int[] actionRanking) {
		return -1; // TODO change to appropriate value
	}

	/**
	 * This method initializes each element of the array to 1. If actionRanking
	 * is null then method simply returns.
	 * 
	 * @param actionRanking
	 *            The counts of each action to take. Use the length of the
	 *            actionRanking array rather than rely on constants for the
	 *            function of this method.
	 */
	static void initializeActionRanking(int[] actionRanking) {
		// TODO
	}

	/**
	 * This method returns a string with the number of sticks left and the
	 * ranking for each action as follows.
	 * 
	 * An example: 10 3,4,11
	 * 
	 * The string begins with a number (number of sticks left), then is followed
	 * by 1 tab character, then a comma separated list of rankings, one for each
	 * action choice in the array. The string is terminated with a newline (\n)
	 * character.
	 * 
	 * @param sticksLeft
	 *            The number of sticks left.
	 * @param actionRanking
	 *            The counts of each action to take. Use the length of the
	 *            actionRanking array rather than rely on constants for the
	 *            function of this method.
	 * @return A string formatted as described.
	 */
	static String actionRankingToString(int sticksLeft, int[] actionRanking) {
		return ""; // TODO change to return a String with the specified format.
	}

	/**
	 * This method updates the actionRanking based on the action. Since the game
	 * was lost, the actionRanking for the action is decremented by 1, but not
	 * allowing the value to go below 1.
	 * 
	 * @param actionRanking
	 *            The counts of each action to take. The 0 index corresponds to
	 *            Config.MIN_ACTION and the highest index corresponds to
	 *            Config.MAX_ACTION.
	 * @param action
	 *            A specific action between and including Config.MIN_ACTION and
	 *            Config.MAX_ACTION.
	 */
	static void updateActionRankingOnLoss(int[] actionRanking, int action) {
		// TODO
	}

	/**
	 * This method updates the actionRanking based on the action. Since the game
	 * was won, the actionRanking for the action is incremented by 1.
	 * 
	 * @param actionRanking
	 *            The counts of each action to take. The 0 index corresponds to
	 *            Config.MIN_ACTION and the highest index corresponds to
	 *            Config.MAX_ACTION.
	 * @param action
	 *            A specific action between and including Config.MIN_ACTION and
	 *            Config.MAX_ACTION.
	 */
	static void updateActionRankingOnWin(int[] actionRanking, int action) {
		// TODO
	}

	/**
	 * Allocates and initializes a 2 dimensional array. The number of rows
	 * corresponds to the number of startSticks. Each row is an actionRanking
	 * with an element for each possible action. The possible actions range from
	 * Config.MIN_ACTION to Config.MAX_ACTION. Each actionRanking is initialized
	 * with the initializeActionRanking method.
	 * 
	 * @param startSticks
	 *            The number of sticks the game is starting with.
	 * @return The two dimensional strategyTable, properly initialized.
	 */
	static int[][] createAndInitializeStrategyTable(int startSticks) {
		return null; // TODO change to return the array
	}

	/**
	 * This formats the whole strategyTable as a string utilizing the
	 * actionRankingToString method. For example:
	 * 
	 * Strategy Table Sticks Rankings 10 3,4,11 9 6,2,5 8 7,3,1 etc.
	 * 
	 * The title "Strategy Table" should be proceeded by a \n.
	 * 
	 * @param strategyTable
	 *            An array of actionRankings.
	 * @return A string containing the properly formatted strategy table.
	 */
	static String strategyTableToString(int[][] strategyTable) {
		return ""; // TODO change to return the formatted String
	}

	/**
	 * This updates the strategy table since a game was won.
	 * 
	 * The strategyTable has the set of actionRankings for each number of sticks
	 * left. The actionHistory array records the number of sticks the user took
	 * when a given number of sticks remained on the table. Remember that
	 * indexing starts at 0. For example, if actionHistory at index 6 is 2, then
	 * the user took 2 sticks when there were 7 sticks remaining on the table.
	 * For each action noted in the history, this calls the
	 * updateActionRankingOnWin method passing the corresponding action and
	 * actionRanking. After calling this method, the actionHistory is cleared
	 * (all values set to 0).
	 * 
	 * @param strategyTable
	 *            An array of actionRankings.
	 * 
	 * @param actionHistory
	 *            An array where the index indicates the sticks left and the
	 *            element is the action that was made.
	 */
	static void updateStrategyTableOnWin(int[][] strategyTable, int[] actionHistory) {
		// TODO
	}

	/**
	 * This updates the strategy table for a loss.
	 * 
	 * The strategyTable has the set of actionRankings for each number of sticks
	 * left. The actionHistory array records the number of sticks the user took
	 * when a given number of sticks remained on the table. Remember that
	 * indexing starts at 0. For example, if actionHistory at index 6 is 2, then
	 * the user took 2 sticks when there were 7 sticks remaining on the table.
	 * For each action noted in the history, this calls the
	 * updateActionRankingOnLoss method passing the corresponding action and
	 * actionRanking. After calling this method, the actionHistory is cleared
	 * (all values set to 0).
	 * 
	 * @param strategyTable
	 *            An array of actionRankings.
	 * @param actionHistory
	 *            An array where the index indicates the sticks left and the
	 *            element is the action that was made.
	 */
	static void updateStrategyTableOnLoss(int[][] strategyTable, int[] actionHistory) {
		// TODO
	}

	/**
	 * This method simulates a game between two players using their
	 * corresponding strategyTables. Use the aiChooseAction method to choose an
	 * action for each player. Record each player's actions in their
	 * corresponding history array. This method doesn't print out any of the
	 * actions being taken. Player 1 should make the first move in the game.
	 * 
	 * @param startSticks
	 *            The number of sticks to start the game with.
	 * @param player1StrategyTable
	 *            An array of actionRankings.
	 * @param player1ActionHistory
	 *            An array for recording the actions that occur.
	 * @param player2StrategyTable
	 *            An array of actionRankings.
	 * @param player2ActionHistory
	 *            An array for recording the actions that occur.
	 * @return 1 or 2 indicating which player won the game.
	 */
	static int playAiVsAi(int startSticks, int[][] player1StrategyTable, int[] player1ActionHistory,
			int[][] player2StrategyTable, int[] player2ActionHistory) {
		return -1; // TODO change to return the winning player.
	}

	/**
	 * This method has the computer play against itself many times. Each time it
	 * plays it records the history of its actions and uses those actions to
	 * improve its strategy.
	 * 
	 * Algorithm: 1) Create a strategy table for each of 2 players with
	 * createAndInitializeStrategyTable. 2) Create an action history for each
	 * player. An action history is a single dimension array of int. Each index
	 * in action history corresponds to the number of sticks remaining where the
	 * 0 index is 1 stick remaining. 3) For each game, 4) Call playAiVsAi with
	 * the return value indicating the winner. 5) Call updateStrategyTableOnWin
	 * for the winner and 6) Call updateStrategyTableOnLoss for the loser. 7)
	 * After the games are played then the strategyTable for whichever strategy
	 * won the most games is returned. When both players win the same number of
	 * games, return the first player's strategy table.
	 * 
	 * @param startSticks
	 *            The number of sticks to start with.
	 * @param numberOfGamesToPlay
	 *            The number of games to play and learn from.
	 * @return A strategyTable that can be used to make action choices when
	 *         playing a person. Returns null if startSticks is less than
	 *         Config.MIN_STICKS or greater than Config.MAX_STICKS. Also returns
	 *         null if numberOfGamesToPlay is less than 1.
	 */
	static int[][] trainAi(int startSticks, int numberOfGamesToPlay) {
		return null; // TODO return the strategy table of the winning player
	}

}
