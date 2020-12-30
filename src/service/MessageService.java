package service;

import com.google.gson.Gson;
import data.Message;
import main.Main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageService {
    public static String reg(String serverAddress, String myAddress, int myPort, String login) {
        Message m = new Message(login, "", "reg "+myAddress+' '+myPort);
        try (Socket s = new Socket(serverAddress, Main.SERVER_PORT)) {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(new Gson().toJson(m));
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            String answer = (String) ois.readObject();
            ois.close();
            oos.close();
            return answer;
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public static void startServerToListen (int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try(ServerSocket server = new ServerSocket(port)) {
                    while (true) {
                        Socket s = server.accept();
                        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                        Message m = new Gson().fromJson((String)ois.readObject(), Message.class);
                        ois.close();
                        s.close();
                        System.out.println("From: "+m.getNameFrom()+"; "+(m.getNameTo().isEmpty()?"":"To: "+m.getNameTo()+"; ")+"Message: "+m.getMessage());
                    }
                } catch (Exception ex) {
                    System.out.println("Error server;");
                }
            }
        }).start();
    }
}
