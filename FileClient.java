import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        try {
            // Connect to server
            Socket socket = new Socket("localhost", 6000);
            System.out.println("Connected to server, sending file...");

            // Open file to send
            FileInputStream fis = new FileInputStream("1.txt");

            // Output stream to server
            OutputStream out = socket.getOutputStream();

            byte[] buffer = new byte[4096];  // 4KB buffer
            int bytesRead;

            // Read file & write into socket
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully!");

            // Close resources
            fis.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
