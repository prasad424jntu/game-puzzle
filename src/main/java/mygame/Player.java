package mygame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import commons.Action;
import commons.Position;
import commons.Utils;

public class Player extends Role implements Serializable{
	
	Position actionModeStartPosition;

	/*
	 * This is the method executed to help player do the action by processing his input action.
	 */
	public void act() {
		while(true) {
				if(!isActed()) {
					
					System.out.println("Enter your action");
					Scanner scanner = new Scanner(System.in);
					String strAction = scanner.nextLine();

					if(!Arrays.asList(new String[]{"J","K","L","R","U","D","F","B","E","S","Q"}).contains(strAction) ) {
						continue;
					}else {
						Action action = Utils.convertInputActionToEnum(strAction);
						if(action.equals(Action.S)) {
							this.persistGame();
						}if(action.equals(Action.Q)) {
							System.out.println("you stopped the game.");
							System.exit(0);
						}
						if(action.equals(Action.E)) {
							actExploreMode();
							continue;
						}
						if(isFruitfulAction(action, field.getPlayer())) {
							System.out.println("Player Moved");
							///field.setPlayer(this);
							this.setAction(action);
							this.setActed(true);
							field.printLayOut();
							//If Action is explore only Player will be moving.
							
							break;
						}else {
							continue;
						}
					}
				}
		}
		
	}
	
	/**
	 * This method is called when player want to  go to explore mode.All the opponents will be in standstill.
	 */
	public void actExploreMode() {
		actionModeStartPosition = field.getPlayer().getPosition();
		System.out.println("You are in action mode.Note that you have to click C to come out of action mode.Actions S and Q does not have any impact in this mode ");
		while(true) {
				
				System.out.println("Enter your action in explore mode");
				Scanner scanner = new Scanner(System.in);
				String strAction = scanner.nextLine();

				if(!Arrays.asList(new String[]{"J","K","L","R","U","D","F","B","C"}).contains(strAction) ) {
					continue;
				}else {
					Action action = Utils.convertInputActionToEnum(strAction);
					if(action.equals(Action.C)) {
						System.out.println("Closing the explore Mode.Your position will be restored back.");
						this.setPosition(actionModeStartPosition);
						field.getPlayer().setPosition(actionModeStartPosition);
						field.printLayOut();
						return;
					}
					if(isFruitfulAction(action, field.getPlayer())) {
						System.out.println("Player Moved in action mode");
						this.setAction(action);
						field.printLayOut();
					}else {
						continue;
					}
				}
	}
		
	}
	
	public String printImage() {
		return "@";
	}
	
	public Position getPlayerPosition(Action action){
		return null;
	}
	
	/**
	 * this method is used to persist the game if user want to save and resume.
	 */
	public void persistGame() {
		try {
			 File file = new File("player.txt");
			 file.createNewFile();
	         FileOutputStream fileOut =
	         new FileOutputStream("player.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Game Saved.");
	         System.exit(0);
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public static void main(String[] args) {
		
		Player player = new Player();
		player.persistGame();
		
	}
	
}
