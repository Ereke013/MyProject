import java.util.Calendar;

public class Person{
    private String first_name;
    private String last_name;
    private String otchestvo;

    public Person() {}

    public Person(String first_name, String last_name, String otchestvo) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.otchestvo = otchestvo;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    @Override
    public String toString() {
        return "Person" +
                "\n| first_name = '" + first_name + '\'' +
                "| last_name = '" + last_name + '\'' +
                "| otchestvo = '" + otchestvo + '\'' +
                '|';
    }
}
