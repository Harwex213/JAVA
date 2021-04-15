package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Server extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String user;
    public static List<Server> jabbers = new ArrayList<>();

    public Server(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                .getOutputStream())), true);
        start();

    }

    public void run() {
        try {
            out.println("Connected");
            out.println("Enter name user");
            user = in.readLine();
            try {

                while (true) {
                    String str = in.readLine();
                    for (Server s : jabbers) {
                        s.out.println(user + ": " + str);
                        //System.out.println();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
public class ServerThread {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = s.accept();
                System.out.println("Connected new user");

                try {
                    Server server = new Server(socket);
                    Server.jabbers.add(server);
                }
                catch (IOException e) {
                    socket.close();
                }
            }
        }
        finally {
            s.close();
        }
    }
}


