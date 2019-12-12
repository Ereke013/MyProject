import java.util.Date;

public class Patient extends Person {
    private Long id;
    private Date bday;
    private String diagnostic;
    private String street;
    private int house;
    private int apartment;
    private String username;
    private String password;

    public Patient(String first_name, String last_name, String otchestvo, Long id) {
        super(first_name, last_name, otchestvo);
        this.id = id;
    }

    public Patient(Long id, String first_name, String last_name, String otchestvo, Date bday, String diagnostic, String street, int house, int apartment, String username, String password) {
        super(first_name, last_name, otchestvo);
        this.id = id;
        this.bday = bday;
        this.diagnostic = diagnostic;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.username = username;
        this.password = password;
    }

    public Patient(Long id, Date bday, String diagnostic, String street, int house, int apartment, String username, String password) {
        this.id = id;
        this.bday = bday;
        this.diagnostic = diagnostic;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.username = username;
        this.password = password;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "\n| id = " + id +
                "| full name = " + getFirst_name() + " " + getLast_name() + " "+ getOtchestvo() +
                "| bday = " + bday +
                "| diagnostic = '" + diagnostic + '\'' +
                "| street = '" + street + '\'' +
                "| house = " + house +
                "| apartment = " + apartment +
                '|';
    }


}
