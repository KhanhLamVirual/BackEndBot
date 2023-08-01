package com.bot.backend;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

import mindustry.net.Host;
import mindustry.net.NetworkIO;

/**
 * ServerHandler
 */
public class ServerHandler {

    public static void pingHost(String host, int port, Consumer<Host> listener, Consumer<Host> exception) {
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.send(new DatagramPacket(new byte[] { -2, 1 }, 2, InetAddress.getByName(host), port));

            socket.setSoTimeout(2000);

            DatagramPacket packet = new DatagramPacket(new byte[256], 256);

            long start = System.currentTimeMillis();
            socket.receive(packet);

            ByteBuffer buffer = ByteBuffer.wrap(packet.getData());
            listener.accept(readServerData(buffer, host, (int) (System.currentTimeMillis() - start)));
            socket.disconnect();
            socket.close();
        } catch (Exception e) {
            exception.accept(new Host(0, null, host, null, 0, 0, 0, null, null, 0, null, null));
             
        }
    }
    public static Host readServerData(ByteBuffer buffer, String ip, int ping) {
        Host host = NetworkIO.readServerData(ping, ip, buffer);
        host.ping = ping;
        return host;
    }
}
