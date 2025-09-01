import java.io.*;
import java.net.*;

public class client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");

            // Reader thread (receive from server)
            Thread readThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.equalsIgnoreCase("exit")) {
                            System.out.println("Server ended the chat.");
                            break;
                        }
                        System.out.println("Server: " + msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Writer thread (send to server)
            Thread writeThread = new Thread(() -> {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                    String msg;
                    while (true) {
                        msg = console.readLine();
                        out.println(msg);
                        if (msg.equalsIgnoreCase("exit")) {
                            System.out.println("You ended the chat.");
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            readThread.start();
            writeThread.start();

            // Wait for both threads to finish before closing the socket
            readThread.join();
            writeThread.join();

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
