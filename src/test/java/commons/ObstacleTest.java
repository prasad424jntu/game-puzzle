package commons;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class ObstacleTest extends TestCase {
	
	Obstacle obstacle = new Obstacle();
	
	@Before
	public void init() {
		Position startCoardinate = new Position(1,2);
		Position endCoardinate = new Position(2,3);
		obstacle.setStartCoardinate(startCoardinate);
		obstacle.setEndCoardinate(endCoardinate);
		
	}
	
	@Test
	public void testSetOccupiedCoardinates() {
		obstacle.setOccupiedCoardinates();
		assertEquals(2, obstacle.getOccupiedCoardinates().size());
	}

}
