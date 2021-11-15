import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerParser {

    public Player createPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        String playerId = null;
        String playerNick = null;
        String playerTeam = null;
        Integer playerLevel = null;
        Integer teamId = null;

        while (resultSet.next()) {
            playerId = resultSet.getString("Id");
            playerNick = resultSet.getString("Nick");
            playerTeam = resultSet.getString("team_name");
            playerLevel = resultSet.getInt("level");
            //teamId = resultSet.getInt("team_id");
        }
        return new Player(playerId, playerNick,playerTeam, playerLevel);
    }

    public List<Player> createPlayersListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Player> players = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String nick = resultSet.getString("nick");
            String team = resultSet.getString("team_name");
            int level = resultSet.getInt("level");
           // int teamId = resultSet.getInt("team_id");
            Player player = new Player(id,nick,team,level);
            players.add(player);
        }
        return players;
    }
}
