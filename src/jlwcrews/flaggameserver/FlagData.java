package jlwcrews.flaggameserver;

import java.sql.*;
import java.util.ArrayList;

public class FlagData{

    private ArrayList<Flag> flags;

    private Connection dbConnect() {
        String url = "jdbc:sqlite:flags.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //population the flags array with the appropriate flags based on the selected game mode
    public ArrayList<Flag> returnFlags(String gameMode) {
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
            flags = new ArrayList<>();
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
        return flags;
    }
}
