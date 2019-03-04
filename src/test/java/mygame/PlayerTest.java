package mygame;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import commons.Action;
import commons.Field;
import commons.Position;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest extends TestCase {

	@InjectMocks
	Player player;
	
	@Mock
	Role role;
	
	
	Action action;
	
	@Before
	public void prepareTestData() {
		action = Action.R;
		player = new Player();
		Field field = new Field();
		field.setHeight(30);
		field.setWidth(30);
		player.setField(field);
		
	}
	
	@Test(expected = Test.None.class)
	public void testAct() {
		String input = "R";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		player.act();
	}
	
	@Test(expected = Test.None.class)
	public void testActExploreMode() {
		String input = "C";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	   // Role role=Mockito.mock(Role.class);
		//Mockito.when(role.isFruitfulAction(Mockito.any(Action.class), Mockito.any(Role.class))).thenReturn(true);
		//Mockito.when(role.getPosition()).thenReturn(new Position(0,0));
		player.actExploreMode();
	}
	
	@Test(expected = Test.None.class)
	public void testPersistGame() {
		player.persistGame();
	}
	
	@After
	public void cleanTestData() {
		 File file = new File("player.txt");
		 if(file.exists()) {
			 file.delete();
		 }
		 System.out.println("file deleted after testing over.");
	}
}
