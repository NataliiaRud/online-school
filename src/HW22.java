import utility.LogLevel;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class HW22 {
    private static final String LOG_SETTINGS_FILE_NAME="logging.properties";

    public static void execute() throws IOException, InterruptedException {
        File loggingProperties = new File(LOG_SETTINGS_FILE_NAME);

        // create settings file if it does not exist
        if (!loggingProperties.exists()) {
            FileOutputStream fos = new FileOutputStream(LOG_SETTINGS_FILE_NAME);
            fos.write("level=DEBUG".getBytes());
            fos.close();
        }

        // reading current log level
        BufferedReader reader = new BufferedReader(new FileReader(LOG_SETTINGS_FILE_NAME));
        String line = reader.readLine();
        reader.close();
        String[] items = line.split("=");

        // reading current level
        final LogLevel[] logLevel = new LogLevel[] {LogLevel.DEBUG};
        if ("ERROR".equals(items[1]) || "WARNING".equals(items[1]) || "INFO".equals(items[1])) {
            logLevel[0] = LogLevel.valueOf(items[1]);
        }
        System.out.println("Initial logLevel = " + logLevel[0]);

        Scanner scanner = new Scanner(System.in);


        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(".");
        path.register(watchService, ENTRY_MODIFY);

        Thread thread = new Thread() {
            @Override
            public void run() {
                boolean poll = true;
                while (poll) {
                    WatchKey key = null;
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
                        BufferedReader reader = new BufferedReader(new FileReader(LOG_SETTINGS_FILE_NAME));
                        String line = reader.readLine();
                        reader.close();
                        String[] items = line.split("=");
                        if ("ERROR".equals(items[1]) || "WARNING".equals(items[1])
                                || "INFO".equals(items[1]) || "DEBUG".equals(items[1])) {
                            logLevel[0] = LogLevel.valueOf(items[1]);

                            System.out.println("logLevel changed to " + logLevel[0]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        do {
            System.out.println("Using logLevel: " + logLevel[0]);

            Thread.sleep(10 * 1000);

            System.out.println("Do you want to continue? yes/no");
        } while ("yes".equals(scanner.next()));
    }
}
