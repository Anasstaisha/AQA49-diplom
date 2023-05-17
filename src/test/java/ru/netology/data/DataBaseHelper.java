package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.DriverManager;

public class DataBaseHelper {
    private static QueryRunner runner = new QueryRunner();

    private DataBaseHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
    }

    @SneakyThrows
    public static DataGenerator.PaymentEntityInfo getPayInformation() {
        var runner = new QueryRunner();
        var payInfo = "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, payInfo, new BeanHandler<>(DataGenerator.PaymentEntityInfo.class));
        }
    }

    @SneakyThrows
    public static DataGenerator.CreditRequestEntityInfo getCreditReqInformation() {
        var runner = new QueryRunner();
        var creditInfo = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, creditInfo, new BeanHandler<>(DataGenerator.CreditRequestEntityInfo.class));
        }
    }

    @SneakyThrows
    public static DataGenerator.OrderEntityInfo getOrderInformation() {
        var runner = new QueryRunner();
        var orderInfo = "SELECT * FROM order_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, orderInfo, new BeanHandler<>(DataGenerator.OrderEntityInfo.class));
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