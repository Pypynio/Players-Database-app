import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            PropertiesLoader.readProperties();
            PlayerDAOimpl playerDAOimpl = new PlayerDAOimpl();
/*            ObjectMapper objectMapper = new ObjectMapper();
            Player playerFromJson = objectMapper.readValue(new File(PropertiesLoader.getUrlToJson()), Player.class);
            playerDAOimpl.insertPlayer(playerFromJson);*/

            // Player player = playerDAOimpl.selectPlayerByNick("Marcin");
            //player.SayMyName();
            // playerDAOimpl.deletePlayerByNick("MatthewPotap");
            // playerDAOimpl.deletePlayerById("6");
            playerDAOimpl.selectAllPlayers();
            playerDAOimpl.countPlayers();
            playerDAOimpl.countSomething(QueryBuilder.displayNumberOfNoobs("2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}