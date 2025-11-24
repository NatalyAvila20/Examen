package com.platinum.cta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/cuentas_clientes";
    private static final String USER = "postgres";
    private static final String PASSWORD = "nata123";

    public static Connection getConnection() throws SQLException {
        try {
            // 🔥 Cargar driver manualmente (evita el error "No suitable driver found")
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✔ Conexión a PostgreSQL exitosa.");
            return conn;

        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: Driver PostgreSQL NO encontrado en WEB-INF/lib");
            e.printStackTrace();
            throw new SQLException("No se encontró el Driver PostgreSQL", e);

        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a PostgreSQL: " + e.getMessage());
            throw e;
        }
    }
}
