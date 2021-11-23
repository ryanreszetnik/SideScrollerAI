package application;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static application.SimParams.*;

import java.util.ArrayList;

public class Player {
	public boolean canJump = true;
	Rectangle hitbox = new Rectangle(playerWidth, playerHeight);
	boolean[] gameInputs = { false, false, false, false };// up down left right
	public double xVel = 0;
	public double yVel = -5;
	public double xPos = startingX;
	public double yPos = startingY;

	public Player(Pane root) {

		hitbox.setTranslateX(xPos);
		hitbox.setTranslateY(yPos);
		root.getChildren().add(hitbox);
	}

	public void update(ArrayList<Platform> platforms) {
		if (gameInputs[2] && !gameInputs[3]) {
			xVel = -playerXspeed;
		} else if (gameInputs[3] && !gameInputs[2]) {
			xVel = playerXspeed;
		} else {
			xVel = 0;
		}
		if (gameInputs[0]) {
			jump();
		}
		yVel += gravity;
//		xPos += xVel;
		yPos += yVel;
		checkCollisions(platforms);
		for(Platform p:platforms){
			p.xVel=-xVel;
		}
		if (yPos >= floorHeight) {
			yVel = 0;
			canJump = true;
			yPos = floorHeight;
		}
		hitbox.setTranslateX(xPos);
		hitbox.setTranslateY(yPos);
	}

	private void checkCollisions(ArrayList<Platform> platforms) {
		double pY = yPos - yVel;
		double pX = xPos - xVel;
		for (Platform platform : platforms) {
			if (platform.getTopY() <= yPos + playerHeight && platform.getTopY() >= pY + playerHeight) {
				// collide with top surface going down
				if (withinX(platform)) {
					yPos = platform.getTopY() - playerHeight;
					yVel=0;
					canJump=true;
				}
			}
			if (platform.getBottomY() >= yPos && platform.getBottomY() <= pY) {
				// collide with bottom surface going up
				if (withinX(platform)) {
					yPos = platform.getBottomY();
					yVel=0;
				}
			}
			if (platform.getRightX() >= xPos && platform.getRightX() <= pX) {
				// collide with right surface going left
				if (withinY(platform)) {
//					xPos = platform.getRightX();
					xVel=0;
				}
			}
			if (platform.getLeftX() <= xPos + playerWidth && platform.getLeftX() >= pX + playerWidth) {
				// collide with left surface going right
				if (withinY(platform)) {
//					xPos = platform.getLeftX() - playerWidth;
					xVel=0;
				}
			}
		}
	}

	private boolean withinX(Platform p) {
		return xPos < p.getRightX() && xPos + playerWidth > p.getLeftX();
	}

	private boolean withinY(Platform p) {
		return yPos < p.getBottomY() && yPos + playerHeight > p.getTopY();
	}

	public void jump() {
		if (canJump) {
			canJump = false;
			yVel = playerJumpspeed;
		}
	}

	public void updateInput(int index, boolean newState) {
		gameInputs[index] = newState;
	}
}
