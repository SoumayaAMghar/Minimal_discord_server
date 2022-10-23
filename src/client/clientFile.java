package client;

import java.io.*;
import java.net.Socket;

public class clientFile {
    public static void main(String[] args) throws IOException {
        byte[] b = new byte[2002];
        try (Socket sr = new Socket("localhost", 4333)) {
            InputStream is = sr.getInputStream();
            try (FileOutputStream fr = new FileOutputStream("C:\\teeeeeeessst.txt")) {
                is.read(b, 0, b.length);
                OutputStream os = sr.getOutputStream();
                fr.write(b, 0, b.length);
            }
        }
    }
}
