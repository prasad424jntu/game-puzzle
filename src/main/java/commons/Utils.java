package commons;

public class Utils {
	
	public static Level convertInputLevelToEnum(String level) {
		
		switch (level) {
		case "B":
			return Level.BEGINNER;
		case "M":
			return Level.MEDIUM;
		case "H":
			return Level.HARD;
		default:
			return Level.BEGINNER;
		}
	}
	
	public static Action convertInputActionToEnum(String action) {
		
		switch (action) {
		case "J":
			return Action.J;
		case "K":
			return Action.K;
		case "L":
			return Action.L;
		case "R":
			return Action.R;
		case "U":
			return Action.U;
		case "D":
			return Action.D;
		case "F":
			return Action.F;
		case "B":
			return Action.B;
		case "E":
			return Action.E;
		case "S":
			return Action.S;
		case "Q":
			return Action.Q;
		case "C":
			return Action.C;
		
		default:
			return Action.J;
		}
		
	}
	
	public static Position nextPosition(Action action,Position currentPosition) {
		Position nextPosition = new Position(currentPosition.getX(),currentPosition.getY());
		switch (action) {
		case J:
			return currentPosition;
		case K:
			return currentPosition;
		case L:
			nextPosition.setX(currentPosition.getX()-1);
			return nextPosition;
		case R:
			nextPosition.setX(currentPosition.getX()+1);
			return nextPosition;
		case U:
			nextPosition.setY(currentPosition.getY()-1);
			return nextPosition;
		case D:
			nextPosition.setY(currentPosition.getY()+1);
			return nextPosition;
		case F:
			return currentPosition;
		case B:
			return currentPosition;
		case E:
			return currentPosition;
		
		default:
			return currentPosition;
		}
	}

}
