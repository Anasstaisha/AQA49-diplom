package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseHelper {
    private static QueryRunner runner = new QueryRunner();

    private DataBaseHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(System.getProperty("source"), System.getProperty("username"), System.getProperty("password"));
    }

    @SneakyThrows
    public static String getPayInformation() {
        runner = new QueryRunner();
        String payInfo = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
            return runner.query(getConn(), payInfo, new ScalarHandler<String>());

    }

    @SneakyThrows
    public static String getCreditReqInformation() {
        runner = new QueryRunner();
        String creditInfo = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
            return runner.query(getConn(), creditInfo, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static void databaseCleanUp() {
        Connection conn = getConn();
        runner.execute(conn, "DELETE FROM credit_request_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM order_entity");
    }
}