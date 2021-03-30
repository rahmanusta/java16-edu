package com.kodedu.socket;

import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static java.net.StandardProtocolFamily.UNIX;

public class UnixSocketServer {

    public static void main(String[] args) throws Exception {
        var address = UnixDomainSocketAddress.of("/Users/usta/socketFile");
        Files.deleteIfExists(address.getPath());
        try (var serverChannel = ServerSocketChannel.open(UNIX)) {
            serverChannel.bind(address);
            while (true){
                try (var clientChannel = serverChannel.accept()) {
                    ByteBuffer buf = ByteBuffer.allocate(64);
                    clientChannel.read(buf);
                    buf.flip();
                    System.out.println(StandardCharsets.UTF_8.decode(buf));
                }
                Thread.sleep(300);
            }
        } finally {
            Files.deleteIfExists(address.getPath());
        }
    }


}
