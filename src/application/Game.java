package application;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class Game {
	boolean[] inputs = { false, false, false, false };// up down left right
	Pane root;
	Player me;
	ArrayList<Platform> platforms = new ArrayList<>();

	public Game(Pane root) {
		this.root = root;
		me = new Player(root);
		
		for (int i = 0; i < 10; i++) {
			for (int p = 0; p < 10; p++) {
				Platform q = new Platform(root, 200 + 100 * i+200*p, 530 - 80 * i, 80, 10);
				platforms.add(q);
			}
		}

	}

	public void update() {
		for (Platform p : platforms) {
			p.update();
		}
		me.update(platforms);
	}

	public void updateInput(int index, boolean newState) {
		me.updateInput(index, newState);
	}

}
