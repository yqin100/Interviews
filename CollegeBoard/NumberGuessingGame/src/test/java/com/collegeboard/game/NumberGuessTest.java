package com.collegeboard.game;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.collegeboard.exception.NumberGuessException;

public class NumberGuessTest {
	@Rule
	public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
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
	
	@Test
	public void testGetNewGuessNumberException() {
		String[] args = new String[] {};
		NumberGuess numberGuess = new NumberGuess(args);
		int lastMinNumber;
		int lastMaxNumber;

		lastMinNumber = 100;
		lastMaxNumber = 1;
		try {
			numberGuess.getNewGuessNumber(lastMinNumber, lastMaxNumber);
			Assert.fail("Min number " + lastMinNumber + " cannot be larger than max number " + lastMaxNumber);
		} catch (NumberGuessException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testGetNewGuessNumber() throws NumberGuessException {
		String[] args = new String[] {};
		NumberGuess numberGuess = new NumberGuess(args);
		int lastMinNumber;
		int lastMaxNumber;

		lastMinNumber = 52;
		lastMaxNumber = 67;
		Assert.assertEquals(59, numberGuess.getNewGuessNumber(lastMinNumber, lastMaxNumber));
		lastMinNumber = 52;
		lastMaxNumber = 54;
		Assert.assertEquals(53, numberGuess.getNewGuessNumber(lastMinNumber, lastMaxNumber));
		lastMinNumber = 52;
		lastMaxNumber = 53;
		Assert.assertEquals(52, numberGuess.getNewGuessNumber(lastMinNumber, lastMaxNumber));
	}
	
	@Test
	public void testGameEnd() throws NumberGuessException, IOException, InterruptedException {
		String[] args = new String[] {"100"};

		systemInMock.provideText("start\r\n"
				+ "end\r\n");
		NumberGuess numberGuess = new NumberGuess(args);
		try {
			numberGuess.start();
			Assert.assertTrue(true);
		} catch (NumberGuessException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testGameYes() throws NumberGuessException, IOException, InterruptedException {
		String[] args = new String[] {"100"};

		systemInMock.provideText("start\r\n"
				+ "yes\r\n");
		NumberGuess numberGuess = new NumberGuess(args);
		try {
			numberGuess.start();
			Assert.assertTrue(true);
		} catch (NumberGuessException e) {
			Assert.fail();
		}
	}
}
