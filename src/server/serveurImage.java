package server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class serveurImage {

        public static void main(String[] args) throws Exception {
            ServerSocket serverSocket = new ServerSocket(13085);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            System.out.println("Reading: " + System.currentTimeMillis());

            byte[] sizeAr = new byte[4];
            inputStream.read(sizeAr);
            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

            byte[] imageAr = new byte[size];
            inputStream.read(imageAr);

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));


            ImageIO.write(image, "jpg", new File("D:\\test2.jpg"));

            serverSocket.close();
        }


}
