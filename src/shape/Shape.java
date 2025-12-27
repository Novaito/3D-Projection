package shape;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Shape {
	
	private List<Dot> dotList = new ArrayList<>();
	private Dot[][] neighboursVertice; // Array of connected vertice from [nth dot] to [nth dot][another dot]
	private Face[] faces;
	
	
	public Shape(List<Dot> dl) {
		for (Dot d : dl) {
			addDot(d);
		}
		neighboursVertice = new Dot[dl.size()][];
	}
	
	public void addDot(Dot d) {
		dotList.add(d);
	}
	
	public int getSize(int i) {
		return ((neighboursVertice == null) || (neighboursVertice[i] == null))?0:neighboursVertice[i].length;
	}
	
	public Dot[] getListNthDot(int i) {
		return neighboursVertice[i];
	}
	
	public void addVertice(int i, Dot d) {
	    Dot[] prevList = getListNthDot(i);  
	    int len = (prevList != null) ? prevList.length : 0;
	    Dot[] newList = new Dot[len + 1];
	    for (int k = 0; k < len; k++) {
	        newList[k] = prevList[k];
	    }

	    newList[len] = d;

	    neighboursVertice[i] = newList;
	}
	
	public void addFace(Face f) {
		Face[] prevList = faces;
		int size = (prevList != null) ? prevList.length : 0;
		Face[] newList = new Face[size+1];
		for (int k=0; k < size; k++) {
			newList[k] = prevList[k];
		}
		newList[size] = f;
		faces = newList;
	}
	
	public void printVert() {
		for (int i = 0; i<neighboursVertice.length; i++) {
			System.out.print(i + " : ");
			if (getSize(i) > 0) {
				for (Dot d: neighboursVertice[i]) {
					System.out.print("[" + d.getX() + ", " + d.getY() + "] ");
				}				
			}
			System.out.println();
		}
	}

	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.web("#87d4f2"));
		gc.setLineWidth(2);
		for (int i = 0; i < neighboursVertice.length; i++) {
			if (getSize(i) > 0 ) {
				for (Dot d: neighboursVertice[i]) {
					gc.strokeLine(dotList.get(i).getX(), dotList.get(i).getY(), d.getX(), d.getY());
				}
			}
		}
	}
	
	public void drawNeon(GraphicsContext gc) {
		gc.setStroke(Color.web("#87d4f260"));
		gc.setLineWidth(7);
		for (int i = 0; i < neighboursVertice.length; i++) {
			if (getSize(i) > 0 ) {
				for (Dot d: neighboursVertice[i]) {
					gc.strokeLine(dotList.get(i).getX(), dotList.get(i).getY(), d.getX(), d.getY());
				}
			}
		}
	}
	
	public void drawFaces(GraphicsContext gc, String color) {
		if (faces != null) {
			for (Face f: faces) {
				f.draw(gc, color);
			}			
		}
	}
}
