public final class QueryBuilder {

    public static String getSelect() {
        return "Select Id, nick,level, team_name from players JOIN teams ON teams.team_id=players.team_id";
    }

    public static String getDeleteByNickQuery(String nick) {
        return "DELETE FROM PLAYERS WHERE Nick =" + "'" +  nick + "'" ;
    }

    public static String getDeleteByIdQuery (String id){
        return "DELETE FROM PLAYERS WHERE Id =" + "'" +  id + "'" ;
    }

    public static String getSelectByNick(String nick) {
       return  "Select Id, nick, level, team_name from players JOIN teams ON teams.team_id=players.team_id" +
                "  WHERE Nick =" + "'" +  nick + "'";
    }

    public static String getInsert(){
        return  "INSERT INTO players (Id,Nick,level,team_id) VALUES(?,?,?,?)";
    }
    public static String insertToTeams(){
        return " INSERT INTO players_to_teams (player_id,team_id) VALUES(?,?)";
    }
}
