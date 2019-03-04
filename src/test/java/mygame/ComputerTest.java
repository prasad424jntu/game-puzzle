package mygame;

import org.junit.Test;

import commons.Action;
import junit.framework.TestCase;

public class ComputerTest extends TestCase {
	
	Computer computer = new Computer();
	
	@Test(expected = Test.None.class)
	public void testAct() {
		computer.act();
	}
	
	@Test
	public void testGenerateRandomAtion() {
	assertTrue(computer.generateRandomAtion() instanceof Action);
		
	}
	

}
