package mygame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import commons.Action;
import commons.Utils;

public class Computer extends Role implements Serializable{
	
	Map<Integer, String> step = new HashMap<>();
	
	Computer(){
		step.put(0, "L");
		step.put(1, "R");
		step.put(2, "U");
		step.put(3, "D");
		step.put(4, "F");
		step.put(5, "B");
		step.put(6, "J");
		step.put(7, "P");
	}

	
	public void act() {
		// TODO Auto-generated method stub
		while(true) {
			if(!isActed()) {
				Action computerAction =	this.generateRandomAtion();
				if(isFruitfulAction(computerAction, field.getSystemPlayers().get(0))) {
					System.out.println("Computer Moved");
					this.setAction(computerAction);
					this.setActed(true);
					field.printLayOut();
					break;
				}else {
					continue;
				}
			}
		}
		
	}
	
	public Action generateRandomAtion() {
		int random = (int)(Math.random()*(7));
		return Utils.convertInputActionToEnum(step.get(random));
	}
	
	public String printImage(int systemPlayerNumber) {
		
		return systemPlayerNumber+"";
	}

}
