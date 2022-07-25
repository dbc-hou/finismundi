import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLException;
//import com.google.gson.Gson;

@WebServlet(name = "VerbServlet", urlPatterns = "/verbs")
public class VerbServlet extends HttpServlet {
    private final VerbsDAO dao = new MySQLVerbsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            Object verbString = dao.findAll().toArray();
            System.out.println(verbString);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
