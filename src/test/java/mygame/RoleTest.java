package mygame;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import commons.Action;
import commons.Field;
import commons.Position;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class RoleTest extends TestCase {

	
	
	Role role = new Role();
	
	
	Action action;
	
	@Before
	public void prepareTestData() {
		action = Action.R;
		Field field = new Field();
		field.setHeight(30);
		field.setWidth(30);
		role.setField(field);
		role.setPosition(new Position(0,1));
	}
	
	@Test(expected = Test.None.class)
	public void testIsFruitfulAction() {
	  role.isFruitfulAction(action, role);
	}
}
