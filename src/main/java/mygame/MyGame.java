package mygame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import commons.Action;
import commons.Field;
import commons.Level;
import commons.Position;
import commons.Utils;

public class MyGame implements Serializable{
	
	Level level;
	Field field = new Field();
	boolean resumeFlow =false;
	Player resumeflowPlayer;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			MyGame myGame = new MyGame();
			myGame.checkResumeFlow();
			if(myGame.isResumeFlow()) {
				myGame.setField(myGame.getResumeflowPlayer().getField());
				myGame.deletePlayerFile();
			}else {
				init();
			}
			Player character = myGame.buildCharacter(scanner);
			myGame.start(scanner);
			printGameInstructions();
			myGame.begin(scanner,character);
			
		}finally {
			scanner.close();
		}
		
	}
	
	/**
	 * Method used to prompt user with initial introduction
	 */
	public static void init() {
		System.out.println("Hello Player....Welcome to the game world");
		System.out.println("We want you to start with your character");
		System.out.println("Please enter the gender of the character M/F:");
	}
	
	/**
	 * This method is used to build the character
	 * @param scanner
	 * @return
	 */
	public Player buildCharacter(Scanner scanner) {
			
			if(resumeFlow) return resumeflowPlayer;
		 	
		 	Player character = new Player();
			
			char gender = scanner.next().charAt(0);
			while(gender != 'M' && gender != 'F' ) {
				System.out.println("Please enter right gender");
				gender = scanner.next().charAt(0);
			}
			scanner = new Scanner(System.in);
			System.out.println("Please enter the name of the character:");
			String name = scanner.nextLine();
			while(name == null || name.equals("") || name.trim().length()<4) {
				System.out.println("Name of the character cannot be empty or less than 4 characters\n");
				System.out.println("Please enter name greater than 4 characters");
				name = scanner.nextLine();
				
			}
			
			System.out.println("Welcome " + name);
			buildLevel(scanner);
			character.setName(name);
			character.setGender(gender+"");
			character.build();
			return character;
	 }
	
	/**
	 * THis method builds the level and constructs the player and obstacles in the field based on the level user chosen.
	 * @param scanner
	 */
	public void buildLevel(Scanner scanner) {
		scanner = new Scanner(System.in);
		System.out.println("Please choose the game level B(beginner), M(Medium), H(hard):");
		String hardness = scanner.nextLine();
		while(!Arrays.asList(new String[]{"B","M","H"}).contains(hardness) ) {
			System.out.println("Please enter valid Level");
			hardness = scanner.nextLine();
		}
		Level level = Utils.convertInputLevelToEnum(hardness);
		System.out.println(level.toString());
		this.setLevel(level);
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
	 
	/**
	 * This method is used to print game instructions
	 */
	 public static void printGameInstructions() {
		 	System.out.println("Your game Stated..Your opponent is computer...Please see below instructions");
			System.out.println("Press arrows to move in directions");
			System.out.println("Press J to Jump");
			System.out.println("Press K to Kick a punch");
			System.out.println("Press L to Move Left");
			System.out.println("Press R to Move Right");
			System.out.println("Press U to Move Up");
			System.out.println("Press D to Move down");
			System.out.println("Press F to Move forward");
			System.out.println("Press B to Move backward");
			System.out.println("Press E to Explore.Note when you are exploring opponent(s)/enemy() will be in stand still.");
			System.out.println("Press S to Save the game so that you will resume later point of time");
			System.out.println("Press Q to quit the game anytime.");
	 }
	 
	 /**
	  * This method takes user prompt for the start or end of the game.
	  * @param scanner
	  */
	 public void start(Scanner scanner) {
		 	System.out.println("To start/resume the game press Y. Press any Key to quit");
			scanner = new Scanner(System.in);
			if(scanner.next().charAt(0) != 'Y') {
				System.exit(0);
			}
	 }
	 
	 /**
	  * This method takes user prompt for the start or end of the game.
	  * @param scanner
	  */
	 public void begin(Scanner scanner,Player role) {
		 	role.setDifficulty(getLevel());
			Computer computer = null; 
			if(isResumeFlow()) {
				computer = field.getSystemPlayers().get(0);
				
			}else {
				computer = new Computer();
				computer.setPoints(level.getPoints());
				computer.setStamina(level.getStamina());
				computer.setLooseGainPoints(level.getLooseGainPoints());
				computer.setPosition(new Position(level.getFieldWidth()-1,level.getFieldHeight()/2));
			}
			//this list is maintained to extend the functionality so that we can increase 
			//the computer opponents in future
			List<Computer> systemPlayers =new ArrayList<>();
			systemPlayers.add(computer);
			//Thread systemPlayer = new Thread(computer);
			role.setField(field);
			computer.setField(field);
			computer.setName("1");
			field.setSystemPlayers(systemPlayers);
			role.setField(field);
			computer.setField(field);
			while(true) {
					role.setActed(false);
					role.act();
					computer.setActed(false);
					computer.act();
			}
		}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public boolean isResumeFlow() {
		return resumeFlow;
	}

	public void setResumeFlow(boolean resumeFlow) {
		this.resumeFlow = resumeFlow;
	}

	public Player getResumeflowPlayer() {
		return resumeflowPlayer;
	}

	public void setResumeflowPlayer(Player resumeflowPlayer) {
		this.resumeflowPlayer = resumeflowPlayer;
	}

	/**
	 * This method checks if the action performed by the user to move his position to next place is fruitful
	 * By checking if the next position is with in the boundaries of the field and no obstacles and system players are there
	 * @param action
	 * @param role
	 * @return
	 */
	/*public boolean isFruitfulAction(Action action,Role role) {
		Position nextposition = Utils.nextPosition(action, role.getPosition());
		if(field.isPositionValid(nextposition)) {
			role.setPosition(nextposition);
			return true;
		}
		return false;
	}*/
	
	
	/**
	 * This method is used to check the resume flow and de-serializes the persisted Player for resuming the play.
	 * @return
	 */
	private void checkResumeFlow() {
		try {
	         FileInputStream fileIn = new FileInputStream("player.txt");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         resumeflowPlayer = (Player) in.readObject();
	         in.close();
	         fileIn.close();
	         if(resumeflowPlayer != null) {
	        	setResumeFlow(true);
	         }
	      } catch (IOException i) {
	         System.out.println("IO exception happened during deserialization of the player txt file.No resume flow");
	      } catch (ClassNotFoundException c) {
	         System.out.println("Player not found.So No resume flow");
	         c.printStackTrace();
	      }catch(Exception e) {
	    	System.out.println("File deserialization failed with unknown reason.No resum flow");  
	      }
	}
	
	/**
	 * This method is used to delete the player file after deserialization.
	 */
	private void deletePlayerFile() {
		 File file = new File("player.txt");
		 file.delete();
		 System.out.println("file deleted after deserialization.");
	}
	 
	 
}
