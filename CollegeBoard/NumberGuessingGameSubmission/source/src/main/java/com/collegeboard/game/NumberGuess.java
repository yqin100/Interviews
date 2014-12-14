package com.collegeboard.game;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.collegeboard.exception.NumberGuessException;

/**
 * A number-guessing game with the following rules:
 * 
 * The user chooses a number in his mind and types "ready" to indicate to the computer that he is ready to
 * begin playing.
 * 
 * The computer asks a series of questions to arrive at the number the user has in mind. The user can only
 * respond with "higher", "lower" or "yes".
 * 
 * The game ends when the user responds with "yes" or "end".
 * 
 * @author Yue Chao Qin
 */
public class NumberGuess {
	/**
	 * SL4J logger.
	 */
    private final Logger log = LoggerFactory.getLogger(getClass());
    /**
     * Minimum number for the guessing game.  Value = 1;
     */
    private int minNumber = 1;
    /**
     * Maximum number for the guessing game.  Default value = 2147483647.
     */
    private int maxNumber = Integer.MAX_VALUE;
    /**
     * Object to read user input.
     */
	private Scanner scanner = new Scanner(System.in);
    
    /**
     * Contructor for the number-guessing game.
     * @param args User can define the max number for the guessing game.  It has to be a positive number from 1 to max (default is 2147483647).
     */
	public NumberGuess(String[] args) {
		try {
			System.out.println("Welcome to the Number Guessing Game");
			log.info("Starting new game");
			init(args);
		} catch (Throwable e) {
			System.out.println("ERROR (stopping game...) => " + e.getMessage());
			log.error(e.getMessage(),e);
		}
	}
	
	/**
	 * Initiates the program by parsing the user's parameters.
	 * @param args User can define the max number for the guessing game.  It has to be a positive number from 1 to max (default is 2147483647).
	 * @throws NumberGuessException Thrown if user arguments are greater then 1 or if it's not a positive number.
	 */
	void init(String []args) throws NumberGuessException {
		String usage = "Usage: <max_positive_number>;";
		if (args.length == 1) {
			int maxNumber = Integer.parseInt(args[0]);
			if (maxNumber < minNumber) {
				throw new NumberGuessException(usage + "  Number has to be >= " + minNumber);
			}
			this.maxNumber = maxNumber; 
		}
		else if (args.length > 1) {
			throw new NumberGuessException(usage + "  Provide a positive number for the max value in the guessing game.  If omitted, the max number is " + maxNumber);
		}	
	}
	
	/**
	 * Display rules to user.
	 */
	private void displayRules() {		
		System.out.println("Rules: \t\t1) Please think of a number from " + minNumber + " to " + maxNumber);
		System.out.println("\t\t2) Type 'higher' if your number is higher");
		System.out.println("\t\t3) Type 'lower' if your number is lower");
		System.out.println("\t\t4) Type 'yes' if the number matches your number");
		System.out.println("\t\t4) Type 'end' to end the game");
		System.out.println("\t\t*** the input strings are case sensitive, lower case only! ***");
	}
	
	/**
	 * Starts the game with instructions and user input.
	 * @throws NumberGuessException Exception thrown if the new guess number has a minimum number that greater than the maximum number.
	 */
	public void start() throws NumberGuessException {
		displayRules();
		
		System.out.println("Hit ENTER to start game...");
		scanner.nextLine();
		startGame();
	}
	
	/**
	 * Start the game logic.
	 * @throws NumberGuessException Exception thrown if the new guess number has a minimum number that greater than the maximum number.
	 */
	void startGame() throws NumberGuessException {
		boolean found = false;
		boolean checkPastGuessValues = true;
		int lastMaxNumber = maxNumber;
		int lastMinNumber = minNumber;
		int guessNumber = lastMaxNumber / 2;
		int count = 1;
		Set<Integer> lastValues = new HashSet<Integer>();
		
		while (!found) {
			log.info("Guess number " + guessNumber);
			if (checkPastGuessValues) {
				if (lastValues.contains(guessNumber)) {
					System.out.println("We already presented this number to you => " + guessNumber + ",  You probably forgot your number, ending game now...");
					log.error("User was already presented with number => " + guessNumber + ", ending game.");
					break;
				}
				else {
					lastValues.add(guessNumber);
				}
				count++;
			}
			checkPastGuessValues = true;

			System.out.println(count + ") Is your number " + guessNumber + "?");
			String userInput = scanner.next();
			switch (userInput) {
				case "higher" :
					// do higher
					lastMinNumber = guessNumber;
					guessNumber = getNewGuessNumber(lastMinNumber, lastMaxNumber);
					break;
				case "lower" :
					// do lower
					lastMaxNumber = guessNumber;
					guessNumber = getNewGuessNumber(lastMinNumber, lastMaxNumber);
					break;
				case "yes" :
					found = true;
					System.out.println("*** Found your number => " + guessNumber + " ***");		
					log.info("Found number => " + guessNumber);
					break;
				case "end" :
					found = true;
					System.out.println("Ending game...");
					log.info("Quitting game...");
					break;
				default:
					checkPastGuessValues = false;
					System.out.println("Do not recognize option '" + userInput + "'.  Please type one of the following options (case sensitive) ['higher', 'lower', 'yes', 'end'].");
					log.error("User input '" + userInput + "' not valid");
			}
		}
		
		System.out.println("Thanks for playing!");
		log.info("Game finished");
	}
	
	/**
	 * Get the next guess number, should be a number between the min/max number.
	 * @param lastMinNumber Minimum number, should be smaller than max.
	 * @param lastMaxNumber Maximum number, should be greater than min.
	 * @return The number between min/max.
	 * @throws NumberGuessException Exception thrown when min is greater than max.
	 */
	int getNewGuessNumber(int lastMinNumber, int lastMaxNumber) throws NumberGuessException {
		if (lastMinNumber > lastMaxNumber) {
			throw new NumberGuessException("The min (" + lastMinNumber + ") number has to be smaller than the max (" + lastMaxNumber + ") number.");
		}
		return lastMinNumber + ((lastMaxNumber - lastMinNumber) / 2);
	}
	
	/**
	 * Start of program.
	 * @param args User arguments.
	 * @throws NumberGuessException Exception thrown if the new guess number has a minimum number that greater than the maximum number.
	 */
	public static void main(String[] args) throws NumberGuessException {
		NumberGuess numberGuess = new NumberGuess(args);
		numberGuess.start();
	}
}
