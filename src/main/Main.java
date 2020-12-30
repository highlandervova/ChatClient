package main;

import com.google.gson.Gson;
import data.Message;
import service.MessageService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        System.out.println("Enter server ip address:");
        String serverAddress = new Scanner(System.in).nextLine();
        System.out.println("Enter your ip address:");
        String myAddress = new Scanner(System.in).nextLine();
        System.out.println("Enter your port:");
        int myPort = new Scanner(System.in).nextInt();
        System.out.println("Enter your login:");
        String login = new Scanner(System.in).nextLine();
        String regResult = MessageService.reg(serverAddress, myAddress, myPort, login);
        System.out.println("Try to register you: " + regResult);
        MessageService.startServerToListen(myPort);
        while (true) {
            System.out.println("Enter to who:");
            String to = new Scanner(System.in).nextLine();
            System.out.println("Enter message:");
            String message = new Scanner(System.in).nextLine();
            Message m = new Message(login, to, message);
            try (Socket s = new Socket(serverAddress, SERVER_PORT)) {
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject(new Gson().toJson(m));
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                String answer = (String) ois.readObject();
                System.out.println(answer);
                ois.close();
                oos.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong(((");
            }
        }
    }
}
