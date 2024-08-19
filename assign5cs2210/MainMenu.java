import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("INTERCHANGE");

        // Create buttons
        Button btnStartGame = new Button("START GAME");
        Button btnHighScores = new Button("HIGH SCORES");
        Button btnCredits = new Button("CREDITS");
        Button btnInstructions = new Button("INSTRUCTIONS");

        // Create layout and add buttons
        VBox vbox = new VBox(10); // 10 is the spacing between elements in the VBox
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(btnStartGame, btnHighScores, btnCredits, btnInstructions);

        // Add a title
        Text title = new Text("INTERCHANGE");
        title.setStyle("-fx-font: 24 arial;");

        // StackPane to allow overlay of title and vbox
        StackPane root = new StackPane();
        root.getChildren().add(title);
        root.getChildren().add(vbox);
        StackPane.setAlignment(title, Pos.TOP_CENTER);

        Scene scene = new Scene(root, 800, 600); // Set the size of the scene here

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
