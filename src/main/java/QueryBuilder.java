public final class QueryBuilder {

    public static String getSelect() {
        return "SELECT * FROM PLAYERS";
    }

    public static String getDeleteQuery(String nick) {
        return "DELETE FROM PLAYERS WHERE Nick =" + "'" +  nick + "'" ;
    }

    public static String getSelectByNick(String nick) {
        return "SELECT * FROM PLAYERS WHERE Nick ="  + "'" +  nick + "'";
    }

    public static String getInsert(){
        return  "INSERT INTO players (Id,Nick) VALUES(?,?)";
    }
}
