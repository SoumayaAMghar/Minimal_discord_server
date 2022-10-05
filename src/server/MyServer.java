package server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
public static void main(String[] args) throws IOException {
    ServerSocket ss =new ServerSocket(1234);
   System.out.println("J'attend la connexion...");
    Socket s = ss.accept();
    InputStream is = s.getInputStream();
    OutputStream os = s.getOutputStream();
    System.out.println("J'attend que le client envoie un octet");
    int nb = is.read();
    System.out.println("J'ai recu un nombre "+ nb);
    int res = nb*3;
    System.out.println("J'envoie la reponse "+ res);
    os.write(res);
    s.close();


    /* pour lire une chaine de caractères envoyée par le client
    Socket socket = ss.accept();
    InputStream is = socket.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    System.out.println("J'attend que le client envoie une chaine de caractère");
    String s= br.readLine();
    /* pour envoyer une chaine de caractères au client
    OutputStream os = socket.getOutputStream();
    PrintWriter pw = new PrintWriter(os,true);
    pw.println("chaine de caracteres");


    /* pour sérialiser un objet(envoyer un objet vers le client)
    * On doit créer cette class puis elle va implémenter l'interface Serializable
    * On va instancié un objet de cette class et lui passer
    * comme parametre à la method writeObject()

    OutputStream oss = socket.getOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(oss);
    //oos.writeObject(objname);
    /*
    Pour lire un objet envoyé par le client(désérialisation)

    InputStream iis= socket.getInputStream();
    ObjectInputStream ois = new ObjectInputStream(iis);
    // Voiture v =(Voiture) ois.readObject();*/


}
}
