package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.DriverManager;

public class DataBaseHelper {
    private static QueryRunner runner = new QueryRunner();

    private DataBaseHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //для запуска СУБД PostrgeSQL убрать комментарий со строчки 23 и добавть на строчку 24
    //private static String url = "jdbc:postgresql://localhost:5432/data";
    private static String url = "jdbc:mysql://localhost:3306/data";
    private static String user = "app";
    private static String password = "pass";

    @SneakyThrows
    public static String getPayInformation() {
        var runner = new QueryRunner();
        var payInfo = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, payInfo, new ScalarHandler<String>());
        }
    }

    @SneakyThrows
    public static String getCreditReqInformation() {
        var runner = new QueryRunner();
        var creditInfo = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, creditInfo, new ScalarHandler<String>());
        }
    }

    @SneakyThrows
    public static void databaseCleanUp() {
        Connection conn = getConn();
        runner.execute(conn, "DELETE FROM credit_request_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM order_entity");
    }
}