import java.io.IOException;
import java.sql.*;
import java.util.List;

public final class PlayerDAOimpl implements PlayerDAO {

    private PlayerParser playerParser;
    private Connection connection;

    public PlayerDAOimpl() {
        setupConnection();
        this.playerParser = new PlayerParser();
    }

    @Override
    public void insertPlayer(Player playerToInsert) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.getInsert());
        preparedStatement.setString(1, playerToInsert.getID());
        preparedStatement.setString(2, playerToInsert.getNick());
        preparedStatement.setInt(3,playerToInsert.getLevel());
        preparedStatement.setInt(4,playerToInsert.getTeamId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Player> selectAllPlayers() throws SQLException {
        String query = QueryBuilder.getSelect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Player> players = playerParser.createPlayersListFromResultSet(resultSet);
        for (Player player: players) {
            player.SayMyName();
            System.out.println("~~~~~~");
        }
        return players;
    }

    @Override
    public void deletePlayerByNick(String nick) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(QueryBuilder.getDeleteByNickQuery(nick));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletePlayerById(String id){
        try {
            String query = QueryBuilder.getDeleteByIdQuery(id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
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
