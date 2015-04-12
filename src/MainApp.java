import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sprite.AnimationPane;

public class MainApp extends Application {
    private AnimationPane animationPane;

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("Sprite Application");
        stage.setWidth(600);
        stage.setHeight(400);

        animationPane = new AnimationPane();
        Scene scene = new Scene(animationPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        animationPane.setOnKeyPressed(event -> keyPressed(event));
        animationPane.requestFocus();




    }


    private void keyPressed(KeyEvent event) {
        System.out.println(event.getCode().getName());



    }

    public static void main(String[] args) {
        launch(args);
    }
}