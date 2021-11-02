import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class PlayerDAOimpl implements PlayerDAO {

    private PlayerParser playerParser;
    private Connection connection;

    public PlayerDAOimpl() {
        setupConnection();
        this.playerParser = new PlayerParser();
    }

    @Override
    public void insertPlayer(Player playerToInsert) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.getInsert());
        preparedStatement.setString(1, playerToInsert.getID());
        preparedStatement.setString(2, playerToInsert.getNick());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Player> selectAllPlayers() throws SQLException {
        String query = QueryBuilder.getSelect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Player> players = playerParser.createPlayersListFromResultSet(resultSet);
        return players;
    }

    @Override
    public void deletePlayer() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(QueryBuilder.getDeleteQuery("Pietrek Kogucik"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Player selectPlayerByNick(String nick) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryBuilder.getSelectByNick(nick));
        return playerParser.createPlayerFromResultSet(resultSet);
    }

    private void setupConnection() {
        try {
            String databaseUrl = PropertiesLoader.getDataBaseUrl();
            this.connection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
