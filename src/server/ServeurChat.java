package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurChat extends Thread {
    private int nombreClient=0;
    private ArrayList<Conversation> clients = new ArrayList<>();

    public static void main(String[] args){
        new ServeurChat().start();
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Demarrage du serveur....");
            while (true){
                Socket socketClient= serverSocket.accept();
                ++nombreClient;
                Conversation conversation =new Conversation(socketClient, nombreClient);
                clients.add(conversation);
                conversation.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class Conversation extends Thread{
        protected Socket socketClient;
        protected int numeroClient;
        public Conversation(Socket socketClient, int num){
            this.socketClient = socketClient;
            this.numeroClient = num;
        }
        public void broadcastMessage(String message, Socket socket, int numClient ) throws IOException {
            for (Conversation client: clients){
                if (client.socketClient != socket){
                    if (client.numeroClient == numClient || numClient== -1){
                        PrintWriter printWriter= new PrintWriter(client.socketClient.getOutputStream(),true);
                        printWriter.println(message);
                    }
                }
            }
        }
        @Override
        public void run() {
            try {
                InputStream is = socketClient.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                OutputStream os = socketClient.getOutputStream();
                PrintWriter pw = new PrintWriter(os,true);
                String IP = socketClient.getRemoteSocketAddress().toString();
                System.out.println("Connexion du client numero "+ numeroClient + "IP = "+ IP);
                pw.println("Bienvenue vous etes le client numero "+numeroClient);

                while (true){
                    String req = br.readLine();
                    if (req.contains("=>")){
                        String[] requestParams = req.split("=>");
                        if(requestParams.length==2){
                            String message = requestParams[1];
                            int numeroClient = Integer.parseInt(requestParams[0]);
                            broadcastMessage(message, socketClient,numeroClient);
                        }
                    }else {
                        broadcastMessage(req, socketClient, -1);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
