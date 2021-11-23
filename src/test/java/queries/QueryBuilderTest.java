package queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    QueryBuilder queryBuilder = new QueryBuilder();

    @Test
    void buildSelectAllQuery() {
        String expected = "SELECT * FROM "+ EntityType.PLAYERS;
        String actual = queryBuilder.buildSelectAllQuery(EntityType.PLAYERS);
        assertEquals(expected, actual);
    }

/*    @Test
    void buildSelectByIdQuery() {
        String expected = "SELECT * FROM PLAYERS WHERE id = id";
        String actual = queryBuilder.buildSelectByIdQuery(EntityType.PLAYERS,"id");
        assertEquals(expected, actual);
    }*/

/*
    @Test
    void buildInsertPlayerQuery() {
        String expected = "INSERT INTO PLAYERS(Id,Nick,level,team_id) VALUES(?,?,?,?)";
        String actual = queryBuilder.buildInsertPlayerQuery(EntityType.PLAYERS);
        assertEquals(expected,actual);
    }
*/

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

/*    @Test
    void buildInsertTeamQuery() {
        String expected = "INSERT INTO TEAMS(id,teamName) VALUES(?,?)";
        String actual = queryBuilder.buildInsertTeamQuery(EntityType.TEAMS);
        assertEquals(expected, actual);
    }*/
}