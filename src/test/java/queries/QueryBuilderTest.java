package queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    QueryBuilder queryBuilder = new QueryBuilder();

/*    @Test
    void buildSelectAllQuery() {
        String expected = "SELECT * FROM "+ EntityType.PLAYERS;
        String actual = queryBuilder.buildSelectAllQuery(EntityType.PLAYERS);
        assertEquals(expected, actual);
    }*/

/*    @Test
    void buildGenericInsertQuery() {
        String playersExpected = "INSERT INTO PLAYERS(Id,Nick,level,team_id) VALUES(?,?,?,?)";
        String playersActual = queryBuilder.buildGenericInsertQuery(EntityType.PLAYERS);
        assertEquals(playersExpected, playersActual);
        String teamsExpected = "INSERT INTO TEAMS(id,teamName) VALUES(?,?)";
        String teamsActual = queryBuilder.buildGenericInsertQuery(EntityType.TEAMS);
        assertEquals(teamsExpected, teamsActual);
    }*/

    @Test
    void buildSelectByIdQuery() {
        String expected = "SELECT * FROM " + EntityType.PLAYERS + " WHERE id = id";
        String actual = queryBuilder.buildSelectByIdQuery(EntityType.PLAYERS,"id");
        assertEquals(expected, actual);
    }

/*    @Test
    void buildDeleteQuery() {
        String expected = "DELETE FROM TEAMS WHERE id = 3";
        String actual = queryBuilder.buildDeleteQuery(EntityType.TEAMS,"3");
        assertEquals(expected,actual);
    }*/


/*    @Test
    void buildUpgradeQuery() {
        String expected = "UPDATE PLAYERS SET id = '112' WHERE id = 4";
        String actual = queryBuilder.buildUpdateQuery(EntityType.PLAYERS,"id","112","4");
        assertEquals(expected,actual);
    }*/

}