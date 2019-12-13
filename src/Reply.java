import java.io.Serializable;
import java.util.ArrayList;

public class Reply implements Serializable {
    private String code;
    private ArrayList<Person> pers = null;
    private ArrayList<Doctors> docs = null;
    private ArrayList<Patient> pat = null;
    private ArrayList<Department> dep = null;


    public void addPatient(Patient patient) {
        pers.add(patient);
    }

    public void addDoctor(Doctors doc) {
        pers.add(doc);
    }

    public void listDep(Department deps){
        dep.add(deps);
    }

//    public Reply(String code, ArrayList<Person> pers) {
//        this.code = code;
//        this.pers = pers;
//    }

    public Reply(String code, ArrayList<Doctors> docs) {
        this.code = code;
        this.docs = docs;
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

    public ArrayList<Doctors> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<Doctors> docs) {
        this.docs = docs;
    }

    public ArrayList<Patient> getPat() {
        return pat;
    }

    public void setPat(ArrayList<Patient> pat) {
        this.pat = pat;
    }

    public ArrayList<Department> getDep() {
        return dep;
    }

    public void setDep(ArrayList<Department> dep) {
        this.dep = dep;
    }

    public String toStringDocs() {
        return "|code='" + code + '\'' +
                "| docs=" + docs +
                '|';
    }

    public String toStringPatient() {
        return "|code='" + code + '\'' +
                "| pat=" + pat +
                '|';
    }


    public String toStringDepartment() {
        return "Reply{" +
                "code='" + code + '\'' +
                ", dep=" + dep +
                '}';
    }
}
