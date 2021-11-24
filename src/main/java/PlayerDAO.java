import com.fasterxml.jackson.databind.ObjectMapper;
import queries.EntityType;
import queries.QueryBuilder;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class PlayerDAO implements DataAccessObject<Player> {
    private final PlayerParser playerParser = new PlayerParser();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final QueryBuilder queryBuilder = new QueryBuilder();
    private Connection connection;

    public PlayerDAO() {
        setupConnection();
    }

    @Override
    public List<Player> selectAll() throws SQLException {
        String query = queryBuilder.buildSelectAllQuery(EntityType.PLAYERS);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Player> players = playerParser.createPlayersListFromResultSet(resultSet);
        for (Player player : players) {
            player.SayMyName();
            System.out.println("~~~~~~");
        }
        return players;
    }

    @Override
    public Player selectById(String id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryBuilder.buildSelectByIdQuery(EntityType.PLAYERS,id));
        Player player = playerParser.createPlayerFromResultSet(resultSet);
        player.SayMyName();
        return player;

    }

    @Override
    public void insert(Player genericObject) {
        try {
            Player player = objectMapper.readValue(new File(PropertiesLoader.getUrlToJson()), Player.class);
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.buildGenericInsertQuery(EntityType.PLAYERS));
            preparedStatement.setInt(1, findNewID());
            preparedStatement.setString(2, player.getNick());
            preparedStatement.setInt(3, player.getLevel());
            preparedStatement.setInt(4, player.getTeamId());
            preparedStatement.executeUpdate();
            System.out.println("Player added to DB");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Player genericObject, String id) {
        try {
            Statement statement = connection.createStatement();
            String query = queryBuilder.buildDeleteQuery(EntityType.PLAYERS,id);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Player genericObject, String field, String newValue, String idValue) {
        try {
            Statement statement = connection.createStatement();
            String query = queryBuilder.buildUpdateQuery(EntityType.PLAYERS, field,newValue,idValue);
            statement.executeUpdate(query);
            System.out.println("Player update done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer findNewID() throws SQLException {
        int newID = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryBuilder.GenericMaxId(EntityType.PLAYERS));
        while (resultSet.next()) {
            newID = resultSet.getInt(1) + 1;
        }
        return newID;
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
