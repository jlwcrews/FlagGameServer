package jlwcrews.flaggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("flaggameserver.fxml"));
        Parent root = loader.load();
        FlagGameServerController controller = loader.getController();
        primaryStage.setTitle("Flag Game Server");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


}
