package commons;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import mygame.Player;

public class FieldTest extends TestCase{
	Field field =new Field();
	
	@Before
	public void init() {
		field.setWidth(1);
		field.setHeight(1);
		Level level = Level.MEDIUM;
		field.setWidth(level.getFieldWidth());
		field.setHeight(level.getFieldHeight());
		field.setWalls(level.buildWalls());
		field.setRocks(level.buildRocks());
		Player player = new Player();
		player.setPoints(level.getPoints());
		player.setStamina(level.getStamina());
		player.setLooseGainPoints(level.getLooseGainPoints());
		player.setPosition(new Position(0,level.getFieldHeight()/2));
		field.setPlayer(player);

	}
	
	@Test
	public void testGetOccupaiedPositions() {
		Position expectedPosition = new Position();
		Position actualPosition =field.getOccupaiedPositions().get(0);
		assertEquals(expectedPosition, actualPosition);
	}
	
	@Test(expected = Test.None.class)
	public void testPrintLayOut() {
		field.printLayOut();
	}
	
	@Test
	public void testDrawObstacle() {
		Obstacle obstacle = new Obstacle();
		Position position = field.getPlayer().getPosition();
		List<Position> occupiedCoardinates = new ArrayList<>();
		occupiedCoardinates.add(position);
		obstacle.setOccupiedCoardinates(occupiedCoardinates);
		assertEquals(true, field.drawObstacle(obstacle, position, false));
	}
	
	@Test()
	public void testPositionValid() {
		Position expectedPosition = new Position(-1,-1);
		assertEquals(field.isPositionValid(expectedPosition),false);
		
	}

}
