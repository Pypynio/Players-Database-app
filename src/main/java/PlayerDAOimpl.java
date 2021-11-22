import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class PlayerDAOimpl implements PlayerDAO {

    private PlayerParser playerParser;
    private Connection connection;
    ObjectMapper objectMapper = new ObjectMapper();
    Scanner scanner = new Scanner(System.in);

    public PlayerDAOimpl() throws IOException {
        setupConnection();
        this.playerParser = new PlayerParser();
    }

    public String chooseNick() throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        List<String> nicki = mapper.readValue(new File(PropertiesLoader.getUrlToNickList()), List.class );
        Random r = new Random();
        int randomNumber = r.nextInt(nicki.size());
        String randomNick = nicki.get(randomNumber);
        System.out.println(randomNick);
        return randomNick;
    }

    @Override
    public void insertPlayer() throws SQLException, IOException {
        Player playerFromFirstJson = objectMapper.readValue(new File(PropertiesLoader.getUrlToJson()), Player.class);
        PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.getInsert());
        preparedStatement.setInt(1, findNewID());
        preparedStatement.setString(2, playerFromFirstJson.getNick());
        preparedStatement.setInt(3, playerFromFirstJson.getLevel());
        preparedStatement.setInt(4, playerFromFirstJson.getTeamId());
        preparedStatement.executeUpdate();
        System.out.println("Player added to DB");
    }

    public void secondPlayerInsert() throws SQLException, IOException {
        Player playerFromJson = objectMapper.readValue(new File(PropertiesLoader.getSecondJson()), Player.class);
        PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.getInsert());
        preparedStatement.setInt(1,findNewID());
        preparedStatement.setString(2,chooseNick());
        preparedStatement.setInt(3, playerFromJson.getLevel());
        preparedStatement.setInt(4, playerFromJson.getTeamId());
        preparedStatement.executeUpdate();
        System.out.println("Random player added to DB");
    }

    @Override
    public List<Player> selectAllPlayers() throws SQLException {
        String query = QueryBuilder.getSelect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Player> players = playerParser.createPlayersListFromResultSet(resultSet);
        for (Player player : players) {
            player.SayMyName();
            System.out.println("~~~~~~");
        }
        return players;
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
            while (resultSet1.next()){
                System.out.println(resultSet1.getInt("id"));
                System.out.println(resultSet1.getString("nick"));
            }
        }
    }
    public Integer findNewID() throws SQLException {
        int newID = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryBuilder.maxID());
        while (resultSet.next()) {
            newID = resultSet.getInt(1) + 1;
        }
        return newID;
    }


    @Override
    public void deletePlayerByNick() {
        try {
            System.out.println("Podaj nick gracza, którego chcesz usunąć: ");
            String nick = scanner.next();
            Statement statement = connection.createStatement();
            statement.executeUpdate(QueryBuilder.getDeleteByNickQuery(nick));
            System.out.println("Gracz usunięty");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletePlayerById() {
        try {
            System.out.println("Podaj Id gracza, którego chcesz usunąć :");
            String id = scanner.next();
            String query = QueryBuilder.getDeleteByIdQuery(id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("gracz usunięty");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Player selectPlayerByNick() throws SQLException {
        System.out.println("Podaj nick: ");
        String nick = scanner.next();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryBuilder.getSelectByNick(nick));
        Player player = playerParser.createPlayerFromResultSet(resultSet);
        player.SayMyName();
        return player;
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
