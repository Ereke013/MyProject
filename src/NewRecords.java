import java.util.Date;

public class NewRecords {
    private long id;
    private String patientFullName;
    private String doctorFullName;
    private String doctorQualification;
    private Date recordDate;

    public NewRecords(long id, String patientFullName, String doctorFullName, String doctorQualification, Date recordDate) {
        this.id = id;
        this.patientFullName = patientFullName;
        this.doctorFullName = doctorFullName;
        this.doctorQualification = doctorQualification;
        this.recordDate = recordDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getDoctorQualification() {
        return doctorQualification;
    }

    public void setDoctorQualification(String doctorQualification) {
        this.doctorQualification = doctorQualification;
    }

    public Date getRecordData() {
        return recordDate;
    }

    public void setRecordData(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "Records\n" +
                "| id = " + id +
                "| patientFullName='" + patientFullName + '\'' +
                "| doctorFullName='" + doctorFullName + '\'' +
                "| doctorQualification='" + doctorQualification + '\'' +
                "| recordDate=" + recordDate +'\n' +
                '|';
    }
}
