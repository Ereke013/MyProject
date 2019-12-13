import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static Database database;
    public static Scanner scanner;

    public static void main(String[] args) throws Exception{
        scanner = new Scanner(System.in);
        database = new Database();



        //region comment last add
//        Department doct = new Department(null, "Surgery");
//        Department doct1 = new Department(null, "Therapy");
//        Department doct2 = new Department(null, "Traumatology");
//        Department doct3 = new Department(null, "Psychiatry");
//        Department doct4 = new Department(null, "Dyagnostics");
//
//        database.addDepartment(doct);
//        database.addDepartment(doct1);
//        database.addDepartment(doct2);
//        database.addDepartment(doct3);
//        database.addDepartment(doct4);

        //endregion

        int ind = -1;
        while(ind != 0) {
            showMenu();
            ind = scanner.nextInt();
            switch (ind) {
                case 1:
                    System.out.println("Enter username: ");
                    String login = scanner.next();

                    System.out.println("Enter password: ");
                    String password = scanner.next();

                    if (login.equals("admin") & password.equals("1234")) {
                        System.out.println("\nWELCOME, ADMIN!!!\n");
                        int choose = -1;
                        while(choose != 0) {
                            adminMenu();
                            choose = scanner.nextInt();
                            switch (choose) {
                                case 1:
                                    addDoctor();
                                    System.out.println("---------------" +
                                            "\nSUCCESS");
                                    break;
                                case 2:
                                    System.out.println("-----------------------" +
                                            "\n|    LIST OF DOCTORS   |" +
                                            "\n---------------------");
                                    database.getDoctors().forEach(doctor -> System.out.println(doctor.toStringAdmin()));
                                    break;
                                case 3:
                                    System.out.println("-----------------------" +
                                            "\n|   LIST OF PATIENTS  |" +
                                            "\n---------------------");
                                    System.out.println(database.getPatients());
                                    break;
                                case 4:
                                    ArrayList<NewRecords> records = database.getOrderList();
                                    if(records.size() > 0) {
                                        System.out.println("-----------------------" +
                                                "\n|   LIST OF RECORDS   |" +
                                                "\n-----------------------");
                                        System.out.println(database.getOrderList());
                                    }
                                    else
                                        System.out.println("-----------------------" +
                                                           "\n|   NO RECORDS FOUND   |" +
                                                           "\n-----------------------");
                                    break;
                                case 5:
                                    database.getDoctors().forEach(doctor -> System.out.println(doctor.toStringAdmin()));
                                    System.out.println("0. Exit");
                                    System.out.println("Choose the index: ");
                                    Long ch = scanner.nextLong();
                                    if(ch == 0){
                                        break;
                                    }
                                    database.removeDoctor(ch);
                                    break;

                                case 6:
                                    System.out.println("Departments" +
                                            "\n--------------------------------");
                                    database.getDepartments().forEach(department -> System.out.println((department.toString())));
                                    break;
                            }


                        }
                        } else {
                        System.out.println("LOGIN OR PASSWORD IS WRONG!!!");
                    }
                    break;
                case 2:
                    userVxod();
                    int cho = scanner.nextInt();
                    if(cho == 1){
                        System.out.println("Enter username: ");
                        String logn = scanner.next();
                        System.out.println("Enter password: ");
                        String psw = scanner.next();
                        //database.isLoginSuccess(logn,psw);
                        Patient patient = database.getPatientByAuth(logn,psw);
                        if(patient != null){

                            int chose = -1;
                            while(chose != 0){
                                userMenu();
                                chose = scanner.nextInt();
                                switch (chose){
                                    case 1:
                                        ArrayList<Records> records =  database.getPatientRecords(patient.getId());
                                        for(Records record: records){
                                            Doctors doctor = database.getDoctor(record.getDoctor_id());
                                            System.out.println("| "+doctor.getFirst_name() + " " + doctor.getOtchestvo() + "| " +
                                                    doctor.getQualification() + "| " + record.getDate());
                                        }
                                        break;
                                    case 2:
                                        ArrayList<Doctors> doc = database.getDoctors();
                                        database.getDepartments().forEach(department -> System.out.println(department.toString()));
                                        System.out.println("Choose id: ");
                                        int nums = scanner.nextInt();
                                        if(doc.size() > 0) {
                                            for (Doctors doctor : database.getDoctorsByDepartment(nums)){

                                                System.out.println(doctor.toStringPatient());
                                            }
                                            System.out.println("Enter doctor id: ");
                                            int index = scanner.nextInt();
                                            makeAnAppointment(index, patient.getId());
                                        }
                                        break;

                                    case 3:
                                        System.out.println("Enter old password: ");
                                        String passwords = scanner.next();
                                        System.out.println("Enter new password: ");
                                        String newPasswords=scanner.next();

                                        database.updatePassword(passwords, newPasswords, patient.getId());
                                        break;

                                    case 4:
                                        
                                        break;
                                }
                            }
                        }
                        else{
                            System.out.println("LOGIN OR PASSWORD INCORRECT!");
                        }
                    }
                    if(cho == 2){
                        registration();
                    }

                    break;
            }
        }
    }

    private static void showMenu(){
        System.out.println("1. Admin");
        System.out.println("2. Patient");
        System.out.println("0. Exit");
        System.out.println("Enter choose: ");
    }

    private static void adminMenu(){
        System.out.println("1. Add doctor");
        System.out.println("2. List of doctors");
        System.out.println("3. List of patients");
        System.out.println("4. Zapisy");
        System.out.println("5. Remove doctor");
        System.out.println("6. List of departments");
        System.out.println("0. EXIT");
        System.out.println("Enter index: ");
    }

    private static void userVxod(){
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("0. Exit");
        System.out.println("Enter from: ");
    }

    private static void userMenu(){
        System.out.println("1. List of zapis");
        System.out.println("2. Make an appointment");
        System.out.println("3. Change my password");
        System.out.println("4. Information about me");
        System.out.println("0. EXIT");
        System.out.println("Enter from list: ");
    }

    private static void registration() throws ParseException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the login: ");
        String nlog = scanner.next();
        System.out.println("Write the password");
        String npsw = scanner.next();

        System.out.println("Enter first name: ");
        String fname = scanner.next();
        System.out.println("Enter last name: ");
        String lname = scanner.next();
        System.out.println("Enter  otchestvo: ");
        String otchestvo = scanner.next();
        System.out.println("Enter birthday(for example: 00.00.0000): ");
        String bday = scanner.next();
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(bday);

        String diag = scanner.nextLine();
        System.out.println("Write street which your life: ");
        String street = scanner.next();
        System.out.println("Dom: ");
        int house = scanner.nextInt();
        System.out.println("Kvartira: ");
        int kv = scanner.nextInt();

        Patient patient = new Patient(null, fname, lname, otchestvo, date, diag, street, house, kv, nlog, npsw);

        database.addPatient(patient);

    }

    private static void addDoctor(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the first name: ");
        String fname = scanner.next();
        System.out.println("Write the last name: ");
        String lname = scanner.next();
        System.out.println("Write the otchestvo: ");
        String otch = scanner.next();

        database.getDepartments().forEach(department -> System.out.println(department.toString()));
        System.out.println("Choose the department id:");
        int dpname = scanner.nextInt();

        System.out.println("Write the qualification: ");
        scanner.nextLine();
        String qual = scanner.nextLine();
        System.out.println("Write the salary: ");
        int salary = scanner.nextInt();
        System.out.println("Write the age:");
        int age = scanner.nextInt();

        Database database = new Database();
        Doctors doc = new Doctors(null,fname,lname,otch, dpname,qual,salary,age);
        database.addDoctor(doc);
    }

    private static void makeAnAppointment(int doctorId, long patientId){
        Calendar calendar = Calendar.getInstance();
        ArrayList<Date> timetable = new ArrayList<>();
        for(int i = 1; i <= 7;i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // 1 деген 1 апта алдыга
            Date oneDayFromNow = calendar.getTime();
            timetable.add(oneDayFromNow);
            System.out.println(i + ". " + oneDayFromNow);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter day index: ");
        int index = scanner.nextInt();
        Date selectedDate = timetable.get(index - 1);

        database.makeRecord(doctorId, patientId, selectedDate);
    }

    private static int scanInt(){
        int n;
        try {
            n = scanner.nextInt();
            return n;
        }catch (InputMismatchException ex){
            System.out.println("Enter number: ");
            return -1000;
        }
    }
}
