import com.fasterxml.jackson.databind.ObjectMapper;
import queries.EntityType;
import queries.QueryBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PlayerDAO implements DataAccessObject<Player> {
    private final PlayerParser playerParser = new PlayerParser();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final QueryBuilder queryBuilder = new QueryBuilder();
    private Connection connection;
    Scanner scanner = new Scanner(System.in);

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
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.buildInsertPlayerQuery(EntityType.PLAYERS));
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

    public String chooseNick() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> nicki = mapper.readValue(new File(PropertiesLoader.getUrlToNickList()), List.class);
        Random r = new Random();
        int randomNumber = r.nextInt(nicki.size());
        String randomNick = nicki.get(randomNumber);
        System.out.println(randomNick);
        return randomNick;
    }

    public void secondPlayerInsert() throws SQLException, IOException {
        Player playerFromJson = objectMapper.readValue(new File(PropertiesLoader.getSecondJson()), Player.class);
        PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.getInsert());
        preparedStatement.setInt(1, findNewID());
        preparedStatement.setString(2, chooseNick());
        preparedStatement.setInt(3, playerFromJson.getLevel());
        preparedStatement.setInt(4, playerFromJson.getTeamId());
        preparedStatement.executeUpdate();
        System.out.println("Random player added to DB");
    }

    public void countPlayers() throws SQLException {
        int count = 0;
        String countQuery = QueryBuilder.countPlayers();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(countQuery);
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        System.out.println("ILOSC MIRKOW : " + count);
    }

    public void countTeamMembers() throws SQLException {
        System.out.println("Podaj ID drużyny: ");
        int teamId = scanner.nextInt();
        int count = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryBuilder.displayNumberOfMembers(teamId));
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        System.out.println("Number of members : " + count);
    }

    public void genericSelect() throws SQLException {
        System.out.println("Podaj nazwę tabeli");
        String table = scanner.next();
        System.out.println("Podaj numer id");
        String id = scanner.next();
        Statement statement = connection.createStatement();

        if (table.equals("teams")) {
            ResultSet resultSet = statement.executeQuery(QueryBuilder.getTeamById(table, id));
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("team_id"));
                System.out.println(resultSet.getString("team_name"));
            }
        } else {
            ResultSet resultSet1 = statement.executeQuery(QueryBuilder.getPlayerById(table, id));
            while (resultSet1.next()) {
                System.out.println(resultSet1.getInt("id"));
                System.out.println(resultSet1.getString("nick"));
            }
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
