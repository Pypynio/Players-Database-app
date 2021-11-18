public final class QueryBuilder {

    public static String getSelect() {
        return "Select Id, nick,level, players.team_id, team_name from players left JOIN teams ON players.team_id = teams.team_id";
    }

    public static String getDeleteByNickQuery(String nick) {
        return "DELETE FROM PLAYERS WHERE Nick =" + "'" +  nick + "'" ;}

    public static String getDeleteByIdQuery (String id){
        return "DELETE FROM PLAYERS WHERE Id =" + "'" +  id + "'" ;
    }

    public static String getSelectByNick(String nick) {
        return  "Select Id, nick,level, players.team_id, team_name from players left JOIN teams ON players.team_id = teams.team_id" +
                "  WHERE Nick =" + "'" +  nick + "'";}

    public static String getInsert(){
        return  "INSERT INTO players (Id,Nick,level,team_id) VALUES(?,?,?,?)";
    }

    public static String countPlayers(){return "SELECT COUNT(*) FROM players";}

    public static String displayNumberOfNoobs(String teamId){
        return "SELECT COUNT(*) FROM players where players.team_id = " + teamId;  }



    public static String getById(String table,String id){
        String query = "Select * from" + table + " where id = " + id;
        return query;
    }
}