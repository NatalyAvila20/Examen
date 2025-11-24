package com.platinum.cta;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {

    private Connection con;

    public TransaccionDAO() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener conexión en TransaccionDAO", e);
        }
    }

    /**
     * Registra una transacción en la tabla transaccion.
     * Inserta fecha automáticamente usando CURRENT_TIMESTAMP.
     */
    public boolean registrar(Transaccion t) {
        String sql = "INSERT INTO transaccion (rutcliente, rutdueño, idcuenta, montotransferencia, cuentatransferencia, tipocuenta, fecha) "
                   + "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getRutCliente());
            ps.setString(2, t.getRutDueno());
            ps.setInt(3, t.getIdCuenta());
            ps.setBigDecimal(4, t.getMontoTransferencia());
            ps.setString(5, t.getCuentaTransferencia());
            ps.setString(6, t.getTipoCuenta());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error registrar transaccion: " + e.getMessage());
            return false;
        }
    }

    /**
     * Opcional: listar transacciones por rutCliente
     */
    public List<Transaccion> listarPorRutCliente(String rutCliente) {
        List<Transaccion> lista = new ArrayList<>();
        String sql = "SELECT id, rutcliente, rutdueño, idcuenta, montotransferencia, cuentatransferencia, tipocuenta, fecha "
                   + "FROM transaccion WHERE rutcliente = ? ORDER BY fecha DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rutCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaccion t = new Transaccion();
                    t.setId(rs.getInt("id"));
                    t.setRutCliente(rs.getString("rutcliente"));
                    t.setRutDueno(rs.getString("rutdueño"));
                    t.setIdCuenta(rs.getInt("idcuenta"));
                    t.setMontoTransferencia(rs.getBigDecimal("montotransferencia"));
                    t.setCuentaTransferencia(rs.getString("cuentatransferencia"));
                    t.setTipoCuenta(rs.getString("tipocuenta"));
                    t.setFecha(rs.getTimestamp("fecha"));
                    lista.add(t);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error listarPorRutCliente: " + e.getMessage());
        }
        return lista;
    }
}
