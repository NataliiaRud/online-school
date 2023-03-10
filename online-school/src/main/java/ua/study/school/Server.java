package ua.study.school;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class Server {
    private static final String BLACKLIST = "blacklist.txt";

    public static void main(String[] args) throws IOException {
        File blacklistFile = new File(BLACKLIST);

        // create settings file if it does not exist
        if (!blacklistFile.exists()) {
            blacklistFile.createNewFile();
        }

        Set<String>[] blackList = new Set[1];
        blackList[0] = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(blacklistFile));
        String line;
        while ((line = reader.readLine()) != null) {
            blackList[0].add(line);
        }
        reader.close();

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(".");
        path.register(watchService, ENTRY_MODIFY);

        Thread thread = new Thread() {
            @Override
            public void run() {
                boolean poll = true;
                while (poll) {
                    WatchKey key;
                    try {
                        key = watchService.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        System.out.println("Event kind : " + event.kind() + " - File : " + event.context());
                    }
                    poll = key.reset();

                    try {
                        Set<String> newBlackList = new HashSet<>();
                        BufferedReader reader = new BufferedReader(new FileReader(blacklistFile));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            newBlackList.add(line);
                        }
                        blackList[0] = newBlackList;
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9898));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            Socket socket = socketChannel.socket();

            OutputStream os = socket.getOutputStream();
            if (blackList[0].contains(socket.getInetAddress().getHostAddress())) {
                os.write("IP in blacklist".getBytes());
            } else {
                os.write("Welcome".getBytes());
            }
            os.close();
        }
    }
}
