import java.io.Serializable;

public class Request implements Serializable {
    private String code;
    private Doctors doctors;
    private Patient patient;

    public Request() {}

    public Request(String code, Doctors doctors) {
        this.code = code;
        this.doctors = doctors;
    }

    public Request(String code, Patient patient) {
        this.code = code;
        this.patient = patient;
    }

    public Request(String code, Doctors doctors, Patient patient) {
        this.code = code;
        this.doctors = doctors;
        this.patient = patient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Request" +
                "\n|code = '" + code + '\'' +
                "| doctors = " + doctors +
                "| patient = " + patient +
                '|';
    }
}
