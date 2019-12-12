public class Doctors extends Person {
    private Long id;
    private String qualification;
    private int department_id;
    private int salary;
    private int age;

    public Doctors() {}

    public Doctors(Long id, String first_name, String last_name, String otchestvo, int department_id, String qualification, int salary, int age) {
        super(first_name, last_name, otchestvo);
        this.id = id;
        this.department_id = department_id;
        this.qualification = qualification;
        this.salary = salary;
        this.age = age;
    }

    public Doctors(Long id, String first_name, String last_name, String otchestvo, String qualification) {
        super(first_name, last_name, otchestvo);
        this.id = id;
        this.qualification = qualification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String toStringAdmin() {
        return "\n| id = " + id +
                "| fullname = " + getFirst_name() + " " + getLast_name() + " "+ getOtchestvo() +
                "| qualification='" + qualification + '\'' +
                "| salary=" + salary +
                "| age=" + age +
                '|';
    }

    public String toStringPatient() {
        return "\n| id = " + id +
                "| fullname = " + getFirst_name() + " " + getOtchestvo() +
                "| qualification='" + qualification + '\'' +
                "| age=" + age +
                '|';
    }
}
