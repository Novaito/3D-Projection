package shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Face {
	private Dot[] dotList;
	private double[] xDots;
	private double[] yDots;
	private int size;
	
	public Face(Dot[] dl) {
		dotList = dl;
		size = dl.length;
		xDots = new double[size];
		yDots = new double[size];
		int i = 0;
		for (Dot d : dotList) {
			xDots[i] = d.getX();
			yDots[i] = d.getY();
			i++;
		}
	}
	
	public Face() {}
	
	public void addDot(Dot d) {
		Dot[] oldList = dotList;  
	    int len = (oldList != null) ? oldList.length : 0;
	    
	    Dot[] newList = new Dot[len + 1];
		double[] newXDots = new double[len+1];
		double[] newYDots = new double[len+1];
		
		for (int i=0; i<len; i++) {
			newList[i] = dotList[i];
			newXDots[i] = dotList[i].getX();
			newYDots[i] = dotList[i].getY();
		}
		newList[size] = d;
		newXDots[size] = d.getX();
		newYDots[size] = d.getY();
		dotList = newList;
		xDots = newXDots;
		yDots = newYDots;
		
		size++;
	}
	
	public void remDot(int i) {
		Dot[] newList = new Dot[size-1];
		xDots = new double[size-1];
		yDots = new double[size-1];
		
		for (int j=0; j < size-1; j++) {
			if (j < i) {
				newList[j] = dotList[j];
			} else {
				newList[j] = dotList[j+1];
			}
		}
		
		dotList = newList;
		size--;
	}
	
	public String toString() {
		if (dotList != null) {
			String s = "";
			int i = 0;
			for (Dot d : dotList) {
				s += "[" + d.getX() + ", " + d.getY() + "] ";
				if (i%5 == 0) s += "\n";
			}
			return s;			
		} else return "Empty face";
	}
	
	public void draw(GraphicsContext gc, String color) {
		if (size > 0) {
			for(int i=0; i < size; i++) {
				xDots[i] = dotList[i].getX();
				yDots[i] = dotList[i].getY();
			}
			
			gc.setFill(Color.web(color));
			gc.fillPolygon(xDots, yDots, size);			
		}
	}
}
