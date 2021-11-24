package queries;

public final class QueryBuilder {

    String selectQuery = "SELECT * FROM ";
    String insertQuery = "INSERT INTO ";
    String playerValues = "(Id,Nick,level,team_id) VALUES(?,?,?,?)";
    String teamValues = "(id,teamName) VALUES(?,?)";
    String deleteQuery = "DELETE FROM ";
    String deleteById = " WHERE id = ";
    String updateQuery = "UPDATE ";

    public String buildSelectAllQuery(EntityType entityType){
        return selectQuery + entityType;
    }

    public String buildSelectByIdQuery(EntityType entityType, String id){
        return selectQuery + entityType + " WHERE id = "+id;
    }

    public String buildGenericInsertQuery(EntityType entityType){
        if (entityType.equals(EntityType.PLAYERS)){
            return insertQuery+entityType+playerValues;
        }
         else return  insertQuery +entityType+teamValues;
    }

    public String buildDeleteQuery(EntityType entityType, String id){
        return deleteQuery + entityType + deleteById + id;
    }

    public String buildUpdateQuery(EntityType entityType, String field, String newValue, String idValue){
        return updateQuery + entityType + " SET " + field + " = " + "'" + newValue+ "'" + " WHERE id = " + idValue ;
    }

    public static String maxID() { return "Select max(id) from "; }

    public static String GenericMaxId(EntityType entityType){
        return maxID()+entityType;
    }

    public static String getSelect() {
        return "Select Id, nick,level, players.team_id, team_name from players left JOIN teams ON players.team_id = teams.team_id";
    }
}
