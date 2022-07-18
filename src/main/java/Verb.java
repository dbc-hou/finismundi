public class Verb {
    private int id;
    private String firstPart;
    private String secondPart;
    private String thirdPart;
    private String supine;
    private String conjugation;
    private String deponency;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(String firstPart) {
        this.firstPart = firstPart;
    }

    public String getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(String secondPart) {
        this.secondPart = secondPart;
    }

    public String getThirdPart() {
        return thirdPart;
    }

    public void setThirdPart(String thirdPart) {
        this.thirdPart = thirdPart;
    }

    public String getSupine() {
        return supine;
    }

    public void setSupine(String supine) {
        this.supine = supine;
    }

    public String getConjugation() {
        return conjugation;
    }

    public void setConjugation(String conjugation) {
        this.conjugation = conjugation;
    }

    public String getDeponency() {
        return deponency;
    }

    public void setDeponency(String deponency) {
        this.deponency = deponency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Verb(int id, String firstPart, String secondPart, String thirdPart, String supine, String conjugation, String deponency, String notes) {
        this.id = id;
        this.firstPart = firstPart;
        this.secondPart = secondPart;
        this.thirdPart = thirdPart;
        this.supine = supine;
        this.conjugation = conjugation;
        this.deponency = deponency;
        this.notes = notes;
    }

    public Verb() {

    }
}
