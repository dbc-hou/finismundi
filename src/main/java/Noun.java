public class Noun {
    private int id;
    private String nominative;
    private String genitive;
    private String gender;
    private String declension;
    private String EnglishMeanings;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNominative() {
        return nominative;
    }

    public void setNominative(String nominative) {
        this.nominative = nominative;
    }

    public String getGenitive() {
        return genitive;
    }

    public void setGenitive(String genitive) {
        this.genitive = genitive;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeclension() {
        return declension;
    }

    public void setDeclension(String declension) {
        this.declension = declension;
    }

    public String getEnglishMeanings() {
        return EnglishMeanings;
    }

    public void setEnglishMeanings(String EnglishMeanings) {
        this.EnglishMeanings = EnglishMeanings;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Noun{" +
                "id=" + id +
                ", nominative='" + nominative + '\'' +
                ", genitive='" + genitive + '\'' +
                ", gender='" + gender + '\'' +
                ", declension='" + declension + '\'' +
                ", EnglishMeanings='" + EnglishMeanings + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Noun(int id, String nominative, String genitive, String gender, String declension, String EnglishMeanings, String notes) {
        this.id = id;
        this.nominative = nominative;
        this.genitive = genitive;
        this.gender = gender;
        this.declension = declension;
        this.EnglishMeanings = EnglishMeanings;
        this.notes = notes;
    }

    public Noun() {

    }
}
