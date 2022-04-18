import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public  void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindowView.fxml"));
        try{
            AnchorPane pane = loader.load();
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            Scene scene = new Scene(pane);
            MainWindowColntroller mainWindowController = loader.getController();
            mainWindowController.setPrimaryStage(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
