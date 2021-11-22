import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<E extends Entity> {
    void insert(E genericObject);
    List<E> selectAll() throws SQLException;
    void delete(E genericObject);
    E selectById(String iD) throws SQLException;
    void update(E genericObject);
}
