package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum Level implements Serializable{
	
	BEGINNER(40,40,10,10,0,0,0),
	MEDIUM(60,60,20,20,1,1,1),
	HARD(100,100,40,40,2,2,2);
	
	Level(int fieldWidth, int fieldHeight, int points,int stamina,int walls,int rocks,int looseGainPoints){
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.points = points;
		this.stamina = stamina;
		this.walls = walls;
		this.rocks = rocks;
		this.looseGainPoints = looseGainPoints;
		this.obstacleWalls = buildWalls();
		this.obstacleRocks = buildRocks();
	}
	
	private int fieldWidth;
	private int fieldHeight;
	private int points;
	private int stamina;
	private int walls;
	private int rocks;
	private int looseGainPoints;
	List<Obstacle> obstacleWalls;
	List<Obstacle> obstacleRocks;
	
	
	public int getLooseGainPoints() {
		return looseGainPoints;
	}
	public void setLooseGainPoints(int looseGainPoints) {
		this.looseGainPoints = looseGainPoints;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public int getWalls() {
		return walls;
	}
	public void setWalls(int walls) {
		this.walls = walls;
	}
	public int getRocks() {
		return rocks;
	}
	public void setRocks(int rocks) {
		this.rocks = rocks;
	}
	public int getFieldWidth() {
		return fieldWidth;
	}
	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}
	public int getFieldHeight() {
		return fieldHeight;
	}
	public void setFieldHeight(int fieldHeight) {
		this.fieldHeight = fieldHeight;
	}
	public List<Obstacle> getObstacleWalls() {
		return obstacleWalls;
	}
	public void setObstacleWalls(List<Obstacle> obstacleWalls) {
		this.obstacleWalls = obstacleWalls;
	}
	public List<Obstacle> getObstacleRocks() {
		return obstacleRocks;
	}
	public void setObstacleRocks(List<Obstacle> obstacleRocks) {
		this.obstacleRocks = obstacleRocks;
	}
	public String toString() {
		return "For your chosen Level you will have "+ this.getPoints() + " points and your stamina will be " + this.getStamina() + " points";
	}
	
	public List<Obstacle> buildRocks() {
		List<Obstacle> obstacles = new ArrayList<>();
		int noOfPositionsOfRock = this.getFieldWidth()/10;
		for(int i=0;i<this.getRocks();i++) {
			obstacles.add(generateObstacle(noOfPositionsOfRock));
		}
		setObstacleRocks(obstacles);
		return obstacles;
	}
	public List<Obstacle> buildWalls() {
		List<Obstacle> obstacles = new ArrayList<>();
		int noOfPositionsOfWall = this.getFieldWidth()/5;
		for(int i=0;i<this.getRocks();i++) {
			obstacles.add(generateObstacle(noOfPositionsOfWall));
		}
		setObstacleWalls(obstacles);
		return obstacles;
	}
	
	public Obstacle generateObstacle(int noOfPositionsOfObstacle) {
		Position startPosition = generateStartPosition();
		Position endPosition = generateEndPositionForObstacle(noOfPositionsOfObstacle,startPosition);
		return new Obstacle(startPosition,endPosition);
	}
	
		
	public Position generateStartPosition() {
		return new Position((int)(Math.random()*(this.getFieldWidth()-1)), (int)(Math.random()*(this.getFieldHeight()-1)));
	}
	
	public Position generateEndPositionForObstacle(int noOfPositionsOfObstacle,Position startPosition) {
		return new Position(startPosition.getX()+noOfPositionsOfObstacle,startPosition.getY());
	}
	
}
