package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mygame.Computer;
import mygame.Player;

public class Field implements Serializable{
	
	int width;
	int height;
	List<Obstacle> walls = new ArrayList<>();
	List<Obstacle> rocks = new ArrayList<>();
	Player player = new Player();
	List<Computer> systemPlayers = new ArrayList<>();
	List<Position> occupaiedPositions = new ArrayList<>();
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<Obstacle> getWalls() {
		return walls;
	}
	public void setWalls(List<Obstacle> walls) {
		this.walls = walls;
	}
	public List<Obstacle> getRocks() {
		return rocks;
	}
	public void setRocks(List<Obstacle> rocks) {
		this.rocks = rocks;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public List<Computer> getSystemPlayers() {
		return systemPlayers;
	}
	public void setSystemPlayers(List<Computer> systemPlayers) {
		this.systemPlayers = systemPlayers;
	}
	
	
	
	public List<Position> getOccupaiedPositions() {
		List<Position> positions = new ArrayList<>();
		positions.add(getPlayer().getPosition());
		for (Obstacle obstacle : getWalls()) {
			positions.addAll(obstacle.getOccupiedCoardinates());
		}
		for (Obstacle obstacle : getRocks()) {
			positions.addAll(obstacle.getOccupiedCoardinates());
		}
		for (Computer computer : getSystemPlayers()) {
			positions.add(computer.getPosition());
		}
		return positions;
	}
	public void setOccupaiedPositions(List<Position> occupaiedPositions) {
		this.occupaiedPositions = occupaiedPositions;
	}
	/**
	 * Method to build the Players Board
	 * @return
	 */
	public Field buildField() {
		Field field = new Field();
		field.width = this.width;
		field.height = this.height;
		field.walls = this.walls;
		field.rocks = this.rocks;
		field.player = this.player;
		field.systemPlayers = this.systemPlayers;
		return field;
	}
	
	/**
	 * Method to print the Player board with all players and obstacles
	 */
	public void printLayOut() {
		for(int i=0;i<width;i++) {
			
			for(int j=0;j<height;j++) {
				Position position = new Position(j,i);
				if(player.getPosition().equals(position)) {
					System.out.print(player.printImage());
					continue;
				}
				for (Obstacle obstacle : rocks) {
					if(drawObstacle(obstacle, position, true))
						continue;
				}
				for (Obstacle obstacle : walls) {
					if(drawObstacle(obstacle, position, false))
						continue;
				}
				for (int index=0;index< systemPlayers.size();index++) {
					Computer computer = systemPlayers.get(index);
					if(computer.getPosition().equals(position)) {
						System.out.print(computer.printImage(index));
					}
					
				}
				System.out.print(" ");
				
			}
			System.out.print("\n");
		}
		
	}
	
	/**
	 * This method is to print the Obstacles(Walls, Rocks) based on the current print position if it belongs to their coordinates
	 * @param obstacle
	 * @param currentPrintPosition
	 * @param isRock
	 */
	public boolean drawObstacle(Obstacle obstacle,Position currentPrintPosition,boolean isRock) {
		if(obstacle.getOccupiedCoardinates().contains(currentPrintPosition)) {
			if(isRock) System.out.print("R");
			else System.out.print("W");
			return true;
		}
		return false;
	}
	
	public boolean isPositionValid(Position position) {
		if(!getOccupaiedPositions().contains(position) 
				&& (0<=position.getX() && position.getX()<=(getWidth()-1))
				&& (0<=position.getY() && position.getY()<=(getHeight()-1))) {
			return true;
		}
		return false;
		
	}
	
	
 
}
