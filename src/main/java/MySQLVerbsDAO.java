//import java.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;

public class MySQLVerbsDAO implements VerbsDAO {

    private Connection conn;


    public MySQLVerbsDAO() {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            DriverManager.registerDriver(new Driver() {
//                @Override
//                public Connection connect(String url, Properties info) throws SQLException {
//                    return null;
//                }
//
//                @Override
//                public boolean acceptsURL(String url) throws SQLException {
//                    return false;
//                }
//
//                @Override
//                public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
//                    return new DriverPropertyInfo[0];
//                }
//
//                @Override
//                public int getMajorVersion() {
//                    return 0;
//                }
//
//                @Override
//                public int getMinorVersion() {
//                    return 0;
//                }
//
//                @Override
//                public boolean jdbcCompliant() {
//                    return false;
//                }
//
//                @Override
//                public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//                    return null;
//                }
//            });
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/latin?" +
                    "user=root&" +
                    "password=codeup");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Verb> findAll() throws SQLException {
        ArrayList<Verb> allVerbs = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("Select * from VerbList order by FirstPart;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Verb v = new Verb();
            v.setId(rs.getInt("id"));
            v.setFirstPart(rs.getString("FirstPart"));
            v.setSecondPart(rs.getString("SecondPart"));
            v.setThirdPart(rs.getString("ThirdPart"));
            v.setSupine(rs.getString("Supine"));
            v.setDeponency(rs.getString("Deponency"));
            v.setEnglishMeanings(rs.getString("EnglishMeanings"));
            v.setNotes(rs.getString("Notes"));

            allVerbs.add(v);
//            System.out.println(v);
        }
        rs.close();
        ps.close();

        return allVerbs;
    }

    @Override
    public Verb findOne(int id) {
        Verb v = new Verb();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from VerbList where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            v.setId(rs.getInt("id"));
            v.setFirstPart(rs.getString("FirstPart"));
            v.setSecondPart(rs.getString("SecondPart"));
            v.setThirdPart(rs.getString("ThirdPart"));
            v.setSupine(rs.getString("Supine"));
            v.setConjugation(rs.getString("Conjugation"));
            v.setDeponency(rs.getString("Deponency"));
            v.setEnglishMeanings(rs.getString("EnglishMeanings"));
            v.setNotes(rs.getString("Notes"));

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    public List<Verb> filteredList(String strMatch) {
        ArrayList<Verb> someVerbs = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from verbs where FirstPart = " + strMatch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Verb v = new Verb();
                v.setId(rs.getInt("id"));
                v.setFirstPart(rs.getString("FirstPart"));
                v.setSecondPart(rs.getString("SecondPart"));
                v.setThirdPart(rs.getString("ThirdPart"));
                v.setSupine(rs.getString("Supine"));
                v.setDeponency(rs.getString("Deponency"));
                v.setNotes(rs.getString("Notes"));
                someVerbs.add(v);

                rs.close();
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return someVerbs;
    }

    public List<Verb> filterByLetter(String strInitial) {
        ArrayList<Verb> someVerbs = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sqlByLetter(strInitial));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Verb v = new Verb();
                v.setId(rs.getInt("id"));
                v.setFirstPart(rs.getString("FirstPart"));
                v.setSecondPart(rs.getString("SecondPart"));
                v.setThirdPart(rs.getString("ThirdPart"));
                v.setSupine(rs.getString("Supine"));
                v.setDeponency(rs.getString("Deponency"));
                v.setNotes(rs.getString("Notes"));
                someVerbs.add(v);

                rs.close();
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return someVerbs;
    }

    public String sqlByLetter(String initialLetter) {
        String initialLetterConvert;
        String basicSelect = "Select * from VerbList";
        switch (initialLetter) {
            case "All":
                return basicSelect;
            case "A":
                initialLetterConvert = " Where FirstPart like 'a%' or FirstPart like 'ā%' or FirstPart like 'â%'";
                break;
            case "B":
                initialLetterConvert = " Where FirstPart like 'b%'";
                break;
            case "C":
                initialLetterConvert = " Where FirstPart like 'c%'";
                break;
            case "D":
                initialLetterConvert = " Where FirstPart like 'd%'";
                break;
            case "E":
                initialLetterConvert = " Where FirstPart like 'e%' or FirstPart like 'ē%' or FirstPart like 'ê%'";
                break;
            case "F":
                initialLetterConvert = " Where FirstPart like 'f%'";
                break;
            case "G":
                initialLetterConvert = " Where FirstPart like 'g%'";
                break;
            case "H":
                initialLetterConvert = " Where FirstPart like 'h%'";
                break;
            case "I":
                initialLetterConvert = " Where FirstPart like 'i%' or FirstPart like 'ī%' or FirstPart like 'î%' or FirstPart like 'j%'";
                break;
            case "L":
                initialLetterConvert = " Where FirstPart like 'l%'";
                break;
            case "M":
                initialLetterConvert = " Where FirstPart like 'm%'";
                break;
            case "N":
                initialLetterConvert = " Where FirstPart like 'n%'";
                break;
            case "O":
                initialLetterConvert = " Where FirstPart like 'o%' or FirstPart like 'ō%' or FirstPart like 'ô%'";
                break;
            case "P":
                initialLetterConvert = " Where FirstPart like 'p%'";
                break;
            case "Q":
                initialLetterConvert = " Where FirstPart like 'q%'";
                break;
            case "R":
                initialLetterConvert = " Where FirstPart like 'r%'";
                break;
            case "S":
                initialLetterConvert = " Where FirstPart like 's%'";
                break;
            case "T":
                initialLetterConvert = " Where FirstPart like 't%'";
                break;
            case "U":
                initialLetterConvert = " Where FirstPart like 'u%' or FirstPart like 'ū%' or FirstPart like 'û%' or FirstPart like 'v%'";
                break;
            default:
                initialLetterConvert = "";
        }
        return basicSelect + initialLetterConvert;
    }
}






