package jlwcrews.flaggameserver;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.*;
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

    //set up the database connection
    private Connection dbConnect() {
        String url = "jdbc:sqlite:flags.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //population the flags array with the appropriate flags based on the selected game mode
    public void returnFlags(String gameMode) {
        String sql = "select country, image from flags f " +
                "join flag_groups fg on f.flag_id=fg.flag_id " +
                "join groups g on g.group_id=fg.group_id ";
        switch (gameMode) {
            case "easy":
                sql = sql + "where g.group_id=2;";
                break;
            case "medium":
                sql = sql + "where g.group_id=3;";
                break;
            case "hard":
                sql = sql + "where g.group_id=1;";
                break;
        }

        try (Connection conn = this.dbConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                Flag flag = new Flag();
                flag.setCountry(rs.getString("country"));
                flag.setFlag(rs.getString("image"));
                flags.add(flag);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void showMessage(String message) {
        messageTextArea.appendText(message + "\n");

    }
}
