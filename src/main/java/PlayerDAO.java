import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {




    void insertPlayer() throws SQLException, IOException;

    List<Player> selectAllPlayers() throws SQLException;

    void deletePlayerByNick();

    Player selectPlayerByNick() throws SQLException;
}
