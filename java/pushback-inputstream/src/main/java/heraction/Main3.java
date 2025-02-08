package heraction;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Main3 {
    public static void main(String[] args) throws IOException {
        try (var server = new ServerSocket(9999)) {
            while (true) {
                try (
                        var client = server.accept();
                        var is = new BufferedInputStream(client.getInputStream(), 4);
                ) {
                    is.mark(4);
                    var buffer = new byte[4];
                    is.read(buffer);
                    if ("stop".equalsIgnoreCase(new String(buffer))) {
                        System.out.println("サーバー止めるよ");
                        break;
                    } else {
                        is.reset();
                    }

                    String message = new String(is.readAllBytes());
                    System.out.println(message);
                }
            }
        }
    }
}
