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
        String verbID = request.getParameter("id");
        System.out.println(verbID);
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            List<Verb> verbs = dao.findAll();
            out.println("<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\"><link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\"> <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script></head><body>");
            out.println("<div id=\"verb-container\" class=\"container-flex\">");
            out.println("<div id=\"verb-row\" class=\"row\">");
            out.println("<div id=\"verb-list\" class=\"col-3\">");
            for (Verb v : verbs) {
                out.println("<p data-id=" + v.getId() + "><a href=\"/verbs?id=" + v.getId() + "\">" + macronToCircumflex(v.getFirstPart()) + ", " + macronToCircumflex(v.getSecondPart()) + "</a></p>");
            }
            out.println("</div>");
            if (verbID != null) {
                int verbIDAsInteger = Integer.parseInt(verbID);
                Verb thisVerb = dao.findOne(verbIDAsInteger);
                out.println("<div id=\"verb-detail\" class=\"col-6\">");
                out.print("<p style=\"text-size: large\">" + thisVerb.getFirstPart() + ", " + thisVerb.getSecondPart() + ", ");
                if (thisVerb.getThirdPart() == null) {
                    out.print ("---");
                } else {
                    out.print(thisVerb.getThirdPart());
                }
                if (thisVerb.getDeponency() != "Deponent") {
                    if (thisVerb.getSupine() == null) {
                        out.print (", ---");
                    } else {
                        out.print(", " + thisVerb.getSupine());
                    }
                }
                out.print ("</p>");
                out.println();
                out.print("Conjugation " + thisVerb.getConjugation());
                if (thisVerb.getDeponency() != null) {
                    out.print(thisVerb.getDeponency());
                }
                out.println();
                out.println(thisVerb.getEnglishMeanings());
                out.println(thisVerb.getNotes());
                out.println("</div></body></html>");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private String macronToCircumflex (String stringIn) {
        String stringOut = stringIn.replace("ā", "â");
        stringOut = stringOut.replace("ē", "ê");
        stringOut = stringOut.replace("ī", "î");
        stringOut = stringOut.replace("ō", "ô");
        stringOut = stringOut.replace("ū", "û");
        return stringOut;
    }
}
