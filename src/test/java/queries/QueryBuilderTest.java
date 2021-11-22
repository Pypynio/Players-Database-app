package queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    QueryBuilder queryBuilder = new QueryBuilder();

    @Test
    public void testSelectAllPlayers(){
        String expected = "SELECT * FROM PLAYERS";
        String actual = queryBuilder.buildSelectAllQuery(EntityType.PLAYERS);
        assertEquals(expected, actual);
    }

    @Test
    void buildSelectByIdQuery() {
        String expected = "SELECT * FROM PLAYERS WHERE id = id";
        String actual = queryBuilder.buildSelectByIdQuery(EntityType.PLAYERS,"id");
        assertEquals(expected, actual);
    }
}