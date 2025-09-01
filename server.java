import java.io.*;
import java.net.*;

public class server
{
    public static void main(String[] args) {
        try{
            ServerSocket ss=new ServerSocket(5000);

            System.out.println("Sever started.Waiting for client...");

            Socket s=ss.accept();

            System.out.println("Client connected");

            BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));

                PrintWriter out=new PrintWriter(s.getOutputStream());

            String message=in.readLine();

                System.out.println("Message from client: " + message);

            out.println("hello client message recevied");
                out.flush();

            s.close();
            ss.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}