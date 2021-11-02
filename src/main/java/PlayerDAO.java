import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {

    void insertPlayer(Player player) throws SQLException, IOException;

    void deletePlayer();

    Player selectPlayerByNick(String nick) throws SQLException;

    List<Player> selectAllPlayers() throws SQLException;

}
