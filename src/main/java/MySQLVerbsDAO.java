import Config.Config;

//import java.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;
import java.util.Properties;
import java.util.logging.Logger;

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
            PreparedStatement ps = conn.prepareStatement("Select * from movies where id = ?");
            ResultSet rs = ps.executeQuery();
            ps.setInt(1, id);
            rs.next();

            v.setId(rs.getInt("id"));
            v.setFirstPart(rs.getString("FirstPart"));
            v.setSecondPart(rs.getString("SecondPart"));
            v.setThirdPart(rs.getString("ThirdPart"));
            v.setSupine(rs.getString("Supine"));
            v.setDeponency(rs.getString("Deponency"));
            v.setNotes(rs.getString("Notes"));

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    public List<Verb>filteredList(String strMatch) {
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
}




