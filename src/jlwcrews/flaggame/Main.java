package jlwcrews.flaggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
Server for the Flag Game
Programmer: Jason Williams
Final version: 1.0
Notes: This is the server for a game where you are shown flags and asked to pick the country.  This server
listens on port 9898, and responds only to "easy", "medium", and "hard".  Once the client has passed the
difficulty request, the server does a sql query against a local sqlite database, and returns an ArrayList of
Flag objects, based on the difficulty selected.  The server then closes the connection and continues waiting.
*/

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //just puts up the javafx scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("flaggameserver.fxml"));
        Parent root = loader.load();
        FlagGameServerController controller = loader.getController();
        primaryStage.setTitle("Flag Game Server");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


}
