import java.sql.SQLException;
import java.util.List;

public interface VerbsDAO {
    public List<Verb> findAll () throws SQLException;

    Verb findOne(int id);

    public List<Verb> filteredList(String strMatch);
}
