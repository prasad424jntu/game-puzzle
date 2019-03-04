package commons;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class LevelTest  extends TestCase{
	
	Level level = Level.MEDIUM;
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void testBuildRocks() {
		assertEquals(level.buildRocks().size(),1);
	}
	
	@Test
	public void testBuildWalls() {
		assertEquals(level.buildWalls().size(),1);
	}
	
	@Test
	public void testGenerateObstacle() {
		Obstacle obs= level.generateObstacle(2);
		assertEquals(2, Math.abs(obs.getEndCoardinate().getX()-obs.getStartCoardinate().getX()));
	}

}
