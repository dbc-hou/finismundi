package Config;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Config {
//    public static final String JDBCDriver = "com.mysql.jdbc.Driver";
    public static final String myDBConn = "jdbc:mysql://localhost:3306/latin";
    public static final String myDBID = "root";
    public static final String myDBPW = "codeup";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            DriverManager.registerDriver(new Driver() {
                @Override
                public Connection connect(String url, Properties info) throws SQLException {
                        return null;
                }

                @Override
                public boolean acceptsURL(String url) throws SQLException {
                    return false;
                }

                @Override
                public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                    return new DriverPropertyInfo[0];
                }

                @Override
                public int getMajorVersion() {
                    return 0;
                }

                @Override
                public int getMinorVersion() {
                    return 0;
                }

                @Override
                public boolean jdbcCompliant() {
                    return false;
                }

                @Override
                public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                    return null;
                }
            });
            conn = DriverManager.getConnection(myDBConn,myDBID,myDBPW);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "Select ID, FirstPart, SecondPart, Conjugation from VerbList";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String firstPart = rs.getString("FirstPart");
                String secondPart = rs.getString("SecondPart");
                String conjugation = rs.getString("Conjugation");

                System.out.println(ID + " " + firstPart + " " + secondPart + " " + conjugation);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null)
                    stmt.close();
            } catch (SQLException se2) {

            } try {
                if(conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
