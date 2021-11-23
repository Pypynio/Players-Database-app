import com.fasterxml.jackson.databind.ObjectMapper;
import queries.EntityType;
import queries.QueryBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class TeamDAO implements DataAccessObject<Team> {
    private final TeamParser teamParser = new TeamParser();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final QueryBuilder queryBuilder = new QueryBuilder();
    private Connection connection;

    public TeamDAO(){ setupConnection(); } // konstruktor

    @Override
    public void insert(Team genericObject) {
        try {
            Team team = objectMapper.readValue(new File(PropertiesLoader.getSecondJson()),Team.class);
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.buildGenericInsertQuery(EntityType.TEAMS));
            preparedStatement.setInt(1,findNewID());
            preparedStatement.setString(2,team.getTeamName());
            preparedStatement.executeUpdate();
            System.out.println("TEAM ADDED TO DB");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Team> selectAll() throws SQLException {
        String query = queryBuilder.buildSelectAllQuery(EntityType.TEAMS);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Team>teams = teamParser.createTeamsListFromResultSet(resultSet);
        for (Team team: teams) {
            team.sayMyName();
        }
        return null;
    }

    @Override
    public void delete(Team genericObject, String id) {
        try {
            Statement statement = connection.createStatement();
            String query = queryBuilder.buildDeleteQuery(EntityType.TEAMS,id);
            statement.executeUpdate(query);
            System.out.println("Team deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Team selectById(String iD) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryBuilder.buildSelectByIdQuery(EntityType.TEAMS,iD));
        Team team = teamParser.createTeamFromResultSet(resultSet);
        team.sayMyName();
        return team;
    }

    @Override
    public void update(Team genericObject,String field,String newValue,String idValue) {
        try {
            Statement statement = connection.createStatement();
            String query = queryBuilder.buildUpdateQuery(EntityType.TEAMS,field,newValue,idValue);
            statement.executeUpdate(query);
            System.out.println("TEAM UPDATE DONE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupConnection() {
        try {
            String databaseUrl = PropertiesLoader.getDataBaseUrl();
            this.connection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer findNewID() throws SQLException {
        int newID = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryBuilder.GenericMaxId(EntityType.TEAMS));
        while (resultSet.next()) {
            newID = resultSet.getInt(1) + 1;
        }
        return newID;
    }
}
