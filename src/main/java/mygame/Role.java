package mygame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import commons.Action;
import commons.Field;
import commons.Level;
import commons.Position;
import commons.Utils;
import commons.Weapon;
import skills.Skill;

public class Role implements Serializable{
	
	String name;
	String gender;
	Level difficulty;
	List<Skill> skills = new ArrayList<Skill>();
	List<Weapon> weapons = new ArrayList<Weapon>();
	boolean acted= false;
	Action action = Action.J;
	Position position;
	Field field;
	int stamina;
	int points;
	int looseGainPoints;
	
	public Role() {
		position = new Position();
	}
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public Level getDifficult() {
		return difficulty;
	}




	public void setDifficult(Level difficult) {
		this.difficulty = difficult;
	}




	public List<Skill> getSkills() {
		return skills;
	}




	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}




	public List<Weapon> getWeapons() {
		return weapons;
	}




	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}




	public Level getDifficulty() {
		return difficulty;
	}




	public void setDifficulty(Level difficulty) {
		this.difficulty = difficulty;
	}




	public boolean isActed() {
		return acted;
	}




	public void setActed(boolean acted) {
		this.acted = acted;
	}




	public Action getAction() {
		return action;
	}




	public void setAction(Action action) {
		this.action = action;
	}


	public Position getPosition() {
		return position;
	}


	public void setPosition(Position position) {
		this.position = position;
	}


	public Role build(){
		Role character = new Role();
		character.name = this.name;
		character.gender = this.gender;
		character.difficulty = this.difficulty;
		character.skills = this.skills;
		character.weapons = this.weapons;
		return character;
		
	}
	
	public void setPosition(Action action) {
		
	switch (action) {
		case L:
			
			break;
		case R:
			
			break;
		case U:
			
			break;
		case D:
			
			break;
		case F:
			
			break;
		case B:
			
			break;
		case J:
			
			break;
		case K:
			
			break;
		default:
			break;
		}
		
	}


	public Field getField() {
		return field;
	}


	public void setField(Field field) {
		this.field = field;
	}
	
	
	
	public int getLooseGainPoints() {
		return looseGainPoints;
	}


	public void setLooseGainPoints(int looseGainPoints) {
		this.looseGainPoints = looseGainPoints;
	}


	/**
	 * This method checks if the action performed by the user to move his position to next place is fruitful
	 * By checking if the next position is with in the boundaries of the field and no obstacles and system players are there
	 * @param action
	 * @param role
	 * @return
	 */
	public boolean isFruitfulAction(Action action,Role role) {
		Position nextposition = Utils.nextPosition(action, role.getPosition());
		if(field.getOccupaiedPositions().contains(nextposition)) {
			role.setPoints(role.getPoints()-role.getLooseGainPoints());
			if(role.getPoints()<=0) {
				if(role instanceof Player) {
					System.out.println("You Lost.Game over");
				}else {
					System.out.println("Computer lost...Game over");
				}
				System.exit(0);
			}
		}
		if(field.isPositionValid(nextposition)) {
			role.setPosition(nextposition);
			return true;
		}
		return false;
	}


	public int getStamina() {
		return stamina;
	}


	public void setStamina(int stamina) {
		this.stamina = stamina;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}
	
	


}
