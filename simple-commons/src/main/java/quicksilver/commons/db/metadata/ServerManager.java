package quicksilver.commons.db.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerManager {

    private static ArrayList<DatabaseServer> serverList = new ArrayList<DatabaseServer>();
    private static HashMap<String, DatabaseServer> serverKeyMap = new HashMap<String, DatabaseServer>();

    public static void registerServer(DatabaseServer server) {

        serverList.add(server);
        serverKeyMap.put(server.getServerName(), server);

    }

    public static List<DatabaseServer> getServers() {
        return serverList;
    }

    public static DatabaseServer getServer(String name) {
        return serverKeyMap.get(name);
    }

}
