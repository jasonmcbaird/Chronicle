package utilities;

import enums.Direction;

public class Position extends Pair<Object, Object> {

	public Position(int x, int y) {
		super(x, y);
	}
	
	public int getX(){
    	return (Integer) l;
    }
    
    public int getY(){
    	return (Integer) r;
    }
    
    public void setX(int i){
    	l = i;
		if((Integer) l < 0) {
			l = 0;
		}
    }
    
    public void setY(int i){
    	this.r = i;
		if((Integer) r < 0) {
			r = 0;
		}
    }
    
    public boolean equalsPosition(Position position) {
    	if(getX() == position.getX() && getY() == position.getY()) {
    		return true;
    	}
    	return false;
    }
    
    public void move(Direction direction, int i) {
    	switch (direction) {
    	case UP:
    		setY(getY() - 1);
    		return;
    	case DOWN:
    		setY(getY() + 1);
    		return;
    	case LEFT:
    		setX(getX() - 1);
    		return;
    	case RIGHT:
    		setX(getX() + 1);
    		return;
    	}
    }
    
    public void move(Direction direction) {
    	move(direction, 1);
    }
	
}
