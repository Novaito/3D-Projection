package shape;

import java.util.ArrayList;
import java.util.List;

public class Shape {
	private int x;
	private int y;
	
	private List<Dot> dotList = new ArrayList<>();
	
	
	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void addDot(Dot d) {
		dotList.add(d);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
