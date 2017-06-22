package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public enum TaskRecorder {
    instance;

    private Connection connection;
    private String driverName = "jdbc:hsqldb:";
    private String username = "sa";
    private String password = "";

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + Configuration.instance.databaseFile;
            connection = DriverManager.getConnection(databaseURL, username, password);
            if (Configuration.instance.isDebug)
                System.out.println("TaskRecorder - connection : " + connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void init() {
        dropTable();
        createTable();
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void dropTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE fortunate_numbers");
        if (Configuration.instance.isDebug){
            System.out.println("--- dropTable");
            System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());
        }
        update(sqlStringBuilder.toString());
    }

    public void createTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE fortunate_numbers ").append(" ( ");
        sqlStringBuilder.append("id BIGINT NOT NULL").append(",");
        sqlStringBuilder.append("type VARCHAR(63) NOT NULL").append(",");
        sqlStringBuilder.append("n BIGINT NOT NULL").append(",");
        sqlStringBuilder.append("fortunate_number BIGINT NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        update(sqlStringBuilder.toString());
    }

    public String buildSQLStatement(long id, String type, int n, int fortunateNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO fortunate_numbers (id,type,n,fortunate_number) VALUES (");
        stringBuilder.append(id).append(",");
        stringBuilder.append("'").append(type).append("'").append(",");
        stringBuilder.append(n).append(",");
        stringBuilder.append(fortunateNumber);
        stringBuilder.append(")");
        if (Configuration.instance.isDebug) {
            System.out.println(stringBuilder.toString());
        }
        return stringBuilder.toString();
    }

    public void insert(String type, int index, int number) {
        update(buildSQLStatement(System.nanoTime(), type, index, number));
    }

    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
            if (Configuration.instance.isDebug)
                System.out.println("TaskRecorder - isClosed : " + connection.isClosed());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}
