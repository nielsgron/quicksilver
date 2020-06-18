package quicksilver.webapp.simpleserver.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class FSWatcher extends Thread {

    private static final Logger LOG = LogManager.getLogger();

    private final WatchService watchService;
    private final Map<WatchKey, Path> watches = new HashMap<>();

    private FSWatcher(Path path) throws IOException {
        LOG.info("Started watching " + path.toAbsolutePath().normalize() + " for autoredeploy");
        watchService = FileSystems.getDefault().newWatchService();
        register(path);
    }

    @Override
    public void run() {
        while (!watches.isEmpty()) {
            try {
                WatchKey key = watchService.take();
                for (WatchEvent event : key.pollEvents()) {
                    LOG.warn("Exiting for redeploy NOW! Got filesystem event: " + event.kind() + " on " + watches.get(key) + "/" + event.context());
                    System.exit(0);
                }
                if (!key.reset()) {
                    watches.remove(key);
                }
            } catch (InterruptedException ex) {
                return;
            }
        }
    }

    private void register(Path path) throws IOException {
        WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.OVERFLOW);
        watches.put(key, path);
        for (File f : path.toFile().listFiles()) {
            if (f.isDirectory()) {
                register(f.toPath());
            }
        }
    }

    static void launch() {
        try {
            new FSWatcher(Paths.get("..")).start();
        } catch (IOException ex) {
            //LOG?
        }
    }

}
