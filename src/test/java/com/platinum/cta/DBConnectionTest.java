package com.platinum.cta;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectionTest {

    private static Connection conn;

    @BeforeAll
    public static void abrirConexion() throws Exception {
        conn = DBConnection.getConnection();
        assertNotNull(conn, "La conexión no debe ser null");
    }

    @AfterAll
    public static void cerrarConexion() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Test
    public void testConexionActiva() throws Exception {
        assertFalse(conn.isClosed(), "La conexión debe estar abierta");
    }

    @Test
    public void testConsultaBasica() throws Exception {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT 1");

        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
    }
}
