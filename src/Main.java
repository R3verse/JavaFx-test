/**
 * Created by Maxjensendk on 27-02-2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage window;
    private Scene scene1;
    private Scene scene2;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label label1 = new Label("Welcome to first scene");
        Button btn1 = new Button("Go to scene 2");
        btn1.setOnAction(e -> window.setScene(scene2));

        // layout 1 - vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, btn1);
        scene1 = new Scene(layout1, 350, 500);

        Button btn2 = new Button("GO back to scene 1");
        btn2.setOnAction(e -> window.setScene(scene1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(btn2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("Title of scene");
        window.show();
    }

}
