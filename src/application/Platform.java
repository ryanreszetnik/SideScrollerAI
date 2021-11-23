package application;

import static application.SimParams.floorHeight;
import static application.SimParams.gravity;
import static application.SimParams.playerXspeed;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Platform {
	Rectangle hitbox;
	double x, y, w, h, xVel;

	public Platform(Pane root, int x, int y, int w, int h) {
		hitbox = new Rectangle(0, y, w, h);
		hitbox.setStyle("-fx-fill: gray; -fx-stroke: black; -fx-stroke-width: 2;");
		hitbox.setTranslateX(x);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.xVel = 0;
		root.getChildren().add(hitbox);
	}

	public void update() {
		x += xVel;
		hitbox.setTranslateX(x);
	}

	public double getLeftX() {
		return x;
	}

	public double getRightX() {
		return x + w;
	}

	public double getTopY() {
		return y;
	}

	public double getBottomY() {
		return y + h;
	}
}
