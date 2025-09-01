import java.io.*;
import java.net.*;

public class client
{
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost",5000);
            
            PrintWriter out=new PrintWriter(s.getOutputStream(), true);
            
            out.println("hello server message recevied");
            
            BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));

            String message=in.readLine();

            System.out.println("client:"+message);


            s.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}