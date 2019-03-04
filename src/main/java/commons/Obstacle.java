package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Obstacle implements Serializable{
	
	Position startCoardinate;
	Position endCoardinate;
	List<Position> occupiedCoardinates = new ArrayList<>();
	public Obstacle() {
		// TODO Auto-generated constructor stub
	}
	public Obstacle(Position startCoardinate,Position endCoardinate){
		this.startCoardinate = startCoardinate;
		this.endCoardinate = endCoardinate;
		setOccupiedCoardinates();
	}
	public Position getStartCoardinate() {
		return startCoardinate;
	}
	public void setStartCoardinate(Position startCoardinate) {
		this.startCoardinate = startCoardinate;
	}
	public Position getEndCoardinate() {
		return endCoardinate;
	}
	public void setEndCoardinate(Position endCoardinate) {
		this.endCoardinate = endCoardinate;
	}
	
	public List<Position> getOccupiedCoardinates() {
		return occupiedCoardinates;
	}
	public void setOccupiedCoardinates(List<Position> occupiedCoardinates) {
		this.occupiedCoardinates = occupiedCoardinates;
	}
	
	public void setOccupiedCoardinates() {
		
		for(int i=startCoardinate.getX();i< endCoardinate.getX();i++) {
			
			for(int j=startCoardinate.getY();j<= endCoardinate.getY();j++) {
				Position position=new Position(i,j);
				occupiedCoardinates.add(position);
				
			}
			
		}
	}
	
	

}
