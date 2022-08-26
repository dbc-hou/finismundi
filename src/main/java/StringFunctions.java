public class StringFunctions {

    public String macToCirc(String stringIn) {
        String stringOut = stringIn.replace("ā", "â");
        stringOut = stringOut.replace("ē", "ê");
        stringOut = stringOut.replace("ī", "î");
        stringOut = stringOut.replace("ō", "ô");
        stringOut = stringOut.replace("ū", "û");
        return stringOut;
    }

    public String pageHeadInfo() {
        String html = "<html lang='en'><head><meta charset='UTF-8'>";
        html += "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css'>";
        html += "<link rel='stylesheet' href='css/latin.css'>";
        html += "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>";
        html += "<script src='js/jquery.js'></script>";
        html += "<script src='js/verbs.js'></script>";
        html += "<script src='js/nouns.js'></script>";
        html += "<script src='js/main.js'></script>";
        html += "</head><body>";
        html += "<div id='site-title' class='text-center col-12'>\n" +
                "<h1>LATIN FOR THE END OF THE WORLD</h1>\n" +
                "</div>";
        html += "<div id='verb-container' class='container-flex'>";

        return html;
    }
}
