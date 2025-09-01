import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try {
            // Start server at port 6000
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Server started, waiting for client...");

            // Accept client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected, receiving file...");

            // Create stream to read from client
            InputStream in = socket.getInputStream();

            // Save received file as "received.txt"
            FileOutputStream fos = new FileOutputStream("1.txt");

            byte[] buffer = new byte[4096];  // 4KB buffer
            int bytesRead;

            // Read from client & write into file
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully!");

            // Close resources
            fos.close();
            in.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
