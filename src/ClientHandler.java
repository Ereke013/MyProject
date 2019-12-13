import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private ArrayList<Doctors> list = null;
    private ArrayList<Patient> ptn = null;
    private ArrayList<Department> deps = null;
    public static Database database;


    public ClientHandler(Socket socket, ArrayList<Doctors> list, ArrayList<Patient> ptn){
        this.socket = socket;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.list = list;
        this.ptn = ptn;
    }



    public void run(){
        while(true) {
            try{
                Request req = (Request) ois.readObject();

                if (req.getCode().equals("ADD_Doc")) {
                    database.addDoctor(req.getDoctors());

                    Reply rep = new Reply();
                    rep.setCode("SUCCESS");
                    oos.writeObject(rep);
                }
                else  if(req.getCode().equals("view_departments")){
                    Reply rep = new Reply();

                    for (Department dep : deps)
                        rep.listDep(dep);

                    oos.writeObject(rep);
                }

                else if (req.getCode().equals("LIST_DOC")) {
                    Reply rep = new Reply();
                    for (Doctors doc : list)
                        rep.addDoctor(doc);

                    oos.writeObject(rep);
                } else if (req.getCode().equals("EXIT")) {
                    break;
                }
            }
            catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
