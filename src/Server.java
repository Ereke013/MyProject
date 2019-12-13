
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static void main(String[] args){
        //Scanner scanner = new Scanner(System.in);
        ArrayList<Doctors> doc = new ArrayList<>();
        ArrayList<Patient> ptn = new ArrayList<>();
        try {
            ServerSocket ss = new ServerSocket(1234);

            while(true){
                System.out.println("Waiting for client....");
                Socket socket = ss.accept();
                System.out.println("Client connected");

                ClientHandler ch =new ClientHandler(socket,doc,ptn);
                ch.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
