public class Hospital {
    private String HospitalName;
    private int year;
    private int rating;

    public Hospital() {}

    public Hospital(String hospitalName, int year, int rating) {
        HospitalName = hospitalName;
        this.year = year;
        this.rating = rating;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "HospitalName='" + HospitalName + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
}
