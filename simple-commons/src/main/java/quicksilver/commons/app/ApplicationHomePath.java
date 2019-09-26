package quicksilver.commons.app;

import java.io.File;

public class ApplicationHomePath {

    private String appFolderName;

    private File applicationHomePath;
    private File configPath;
    private File logPath;
    private File dataPath;

    public ApplicationHomePath(String appFolderName) {

        String userHome = System.getProperty("user.home");
        this.appFolderName = appFolderName;

        applicationHomePath = new File(userHome, appFolderName);
        configPath = new File(applicationHomePath, "config");
        logPath = new File(applicationHomePath, "logs");
        dataPath = new File(applicationHomePath, "data");

        applicationHomePath.mkdirs();
        configPath.mkdirs();
        logPath.mkdirs();
        dataPath.mkdirs();

    }

    public File getApplicationHomePath() {
        return applicationHomePath;
    }

    public File getConfigPath() {
        return configPath;
    }

    public File getLogPath() {
        return logPath;
    }

    public File getDataPath() {
        return dataPath;
    }

    public String getAppFolderName() {
        return appFolderName;
    }

}
