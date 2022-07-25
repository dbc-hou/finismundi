import java.sql.SQLException;
import java.util.List;

public interface VerbsDAO {
    List<Verb> findAll() throws SQLException;

    Verb findOne(int id);

    List<Verb> filteredList(String strMatch);
}
