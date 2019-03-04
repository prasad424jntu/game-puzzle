package commons;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

	
	Utils util = new Utils();
	
	@Test
	public void testConvertInputLevelToEnum() {
		assertEquals(Level.BEGINNER, util.convertInputLevelToEnum("B"));
	}
	
	@Test
	public void testConvertInputActionToEnum() {
		assertEquals(Action.D, util.convertInputActionToEnum("D"));
	}
	
	@Test
	public void testNextPosition() {
		
		Action action = Action.L;
		Position position = new Position(1,1);
		Position expectedPosition = new Position(0,1);
		assertEquals(expectedPosition, util.nextPosition(action, position));
		
	}
}
