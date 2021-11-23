package queries;

public final class QueryBuilder {

    String selectQuery = "SELECT * FROM ";
    public String buildSelectAllQuery(EntityType entityType){
        return selectQuery + entityType;
    }

    public String buildSelectByIdQuery(EntityType entityType, String id){
        return selectQuery + entityType + " WHERE id = "+id;
    }

    String insertQuery = "INSERT INTO ";
    String playerValues = "(Id,Nick,level,team_id) VALUES(?,?,?,?)";
    String teamValues = "(id,teamName) VALUES(?,?)";

    public String buildGenericInsertQuery(EntityType entityType){
        if (entityType.equals(EntityType.PLAYERS)){
            return insertQuery+entityType+playerValues;
        }
         else return  insertQuery +entityType+teamValues;
    }


    String deleteQuery = "DELETE FROM ";
    String deleteById = " WHERE id = ";

    public String buildDeleteQuery(EntityType entityType, String id){
        return deleteQuery + entityType + deleteById + id;
    }

    String updateQuery = "UPDATE ";

    public String buildUpdateQuery(EntityType entityType, String field, String newValue, String idValue){
        return updateQuery + entityType + " SET " + field + " = " + "'" + newValue+ "'" + " WHERE id = " + idValue ;
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


    public static String maxID() { return "Select max(id) from "; }

    public static String GenericMaxId(EntityType entityType){
        return maxID()+entityType;
    }

    public static String getTeamById(String table, String id) {
        return "Select * from " + table + " where team_id = " + id;
    }

    public static String getPlayerById(String table, String id) {
        return "Select * from " + table + " where id = " + id;
    }
}