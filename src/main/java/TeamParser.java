import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamParser {

    public Team createTeamFromResultSet(ResultSet resultSet) throws SQLException {
        String id = null;
        String teamName = null;

        while (resultSet.next()){
            id = resultSet.getString("id");
            teamName= resultSet.getString("teamName");
        }
        return new Team(id,teamName);
    }

    public List<Team> createTeamsListFromResultSet(ResultSet resultSet) throws SQLException {
    List<Team> teams = new ArrayList<>();
    while (resultSet.next()){
        String id = resultSet.getString("id");
        String teamName = resultSet.getString("teamName");
        Team team = new Team(id,teamName);
        teams.add(team);
    }
    return teams;
    }
}
