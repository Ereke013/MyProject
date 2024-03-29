import java.util.Date;

public class Records {
    private Long id;
    private int patient_id;
    private int doctor_id;
    private Date date;

    public Records(Long id, int patient_id, int doctor_id, Date date) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.date = date;
    }

    public Records() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Records" +
                "\n| id = " + id +
                "| patient_id = " + patient_id +
                "| doctor_id = " + doctor_id +
                "| date = " + date +
                '|';
    }
}
