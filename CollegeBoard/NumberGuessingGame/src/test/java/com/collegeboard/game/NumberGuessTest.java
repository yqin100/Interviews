package com.collegeboard.game;

import org.junit.Assert;
import org.junit.Test;

import com.collegeboard.exception.NumberGuessException;

public class NumberGuessTest {
	@Test
	public void testInitZeroArgs() {
		try {
			String[] args = new String[] {};
			NumberGuess numberGuess = new NumberGuess(args);
			numberGuess.init(args);
			Assert.assertTrue(true);
		} catch (NumberGuessException e) {
			Assert.fail(e.getMessage());
		}
	}	
	
	@Test
	public void testInitOneArgsValid() {
		try {
			String[] args = new String[] {"1"};
			NumberGuess numberGuess = new NumberGuess(args);
			numberGuess.init(args);
			Assert.assertTrue(true);
		} catch (NumberGuessException e) {
			Assert.fail(e.getMessage());
		}

		try {
			String[] args = new String[] {"123"};
			NumberGuess numberGuess = new NumberGuess(args);
			numberGuess.init(args);
			Assert.assertTrue(true);
		} catch (NumberGuessException e) {
			Assert.fail(e.getMessage());
		}
	}	
	
	@Test
	public void testInitOneArgsInvalid() {
		try {
			String[] args = new String[] {"0"};
			NumberGuess numberGuess = new NumberGuess(args);
			numberGuess.init(args);
			Assert.fail("Number 0 should be invalid");
		} catch (NumberGuessException e) {
			Assert.assertTrue(true);
		}

		try {
			String[] args = new String[] {"-1"};
			NumberGuess numberGuess = new NumberGuess(args);
			numberGuess.init(args);
			Assert.fail("Number 0 should be invalid");
		} catch (NumberGuessException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitMultipleArgsInvalid() {
		try {
			String[] args = new String[] {"123", "123"};
			NumberGuess numberGuess = new NumberGuess(args);
			numberGuess.init(args);
			Assert.fail("There can only be one user argument");
		} catch (NumberGuessException e) {
			Assert.assertTrue(true);
		}		
	}
}
