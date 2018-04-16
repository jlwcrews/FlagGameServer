package jlwcrews.flaggameserver;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FlagGameServerController implements Initializable{

    @FXML
    public TextArea messageTextArea;

    @FXML
    private Pane serverStatusPane;

    @FXML
    private ListView<?> userListView;

    private ArrayList<Flag> flags;

    public static FlagGameServerController fgsc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fgsc = this;
        //run the server in a new thread, and then continue on to display the scene
        Thread serverThread = new Thread(new FlagGameServer(fgsc));
        serverThread.start();
    }

    public void showMessage(String message) {
        messageTextArea.appendText(message + "\n");

    }
}
