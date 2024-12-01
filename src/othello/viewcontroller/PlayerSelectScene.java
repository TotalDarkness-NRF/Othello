package othello.viewcontroller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PlayerSelectScene extends Scene {

    public PlayerSelectScene(Stage stage) {
        super(new StackPane());
        createScene(stage);
    }

    private void createScene(Stage stage) {
        // Create the second scene
        Button btn2 = new Button("Home");
        StackPane root2 = new StackPane();
        root2.getChildren().add(btn2);
        Scene scene2 = new Scene(root2, 300, 200);
        Scene oldScene = stage.getScene().getRoot().getScene();

        // Action for the second button to go back to the first scene
        btn2.setOnAction(e -> stage.setScene(oldScene));

        // Switch to the new scene
        stage.setScene(scene2);
    }
    // TODO create a player gui that allows selecting player 1 and 2 of human, greedy, or random
}