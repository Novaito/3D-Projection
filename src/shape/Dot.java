package shape;

import main.Screen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class Dot {
	public double x;
	public double y;
	public double z;
	
	private double xp;
	private double yp;
	
	public Dot(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
		xp = x;
		yp = y;
	}
	
	public Rectangle getDotToSquare(int width) {
		return new Rectangle(x - width/2, y - width/2, width, width);
	}
	
	public Circle getDotToCircle(int radius) {
		return new Circle(x, y, radius);
	}
	
	public void fromCenter(int w, int h) {
		xp = (xp + 1) / 2 * w;
		yp = (1 - (yp + 1) / 2) * h;
	}
	
	public void projectDot() {
		double focal = 1.0;
		xp = (x/(z + focal)) * focal;
		yp = (y/(z + focal)) * focal;
	}
	
	public void zTranslate(double dt) {
		setZ(getZ() + dt);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.DARKSEAGREEN);
		gc.fillRect(getX() - 10, getY() - 10, 20, 20);
	}
	
	public void update(Screen s, double dt) {
		zTranslate(dt);
		projectDot(); // Project Z axis on x-y axis
		fromCenter(s.getWidth(), s.getHeight()); // Udpate points from origin
	}
	
	public void zRotate(double angle, double cx, double cz) {
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		double prevX = x - cx;
		double prevZ = z - cz;
		x = prevX * c - prevZ * s + cx; // Relative position of center of x
		z = prevX * s + prevZ * c + cz; // Relative position of center of z
	}
	
	public double getX() {
		return xp;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return yp;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
}
