import java.sql.SQLException;
import java.util.List;

public interface NounsDAO {
    List<Noun> findAll() throws SQLException;

    Noun findOne(int id);

    List<Noun> filteredList(String strMatch);
}
