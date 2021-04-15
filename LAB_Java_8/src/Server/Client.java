package Server;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Client{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner scanner = new Scanner(System.in);
    private String message = "";

    public Client(InetAddress addr) {
        System.out.println("Create connected" );
        try {
            socket = new Socket(addr, ServerThread.PORT);
        }
        catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);
            run();
        }
        catch (IOException | InterruptedException e) {
            try {
                socket.close();
            }
            catch (IOException e2) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void run() throws InterruptedException, IOException {
        try {
            String str = in.readLine();
            Runnable read = ()-> {
                while (true) {
                    try {
                        message = in.readLine();
                        if(message.equals("END"))
                            break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(message);
                }
            };

            Thread reader = new Thread(read,"reader");
            reader.start();

            while (true){
                message = scanner.nextLine();
                this.out.println(message);
                if(message.equals("END"))
                    break;
            }
            reader.interrupt();

        } finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void readMessages() throws IOException {
        String str = in.readLine();
        System.out.println("Client"+str);
    }
}

class MainClient {
    private static InetAddress address;

    public static void main(String[] args) throws IOException{
        System.out.println("Enter IP server");
        Scanner in = new Scanner(System.in);
        new Client(InetAddress.getByName(in.nextLine()));
    }
}