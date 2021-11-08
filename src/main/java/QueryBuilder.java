public final class QueryBuilder {

    public static String getSelect() {
        return "SELECT * FROM PLAYERS";
    }

    public static String getDeleteByNickQuery(String nick) {
        return "DELETE FROM PLAYERS WHERE Nick =" + "'" +  nick + "'" ;
    }

    public static String getDeleteByIdQuery (String id){
        return "DELETE FROM PLAYERS WHERE Id =" + "'" +  id + "'" ;
    }

    public static String getSelectByNick(String nick) {
        return "SELECT * FROM PLAYERS WHERE Nick ="  + "'" +  nick + "'";
    }

    public static String insertToTeams(){return  "INSERT INTO teams (team_id,team_name) VALUES(?,?)";}

    public static String getInsert(){
        return  "INSERT INTO players (Id,Nick) VALUES(?,?)";
    }
}
