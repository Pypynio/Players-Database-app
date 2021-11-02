import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerParser {

    public Player createPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        String playerId = null;
        String playerNick = null;

        while (resultSet.next()) {
            playerId = resultSet.getString("Id");
            playerNick = resultSet.getString("Nick");
        }
        return new Player(playerId, playerNick);
    }

    public List<Player> createPlayersListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Player> players = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String nick = resultSet.getString("nick");
            Player player = new Player(id,nick);
            players.add(player);
        }
        return players;
    }
}
