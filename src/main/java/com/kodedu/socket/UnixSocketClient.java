package com.kodedu.socket;

import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class UnixSocketClient {

    public static void main(String[] args) throws Exception {
        var address = UnixDomainSocketAddress.of("/Users/usta/socketFile");
        while (true){
            try (var clientChannel = SocketChannel.open(address)) {
                System.out.print("Enter message: ");
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                ByteBuffer buf = ByteBuffer.wrap(line.getBytes());
                clientChannel.write(buf);
            }
        }

    }

}
