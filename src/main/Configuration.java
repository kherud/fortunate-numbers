package main;

public enum Configuration
{
    instance;

    int numberOfCores = Runtime.getRuntime().availableProcessors();

    boolean persistData = true;
    boolean isDebug = false;

    String userDirectory = System.getProperty("user.dir");
    String fileSeparator = System.getProperty("file.separator");

    String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    String databaseFile = dataDirectory + "fortunate_numbers.db";
}
