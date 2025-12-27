package forms;

import shape.*;
import java.util.ArrayList;
import java.util.List;

public class Cube {
	private List<Dot> dots = new ArrayList<>();
	private Shape s;
	
	public Cube(Dot d1, Dot d2, Dot d3, Dot d4) {
		dots.add(d1);
		dots.add(d2);
		dots.add(d3);
		dots.add(d4);
		dots.add(new Dot(d1.getX(), d1.getY(), d1.getZ() - Math.abs(d1.getX() - d2.getX()) ));
		dots.add(new Dot(d2.getX(), d2.getY(), d2.getZ() - Math.abs(d1.getX() - d2.getX()) ));
		dots.add(new Dot(d3.getX(), d3.getY(), d3.getZ() - Math.abs(d1.getX() - d2.getX()) ));
		dots.add(new Dot(d4.getX(), d4.getY(), d4.getZ() - Math.abs(d1.getX() - d2.getX()) ));
		
		s = new Shape(dots);
		addVertice();
		addFace();
	}
	
	public List<Dot> getDots() {
		return dots;
	}
	
	public Shape getShape() {
		return s;
	}
	
	private void addVertice() {
		s.addVertice(0, dots.get(1));
		s.addVertice(0, dots.get(2));
		s.addVertice(1, dots.get(3));
		s.addVertice(2, dots.get(3));
		s.addVertice(3, dots.get(7));
		s.addVertice(0, dots.get(4));
		s.addVertice(1, dots.get(5));
		s.addVertice(2, dots.get(6));
		s.addVertice(4, dots.get(5));
		s.addVertice(4, dots.get(6));
		s.addVertice(5, dots.get(7));
		s.addVertice(6, dots.get(7));
	}
	
	private void addFace() {
		Face f1 = new Face();
		Face f2 = new Face();
		Face f3 = new Face();
		Face f4 = new Face();
		Face f5 = new Face();
		Face f6 = new Face();
		
		f1.addDot(dots.get(0));
		f1.addDot(dots.get(1));
		f1.addDot(dots.get(3));
		f1.addDot(dots.get(2));
		
		f2.addDot(dots.get(0));
		f2.addDot(dots.get(1));
		f2.addDot(dots.get(5));
		f2.addDot(dots.get(4));
		
		f3.addDot(dots.get(1));
		f3.addDot(dots.get(3));
		f3.addDot(dots.get(7));
		f3.addDot(dots.get(5));
		
		f4.addDot(dots.get(0));
		f4.addDot(dots.get(2));
		f4.addDot(dots.get(6));
		f4.addDot(dots.get(4));
		
		f5.addDot(dots.get(4));
		f5.addDot(dots.get(5));
		f5.addDot(dots.get(7));
		f5.addDot(dots.get(6));
		
		f6.addDot(dots.get(4));
		f6.addDot(dots.get(6));
		f6.addDot(dots.get(2));
		f6.addDot(dots.get(3));
		
		s.addFace(f1);
		s.addFace(f2);
		s.addFace(f3);
		s.addFace(f4);
		s.addFace(f5);
		s.addFace(f6);
	}
}
