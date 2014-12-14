package com.collegeboard.game;

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
     * Contructor for the number-guessing game.
     * @param args User can define the max number for the guessing game.  It has to be a positive number from 1 to max (default is 2147483647).
     */
	public NumberGuess(String[] args) {
		try {
			log.info("Welcome to the Number Guessing Game");
			init(args);
		} catch (Throwable e) {
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
		}
		else if (args.length > 1) {
			throw new NumberGuessException(usage + "  Provide a positive number for the max value in the guessing game.  If omitted, the max number is " + maxNumber);
		}	
		
		log.info("Please think of a number from " + minNumber + " to " + maxNumber);
	}
	
	/**
	 * Start of program.
	 * @param args User arguments.
	 */
	public static void main(String[] args) {
		new NumberGuess(args);
	}
}
