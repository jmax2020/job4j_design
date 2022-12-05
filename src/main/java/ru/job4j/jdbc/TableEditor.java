package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception  {
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String driverClass = properties.getProperty("jdbc.driver");
        Class.forName(driverClass);
        connection = DriverManager.getConnection(url, username, password);
    }

    private void runStatement(String query) throws SQLException {
        try (Statement stat = connection.createStatement()) {
            stat.execute(query);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sqlQuery = String.format(
            "create table if not exists %s(%s);",
            tableName,
            "id serial primary key"
        );
        runStatement(sqlQuery);
    }

    public void dropTable(String tableName) throws SQLException {
        String sqlQuery = String.format(
            "drop table if exists %s;",
            tableName);
        runStatement(sqlQuery);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sqlQuery = String.format(
            "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type);
        runStatement(sqlQuery);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sqlQuery = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName);
        runStatement(sqlQuery);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sqlQuery = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName);
        runStatement(sqlQuery);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor  tabEd = new TableEditor(config)) {
            tabEd.createTable("testTable");
            System.out.println(tabEd.getTableScheme("testTable"));

            tabEd.addColumn("testTable", "newColumn", "int");
            System.out.println(tabEd.getTableScheme("testTable"));

            tabEd.renameColumn("testTable", "newColumn", "oldColumn");
            System.out.println(tabEd.getTableScheme("testTable"));

            tabEd.dropColumn("testTable", "oldColumn");
            System.out.println(tabEd.getTableScheme("testTable"));

            tabEd.dropTable("testTable");
        }
    }
}