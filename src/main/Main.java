package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import shape.*;
import forms.Cube;



public class Main extends Application {
	
	private final Screen SCREEN = new Screen(800, 800);
	
	public void start(Stage stage) {
		Canvas canvas = new Canvas(SCREEN.getWidth(), SCREEN.getHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Cube cube = new Cube(
			new Dot(0.20f, 0.20f, 0.20f),
			new Dot(-0.20f, 0.20f, 0.20f),
			new Dot(0.20f, -0.20f, 0.20f),
			new Dot(-0.20f, -0.20f, 0.20f)
		);
		
		
		AnimationTimer loop = new AnimationTimer() {
			long lastTime;
			double angle = 0.0f;
			@Override
			public void handle(long now) {
				if (lastTime == 0) {
					lastTime = now;
					return;
				}
				
				double dt = (now - lastTime) * 1e-9; // Conversion nanosec -> sec
				lastTime = now;
				angle = 4*Math.PI/9*dt;
				
				gc.clearRect(0, 0, SCREEN.getWidth(), SCREEN.getHeight());
				
				for (Dot d : cube.getDots()) {
					d.yAxisRotate(angle, 0.0f, 0.0f);
					d.zAxisRotate(angle, 0.0f, 0.0f);
					d.update(SCREEN, 0.0f * dt);
				}
				cube.getShape().drawFaces(gc, "#3f4a87");
				cube.getShape().draw(gc);
				cube.getShape().drawNeon(gc);
			}
		};
		
		loop.start();
		
		Scene scene = new Scene(new Pane(canvas), Color.web("#222"));
		stage.setTitle("3D Shape Projection");
		stage.setScene(scene);
		stage.show();	

	}
	
	public static void main(String[] args) {
		launch();
	}
}