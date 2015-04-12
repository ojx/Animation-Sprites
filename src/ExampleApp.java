import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sprite.AnimationPane;

public class ExampleApp extends Application {
    private AnimationPane animationPane;
    private Lucas lucas;

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("Sprite Example Application");
        stage.setWidth(600);
        stage.setHeight(350);

        animationPane = new AnimationPane();
        Scene scene = new Scene(animationPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        animationPane.setBackgroundImage("fence.jpg");

        lucas = new Lucas();

        lucas.setLayoutX(animationPane.getWidth() / 2);
        lucas.setLayoutY(animationPane.getHeight() - 50);

        lucas.stand();

        animationPane.setOnKeyPressed(event -> keyPressed(event));
        animationPane.requestFocus();
        animationPane.add(lucas);
    }


    private void keyPressed(KeyEvent event) {
        //System.out.println(keyEvent.getCode().getName());
        if (event.getCode().getName().equals("Right")) {
            lucas.runRight();
        } else if (event.getCode().getName().equals("Up")) {
            lucas.jump();
        } else if (event.getCode().getName().equals("Left")) {
            lucas.runLeft();
        } else {
            lucas.stand();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}