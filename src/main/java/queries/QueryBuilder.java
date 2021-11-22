package queries;

public final class QueryBuilder {

    String selectQuery = "SELECT * FROM ";
    public String buildSelectAllQuery(EntityType entityType){
        return selectQuery + entityType;
    }

    String SelectByIdQuery = "SELECT * FROM ";

    public String buildSelectByIdQuery(EntityType entityType, String id){
        return SelectByIdQuery + entityType + " WHERE id = "+id;
    }


    public static String getSelect() {
        return "Select Id, nick,level, players.team_id, team_name from players left JOIN teams ON players.team_id = teams.team_id";
    }

    public static String getDeleteByNickQuery(String nick) {
        return "DELETE FROM PLAYERS WHERE Nick =" + "'" + nick + "'";
    }

    public static String getDeleteByIdQuery(String id) {
        return "DELETE FROM PLAYERS WHERE Id =" + "'" + id + "'";
    }

    public static String getSelectByNick(String nick) {
        return "Select Id, nick,level, players.team_id, team_name from players left JOIN teams ON players.team_id = teams.team_id" +
                "  WHERE Nick =" + "'" + nick + "'";
    }

    public static String getInsert() {
        return "INSERT INTO players (Id,Nick,level,team_id) VALUES(?,?,?,?)";
    }

    public static String countPlayers() {
        return "SELECT COUNT(*) FROM players";
    }

    public static String displayNumberOfMembers(int teamId) {
        return "SELECT COUNT(*) FROM players where players.team_id = " + teamId;
    }

    public static String maxID() {
        return "Select max(id) from players";
    }

    public static String getTeamById(String table, String id) {
        return "Select * from " + table + " where team_id = " + id;
    }

    public static String getPlayerById(String table, String id) {
        return "Select * from " + table + " where id = " + id;
    }
}