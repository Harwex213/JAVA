package src;

import Server.ServerThread;
import getData.TestIP;
import getData.parsingHTML;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.System.in;


public class Main {

    private static Socket socket = null;
    public static void main(String[] args) throws IOException {

        TestIP ip = new TestIP();
        ip.getIP("vk.com");
        ip.getIP("localhost");
        parsingHTML parsingHTML = new parsingHTML();
        parsingHTML.parse("http://" + "htmlbook.ru/");

    }
}




