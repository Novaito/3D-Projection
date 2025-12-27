package main;

import shape.Dot;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.ArrayList;


public class Main extends Application {
	
	private final Screen SCREEN = new Screen(800, 800);
	
	private List<Dot> dotList = new ArrayList<>();
	
	
	public void start(Stage stage) {
		Canvas canvas = new Canvas(SCREEN.getWidth(), SCREEN.getHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		dotList.add(new Dot(0.25f, 0.25f, 0.25f));
		dotList.add(new Dot(-0.25f, 0.25f, 0.25f));
		dotList.add(new Dot(0.25f, -0.25f, 0.25f));
		dotList.add(new Dot(-0.25f, -0.25f, 0.25f));
		dotList.add(new Dot(0.25f, 0.25f, -0.25f));
		dotList.add(new Dot(-0.25f, 0.25f, -0.25f));
		dotList.add(new Dot(0.25f, -0.25f, -0.25f));
		dotList.add(new Dot(-0.25f, -0.25f, -0.25f));
		
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
				angle = Math.PI*dt;
				
				gc.clearRect(0, 0, SCREEN.getWidth(), SCREEN.getHeight());
				
				for (Dot d : dotList) {
					d.zRotate(angle, 0.0f, 0.0f);
					d.update(SCREEN, 0.25f * dt);
				}
				for (Dot d : dotList) {
					d.draw(gc);
				}
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