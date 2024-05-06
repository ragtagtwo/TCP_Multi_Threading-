import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_IP = "127.0.0.1"; // Loopback address for local testing
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server.");

            // Set up streams for communication
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            // Read input from user and send to server
            System.out.print("Enter a string to reverse (type 'exit' to quit): ");
            String userInput;
            while ((userInput = userInputReader.readLine()) != null) {
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                // Send the user input to the server
                writer.println(userInput);

                // Receive the reversed string from the server and display it
                String reversedString = reader.readLine();
                System.out.println("Reversed string from server: " + reversedString);

                System.out.print("Enter another string to reverse (type 'exit' to quit): ");
            }

            // Close the resources
            reader.close();
            writer.close();
            userInputReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
