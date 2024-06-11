import java.io.*;
import java.net.*;

public class Client extends Thread{
    static PrintWriter printWriter;
    static BufferedReader bufferedReader;
    public Client(PrintWriter pw, BufferedReader br) {
        printWriter = pw;
        bufferedReader = br;
    }

    public void run() {
        String line;
        try {
                while ((line = bufferedReader.readLine()) != null) {
                    printWriter.println(line);
                    printWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.print("Enter your name: ");
            String name = bufferedReader.readLine();
            printWriter.println(name);
            printWriter.flush();

            Client client = new Client(printWriter , bufferedReader);

            Thread messageThread = new Thread(client);
            messageThread.start();

            String response;
            while ((response = reader.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}