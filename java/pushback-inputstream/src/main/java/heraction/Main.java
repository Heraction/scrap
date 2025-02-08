package heraction;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        try (var server = new ServerSocket(9999)) {
            while (true) {
                try (
                        var client = server.accept();
                        var is = client.getInputStream();
                ) {
                    String message = new String(is.readAllBytes());
                    System.out.println(message);
                }
            }
        }
    }
}
