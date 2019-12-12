public class Department {
    private Long id;
    private String department_name;

    public Department(Long id, String department_name) {
        this.id = id;
        this.department_name = department_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    @Override
    public String toString() {
        return  "\n|id = " + id +
                "| department_name = '" + department_name + '\'' +
                '|';
    }
}
