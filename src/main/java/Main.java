import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            PropertiesLoader.readProperties();
            PlayerDAOimpl playerDAOimpl = new PlayerDAOimpl();
            ObjectMapper objectMapper = new ObjectMapper();

/*
            Player playerFromJson = objectMapper.readValue(new File(PropertiesLoader.getUrlToJson()), Player.class);
            playerDAOimpl.insertPlayer(playerFromJson);

           Player player = playerDAOimpl.selectPlayerByNick("Jaca");
           player.SayMyName();*/

           // playerDAOimpl.deletePlayerByNick("Bialas");
          //  playerDAOimpl.deletePlayerById("114");
            playerDAOimpl.selectAllPlayers();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}