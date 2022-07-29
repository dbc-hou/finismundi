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
import java.util.List;
//import com.google.gson.Gson;

@WebServlet(name = "VerbServlet", urlPatterns = "/verbs")
public class VerbServlet extends HttpServlet {
    private final VerbsDAO dao = new MySQLVerbsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            List<Verb> verbs = dao.findAll();
            out.println("<html><body>");
            for (Verb v : verbs) {
                out.println("<p>" + v + "</p>");
            }
            out.println("</body></html>");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
