import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Database {
    private Connection conn;

    public Scanner scanner = new Scanner(System.in);
    public PreparedStatement preparedStatement;
    public ResultSet rs;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Doctors> getDoctors() {
        ArrayList<Doctors> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctors ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String myname = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String otchestvo = rs.getString("otchestvo");
                int department_id = rs.getInt("department_id");
                String qualification = rs.getString("qualification");
                int salary = rs.getInt("salary");
                int age = rs.getInt("age");

                list.add(new Doctors(id, myname, last_name, otchestvo,department_id , qualification, salary, age));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Person> getPatients() {
        ArrayList<Person> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String myname = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String otchestvo = rs.getString("otchestvo");
                Date bday = rs.getDate("bday");
                String street = rs.getString("street");
                int house = rs.getInt("house");
                int apartment = rs.getInt("apartment");
                String username = rs.getString("username");
                String password = rs.getString("password");

                list.add(new Patient(id, myname, last_name, otchestvo, bday, street, house, apartment, username, password));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addDoctor(Doctors doctors) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO doctors(id,first_name, last_name,otchestvo, qualification, salary, age) VALUES(NULL, ? ,?, ?, ?, ?, ?)");
            ps.setString(1, doctors.getFirst_name());
            ps.setString(2, doctors.getLast_name());
            ps.setString(3, doctors.getOtchestvo());
            ps.setString(4, doctors.getQualification());
            ps.setInt(5, doctors.getSalary());
            ps.setInt(6, doctors.getAge());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLogin(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO login(id,name, username, password) VALUES(NULL, ? ,?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPatient(Patient patient) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO patient(id,first_name, last_name,otchestvo, bday, street, house, apartment, username, password) VALUES(NULL, ? ,?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, patient.getFirst_name());
            ps.setString(2, patient.getLast_name());
            ps.setString(3, patient.getOtchestvo());
            ps.setDate(4, new java.sql.Date(patient.getBday().getTime()));
            ps.setString(5, patient.getStreet());
            ps.setInt(6, patient.getHouse());
            ps.setInt(7, patient.getApartment());
            ps.setString(8, patient.getUsername());
            ps.setString(9, patient.getPassword());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDoctor(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE  FROM doctors WHERE id = ?");
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patient getPatientByAuth(String username, String password) {
        String checkUser = "SELECT * FROM patient WHERE username = \"" + username + "\" AND password = \"" + password + "\"";
        Patient patient;
        try {
            preparedStatement = conn.prepareStatement(checkUser);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String myname = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String otchestvo = rs.getString("otchestvo");
                Date bday = rs.getDate("bday");

                String street = rs.getString("street");
                int house = rs.getInt("house");
                int apartment = rs.getInt("apartment");
                username = rs.getString("username");
                password = rs.getString("password");
                patient = new Patient(id, myname, last_name, otchestvo, bday, street, house, apartment, username, password);
                System.out.println("\nWelcome " + myname);
                return patient;
            }
            preparedStatement.close();
            return null;
        } catch (Exception ex) {
            ex.getLocalizedMessage();
            return null;
        }
    }

    public void removeZapisov(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE  FROM records WHERE id = ?");
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NewRecords> getOrderList() {
        ArrayList<NewRecords> list = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM RECORDS");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                int patient_id = rs.getInt("patient_id");
                int doctor_id = rs.getInt("doctor_id");
                Date date = rs.getDate("date");

                Patient patient = getPatient((long) patient_id);
                Doctors doctor = getDoctor(doctor_id);
                list.add(new NewRecords(id,
                        patient.getFirst_name() + " " + patient.getLast_name(),
                        doctor.getFirst_name() + " " + doctor.getOtchestvo(),
                        doctor.getQualification(),
                        date));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Patient getPatient(Long patientId) {
        Patient patient = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM PATIENT WHERE id = " + patientId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String patientFirstName = rs.getString("first_name");
                String patientLastName = rs.getString("last_name");
                String patientFatherName = rs.getString("otchestvo");
                // PatientInfo id
                patient = new Patient(patientFirstName, patientLastName, patientFatherName, patientId);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public void updatePassword(String oldpassword, String newPassword, Long patientId) {
        String ss = "UPDATE patient SET password =  \"" + newPassword + "\" WHERE password = \"" + oldpassword + "\" AND " +
                "id = \"" + patientId + "\" ";
        try {
            PreparedStatement statement = conn.prepareStatement(ss);
//            statement.setString(1, password);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addDepartment(Department dp) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO department(id,department_name) VALUES(NULL ,?)");
            ps.setString(1, dp.getDepartment_name());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Records> getPatientRecords(Long patientId) {
        ArrayList<Records> list = new ArrayList<>();
        String ss = "SELECT * from `records` WHERE patient_id = \"" + patientId + "\" ";
        try {
            PreparedStatement statement = conn.prepareStatement(ss);
            rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                int patient_id = rs.getInt("patient_id");
                int doctor_id = rs.getInt("doctor_id");
                Date date = rs.getDate("date");

                list.add(new Records(id, patient_id, doctor_id, date));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Department> getDepartments() {
        ArrayList<Department> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM department ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String dep = rs.getString("department_name");


                list.add(new Department(id, dep));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Doctors> getDoctorsByDepartment(int departmentId) {
        ArrayList<Doctors> list = new ArrayList<>();
        //TODO return doctors
        String s = "SELECT * FROM `doctors` WHERE `department_id` = " + departmentId;
        try {
        PreparedStatement statement = conn.prepareStatement(s);

            rs = statement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String doctorFirstName = rs.getString("first_name");
                String doctorLastName = rs.getString("last_name");
                String doctorFatherName = rs.getString("otchestvo");
                String doctorQualification = rs.getString("qualification");

                list.add(new Doctors(id, doctorFirstName, doctorLastName, doctorFatherName, doctorQualification));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Doctors getDoctor(int doctorId) {
        Doctors doctor = null;
        String ss = "SELECT * from `doctors` WHERE id = \"" + doctorId + "\" ";
        try {
            PreparedStatement statement = conn.prepareStatement(ss);
            rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String doctorFirstName = rs.getString("first_name");
                String doctorLastName = rs.getString("last_name");
                String doctorFatherName = rs.getString("otchestvo");
                String doctorQualification = rs.getString("qualification");

                doctor = new Doctors(id, doctorFirstName, doctorLastName, doctorFatherName, doctorQualification);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public boolean makeRecord(int doctorId, long patientId, Date date) {
        //TODO REQUEST
        String insertRecord =
                "INSERT INTO `records` (`patient_id`, `doctor_id`, `date`) " +
                        "VALUES (" + patientId + ", " + doctorId + ", '" + new java.sql.Date(date.getTime()) + "')";
        try {
            preparedStatement = conn.prepareStatement(insertRecord);
            return preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public Patient getPatientInfo(Long patientId) {
        Patient patient = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM PATIENT WHERE id = " + patientId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String patientFirstName = rs.getString("first_name");
                String patientLastName = rs.getString("last_name");
                String patientFatherName = rs.getString("otchestvo");
                Date bday = rs.getDate("bday");
                String street = rs.getString("street");
                int house = rs.getInt("house");
                int apartment = rs.getInt("apartment");

                // PatientInfo id
                patient = new Patient(patientId, patientFirstName, patientLastName, patientFatherName, bday,street,house,apartment);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }
}
