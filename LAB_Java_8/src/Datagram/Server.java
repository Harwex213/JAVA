package Datagram;
import java.net.*;
import java.util.HashSet;

public class Server {

    private static HashSet<Integer> portSet = new HashSet<Integer>();

    public static void main(String args[]) throws Exception {

        int serverport = 7777;

        if (args.length < 1) {
            System.out.println("Usage: UDPServer " + "Now using Port# = " + serverport);
        }

        else {
            serverport = Integer.valueOf(args[0]).intValue();
            System.out.println("Usage: UDPServer " + "Now using Port# = " + serverport);
        }

        // Open a new datagram socket on the specified port
        DatagramSocket udpServerSocket = new DatagramSocket(serverport);

        System.out.println("Server started...\n");

        while(true)
        {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            udpServerSocket.receive(receivePacket);
            String clientMessage = (new String(receivePacket.getData())).trim();
            System.out.println("Client Connected - Socket Address: " + receivePacket.getSocketAddress());
            System.out.println("Client message: \"" + clientMessage + "\"");
            InetAddress clientIP = receivePacket.getAddress();
            System.out.println("Client IP Address & Hostname: " + clientIP + ", " + clientIP.getHostName() + "\n");
            int clientport = receivePacket.getPort();

            String returnMessage = clientMessage.toUpperCase();

            // Create an empty buffer/array of bytes to send back
            byte[] sendData  = new byte[1024];

            // Assign the message to the send buffer
            sendData = returnMessage.getBytes();

            for(Integer port : portSet)
            {
                if(port != clientport)
                {
                    // Create a DatagramPacket to send, using the buffer, the clients IP address, and the clients port
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientport);

                    // Send the echoed message
                    udpServerSocket.send(sendPacket);
                }
            }
        }
    }
}