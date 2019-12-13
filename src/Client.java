import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.PasswordAuthentication;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Database database;
    public static Scanner scanner;
    public static void main(String[] args){
        scanner=new Scanner(System.in);

        Socket socket = null;
        try {
            socket = new Socket("localhost", 1234);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while(true) {
                showMenu();
                int ch = scanner.nextInt();

                if(ch == 1){
                    System.out.println("Enter username: ");
                    String username = scanner.next();
                    System.out.println("Enter password");
                    String password = scanner.next();


                    if(username.equals("admin") & password.endsWith("1234")){
                        adminMenu();
                        int cho = scanner.nextInt();
                        if(cho == 1){
                            Reply rep;
                            System.out.println("Write the first name: ");
                            String fname = scanner.next();
                            System.out.println("Write the last name: ");
                            String lname = scanner.next();
                            System.out.println("Write the otchestvo: ");
                            String otch = scanner.next();

                            oos.writeObject(new Request("view_departments"));
                            rep = (Reply)ois.readObject();
                            System.out.println(database.getDepartments());

//                            database.getDepartments().forEach(department -> System.out.println(department.toString()));
                            System.out.println("Choose the department id:");
                            int dpname = scanner.nextInt();

                            System.out.println("Write the qualification: ");
                            scanner.nextLine();
                            String qual = scanner.nextLine();
                            System.out.println("Write the salary: ");
                            int salary = scanner.nextInt();
                            System.out.println("Write the age:");
                            int age = scanner.nextInt();

                            Doctors doc = new Doctors(null,fname,lname,otch, dpname,qual,salary,age);
                            oos.writeObject(new Request("ADD_Doc", doc));

                            rep = (Reply)ois.readObject();
                            System.out.println(rep.getCode());
                        }
                        if(cho == 2){
                            oos.writeObject(new Request("LIST_DOC"));
                            Reply rep = (Reply)ois.readObject();

                            for(Doctors doc : rep.getDocs())
                                System.out.println(doc);
                        }
                    }
                }
                if(ch == 2){
                    userVxod();
                    int cho = scanner.nextInt();
                    if(cho == 1){

                    }
                }
                if(ch == 0){
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        System.out.println("6. Remove zapis");
        System.out.println("7. List of departments");
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

    }
}
