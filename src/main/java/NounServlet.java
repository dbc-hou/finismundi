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
// (each noun gets a span, each span is separated with a <br/>
// then one div 6 columns wide to display information on the noun selected.
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
            out.println("<div id='noun-row' class='row col-12'>");
            out.println("<div id='instructions' class='col-12'>Click a noun in the list to see more information about it.</div>");
            out.println("<div id='noun-list' class='card col-3' style='height: 300px; overflow: scroll; top: 3em; left:1em'>");
            for (Noun n : nouns) {
                out.println("<span data-id=" + n.getId() + "><a href='/nouns?id=" + n.getId() + "'>" + sf.macToCirc(n.getNominative()) + ", " + sf.macToCirc(n.getGenitive()) + "</a></span>");
            }
            out.println("</div>");
// When user clicks a noun, it runs the findOne method to select a single noun
// corresponding to the ID of the noun selected. This displays the gender, declension,
// meanings, notes from the NounList table.
            if (nounID != null) {
                int nounIDAsInteger = Integer.parseInt(nounID);
                Noun thisNoun = dao.findOne(nounIDAsInteger);
                out.println("<div id='noun-detail' class='col-6 card ml-1' style='top: 3em'>");
                out.print("<p style='text-size: larger; color: purple'><strong>" + sf.macToCirc(thisNoun.getNominative()) + ", " + sf.macToCirc(thisNoun.getGenitive()) + "</strong><br/>");
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
                out.println("<p><strong>Meanings:</strong> <em>" + thisNoun.getEnglishMeanings() + "</em></p>");
                if (thisNoun.getNotes() != null) {
                    out.println("<p><strong>Notes:</strong> " + sf.macToCirc(thisNoun.getNotes()) + "</p>");
                }
// Close out any unclosed divs, the body, and the html.
                out.println("</div></div></body></html>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
