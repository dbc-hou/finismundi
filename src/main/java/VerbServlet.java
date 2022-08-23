import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//import com.google.gson.Gson;

@WebServlet(name = "VerbServlet", urlPatterns = "/verbs")
public class VerbServlet extends HttpServlet {
    private final VerbsDAO dao = new MySQLVerbsDAO();
    private final StringFunctions sf = new StringFunctions();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String verbID = request.getParameter("id");
        System.out.println(verbID);
// Set up HTML: One div 3 columns wide containing the complete list of verbs,
// (each verb gets a span, each span is separated with a <br/>
// then one div 6 columns wide to display information on the verb selected.
// The macronToCircumflex function converts macrons (which don't print) to circumflex accents.
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            List<Verb> verbs = dao.findAll();
            out.println(sf.pageHeadInfo());
//            out.println("<html lang=\"en\">\n" +
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\"><link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\"> <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script></head><body>");
//            out.println("<div id=\"verb-container\" class=\"container-flex\">");
//            out.println("<div id=\"verb-row\" class=\"row\">");
//            out.println("<div id=\"verb-list\" class=\"col-3\"><p>");
// Loop through and display all the first and second principal parts of each verb;
// each span gets a data-id attribute from the ID of its verb.
// Then close out the verb-list div.
            for (Verb v : verbs) {
                out.println("<span data-id=" + v.getId() + "><a href='/verbs?id=" + v.getId() + "'>" + sf.macToCirc(v.getFirstPart()) + ", " + sf.macToCirc(v.getSecondPart()) + "</a></span><br/>");
            }
            out.println("</div>");
// When user clicks a verb, it runs the findOne method to select a single verb
// corresponding to the ID of the verb selected.
// Various criteria determine whether the verb has third and fourth principal parts
// or is deponent and what fields are relevant to display.
            if (verbID != null) {
                int verbIDAsInteger = Integer.parseInt(verbID);
                Verb thisVerb = dao.findOne(verbIDAsInteger);
                out.println("<div id='verb-detail' class='col-6 card' style='top: 100px'>");
                out.print("<p style='text-size: large; color: purple'><strong>" + sf.macToCirc(thisVerb.getFirstPart()) + ", " + sf.macToCirc(thisVerb.getSecondPart()) + ", ");
// Display third principal part if the verb has one; otherwise ---.
                if (thisVerb.getThirdPart() == null) {
                    out.print ("---");
                } else {
                    out.print(sf.macToCirc(thisVerb.getThirdPart()));
                }
// Display fourth principal part if the verb has one; otherwise ---.
// Deponent and semi-deponent verbs don't have the fourth part.
                if (thisVerb.getDeponency() == null) {
                    if (thisVerb.getSupine() == null) {
                        out.print (", ---");
                    } else {
                        out.print(", " + sf.macToCirc(thisVerb.getSupine()));
                    }
                }
                out.print ("</strong></p>");
                out.println();
// Below the principal parts, display the verb's conjugation, deponency if any,
// English meaning(s), and any notes about it.
                out.println("<p>Conjugation " + thisVerb.getConjugation() + "</p>");
                if (thisVerb.getDeponency() != null) {
                    out.print("<p>" + thisVerb.getDeponency() + "</p>");
                }
                out.println();
                out.println("<p><strong>Meanings:</strong> " + thisVerb.getEnglishMeanings() + "</p>");
                if (thisVerb.getNotes() != null) {
                    out.println("<p><strong>Notes:</strong> " + thisVerb.getNotes() + "</p>");
                }
// Close out the verb-detail div, the body, and the html.
                out.println("</div></body></html>");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
