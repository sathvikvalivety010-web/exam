import java.io.*;
import java.net.*;

public class server1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Reader thread (receive from client)
            Thread readThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.equalsIgnoreCase("exit")) {
                            System.out.println("Client left the chat.");
                            break;
                        }
                        System.out.println("Client: " + msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Writer thread (send to client)
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

            // Wait for threads to finish
            readThread.join();
            writeThread.join();

            // Close resources
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
