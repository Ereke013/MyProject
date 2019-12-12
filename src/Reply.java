import java.io.Serializable;
import java.util.ArrayList;

public class Reply implements Serializable {
    private String code;
    private ArrayList<Person> pers = null;

    public void addPatient(Patient patient) {
        pers.add(patient);
    }

    public void addDoctor(Doctors doc) {
        pers.add(doc);
    }

    public Reply(String code, ArrayList<Person> pers) {
        this.code = code;
        this.pers = pers;
    }

    public Reply() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Person> getPers() {
        return pers;
    }

    public void setPers(ArrayList<Person> pers) {
        this.pers = pers;
    }

    @Override
    public String toString() {
        return "Reply" +
                "\n| code = '" + code + '\'' +
                "| pers = " + pers +
                '|';
    }
}
