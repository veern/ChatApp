import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedWriter buffWriter;
    private BufferedReader buffReader;
    private InputStreamReader inputStreamReader;
    private OutputStreamWriter outputStreamWriter;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connect(1234);
    }

    public void connect(int port) throws IOException {
        String msgToSend;
        String userName = null;

        try {
            socket = new Socket("localhost", port);
            System.out.println("Succesfully connected to a server!");
            Scanner scanner = new Scanner(System.in);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            buffReader = new BufferedReader(inputStreamReader);
            buffWriter = new BufferedWriter(outputStreamWriter);

            do {
                System.out.println("Choose Your username: ");
                userName = scanner.nextLine();
                if (userName.isEmpty())
                    System.out.println("Provide a valid string!");
            } while (userName.isEmpty());

            while (true) {
                System.out.println("Message: ");
                msgToSend = scanner.nextLine();
                buffWriter.write(msgToSend);
                buffWriter.newLine();
                buffWriter.flush();
                if (msgToSend.equalsIgnoreCase("exit"))
                    break;
            }
            scanner.close();
            stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws IOException {
        buffReader.close();
        buffWriter.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        socket.close();
    }
}