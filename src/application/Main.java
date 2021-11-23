package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import static application.SimParams.*;

import java.util.ArrayList;

public class Main extends Application {
	static Game game;
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root, screenW, screenH);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			game = new Game(root);
			
			
			AnimationTimer timer;
			timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					onUpdate();
				}
			};
			timer.start();
			scene.setOnKeyPressed(e ->{
				switch(e.getCode()){
				case W:
					game.updateInput(0,true);
					break;
				case A:
					game.updateInput(2,true);
					break;
				case S:
					game.updateInput(1,true);
					break;
				case D:
					game.updateInput(3,true);
					break;
				}
			});
			scene.setOnKeyReleased(e ->{
				switch(e.getCode()){
				case W:
					game.updateInput(0,false);
					break;
				case A:
					game.updateInput(2,false);
					break;
				case S:
					game.updateInput(1,false);
					break;
				case D:
					game.updateInput(3,false);
					break;
				}
			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void onUpdate() {
		game.update();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
