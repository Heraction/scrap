package heraction;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.ServerSocket;

public class Main2 {
    public static void main(String[] args) throws IOException {
        try (var server = new ServerSocket(9999)) {
            while (true) {
                try (
                        var client = server.accept();
                        var is = new PushbackInputStream(client.getInputStream(), 4);
                ) {
                    var buffer = new byte[4];
                    is.read(buffer);
                    if ("stop".equalsIgnoreCase(new String(buffer))) {
                        System.out.println("サーバー止めるよ");
                        break;
                    } else {
                        is.unread(buffer);
                    }

                    String message = new String(is.readAllBytes());
                    System.out.println(message);
                }
            }
        }
    }
}
