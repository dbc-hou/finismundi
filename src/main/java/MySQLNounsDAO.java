//import java.sql.*;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLNounsDAO implements NounsDAO {

    private Connection conn;


    public MySQLNounsDAO() {
        try {
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
    public List<Noun> findAll() throws SQLException {
        ArrayList<Noun> allNouns = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("Select * from NounList order by Nominative;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Noun n = new Noun();
            n.setId(rs.getInt("id"));
            n.setNominative(rs.getString("Nominative"));
            n.setGenitive(rs.getString("Genitive"));
            n.setGender(rs.getString("Gender"));
            n.setDeclension(rs.getString("Declension"));
            n.setEnglishMeanings(rs.getString("EnglishMeanings"));
            n.setNotes(rs.getString("Notes"));

            allNouns.add(n);
        }
        rs.close();
        ps.close();

        return allNouns;
    }

    @Override
    public Noun findOne(int id) {
        Noun n = new Noun();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from NounList where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            n.setId(rs.getInt("id"));
            n.setNominative(rs.getString("Nominative"));
            n.setGenitive(rs.getString("Genitive"));
            n.setGender(rs.getString("Gender"));
            n.setDeclension(rs.getString("Declension"));
            n.setEnglishMeanings(rs.getString("EnglishMeanings"));
            n.setNotes(rs.getString("Notes"));

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public List<Noun> filteredList(String strMatch) {
        ArrayList<Noun> someNouns = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from verbs where Nominative = " + strMatch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Noun n = new Noun();
                n.setId(rs.getInt("id"));
                n.setNominative(rs.getString("Nominative"));
                n.setGenitive(rs.getString("Genitive"));
                n.setGender(rs.getString("Gender"));
                n.setDeclension(rs.getString("Declension"));
                n.setEnglishMeanings(rs.getString("EnglishMeanings"));
                n.setNotes(rs.getString("Notes"));
                someNouns.add(n);

                rs.close();
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return someNouns;
    }

    public ArrayList<Noun> filterByLetter(String strInitial) {
        ArrayList<Noun> someNouns = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sqlByLetter(strInitial));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Noun n = new Noun();
                n.setId(rs.getInt("id"));
                n.setNominative(rs.getString("Nominative"));
                n.setGenitive(rs.getString("Genitive"));
                n.setGender(rs.getString("Gender"));
                n.setDeclension(rs.getString("Declension"));
                n.setEnglishMeanings(rs.getString("EnglishMeanings"));
                n.setNotes(rs.getString("Notes"));
                someNouns.add(n);

                rs.close();
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return someNouns;
    }

    public String sqlByLetter(String initialLetter) {
        String initialLetterConvert;
        String basicSelect = "Select * from NounList";
        switch (initialLetter) {
            case "All":
                return basicSelect;
            case "A":
                initialLetterConvert = " Where Nominative like 'a%' or Nominative like 'ā%' or Nominative like 'â%'";
                break;
            case "B":
                initialLetterConvert = " Where Nominative like 'b%'";
                break;
            case "C":
                initialLetterConvert = " Where Nominative like 'c%'";
                break;
            case "D":
                initialLetterConvert = " Where Nominative like 'd%'";
                break;
            case "E":
                initialLetterConvert = " Where Nominative like 'e%' or Nominative like 'ē%' or Nominative like 'ê%'";
                break;
            case "F":
                initialLetterConvert = " Where Nominative like 'f%'";
                break;
            case "G":
                initialLetterConvert = " Where Nominative like 'g%'";
                break;
            case "H":
                initialLetterConvert = " Where Nominative like 'h%'";
                break;
            case "I":
                initialLetterConvert = " Where Nominative like 'i%' or Nominative like 'ī%' or Nominative like 'î%' or Nominative like 'j%'";
                break;
            case "L":
                initialLetterConvert = " Where Nominative like 'l%'";
                break;
            case "M":
                initialLetterConvert = " Where Nominative like 'm%'";
                break;
            case "N":
                initialLetterConvert = " Where Nominative like 'n%'";
                break;
            case "O":
                initialLetterConvert = " Where Nominative like 'o%' or Nominative like 'ō%' or Nominative like 'ô%'";
                break;
            case "P":
                initialLetterConvert = " Where Nominative like 'p%'";
                break;
            case "Q":
                initialLetterConvert = " Where Nominative like 'q%'";
                break;
            case "R":
                initialLetterConvert = " Where Nominative like 'r%'";
                break;
            case "S":
                initialLetterConvert = " Where Nominative like 's%'";
                break;
            case "T":
                initialLetterConvert = " Where Nominative like 't%'";
                break;
            case "U":
                initialLetterConvert = " Where Nominative like 'u%' or Nominative like 'ū%' or Nominative like 'û%' or Nominative like 'v%'";
                break;
            default:
                initialLetterConvert = "";
        }
        return basicSelect + initialLetterConvert;
    }
}






