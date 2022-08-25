import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//import com.google.gson.Gson;

@WebServlet(name = "NounServlet", urlPatterns = "/nouns")
public class NounServlet extends HttpServlet {
    private final NounsDAO dao = new MySQLNounsDAO();
    private final StringFunctions sf = new StringFunctions();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genderPicker = "";
        response.setContentType("application/json");
        String nounID = request.getParameter("id");
        System.out.println(nounID);
// Set up HTML: One div 3 columns wide containing the complete list of verbs,
// (each verb gets a span, each span is separated with a <br/>
// then one div 6 columns wide to display information on the verb selected.
// The macronToCircumflex function converts macrons (which don't print) to circumflex accents.
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            List<Noun> nouns = null;
            try {
                nouns = dao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println(sf.pageHeadInfo());

            for (Noun n : nouns) {
                out.println("<span data-id=" + n.getId() + "><a href='/verbs?id=" + n.getId() + "'>" + sf.macToCirc(n.getNominative()) + ", " + sf.macToCirc(n.getGenitive()) + "</a></span><br/>");
            }
            out.println("</div>");
// When user clicks a verb, it runs the findOne method to select a single verb
// corresponding to the ID of the verb selected.
// Various criteria determine whether the verb has third and fourth principal parts
// or is deponent and what fields are relevant to display.
            if (nounID != null) {
                int nounIDAsInteger = Integer.parseInt(nounID);
                Noun thisNoun = dao.findOne(nounIDAsInteger);
                out.println("<div id='noun-detail' class='col-6 card' style='top: 100px'>");
                out.print("<p style='text-size: large; color: purple'><strong>" + sf.macToCirc(thisNoun.getNominative()) + ", " + sf.macToCirc(thisNoun.getGenitive()) + ",</strong><br/>");
                switch (thisNoun.getGender()) {
                    case "m":
                        genderPicker = "masculine";
                        break;
                    case "f":
                        genderPicker = "feminine";
                        break;
                    case "n":
                        genderPicker = "neuter";
                        break;
                    case "u":
                        genderPicker = "uter (m or f)";
                        break;
                    default:
                        genderPicker = "";
                }
                out.println("<strong>Gender:</strong> " + genderPicker + "<br/>");
                out.println("<strong>Declension:</strong> " + thisNoun.getDeclension() + "<br/>");
                out.println("<em>" + thisNoun.getEnglishMeanings() + "</em><br/>");
                out.println(thisNoun.getNotes() + "</p>");
// Below the principal parts, display the verb's conjugation, deponency if any,
// English meaning(s), and any notes about it.

                out.println("<p><strong>Meanings:</strong> " + thisNoun.getEnglishMeanings() + "</p>");
                if (thisNoun.getNotes() != null) {
                    out.println("<p><strong>Notes:</strong> " + thisNoun.getNotes() + "</p>");
                }
// Close out the verb-detail div, the body, and the html.
                out.println("</div></body></html>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
