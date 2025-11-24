package com.platinum.cta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("❌ No se pudo conectar a la base de datos", e);
        }
    }

    // LISTAR USUARIOS
    public List<User> listar() {
        List<User> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY id";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("nombreusuario"),
                        rs.getString("password")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    // REGISTRAR USUARIO
    public boolean registrar(User u) {
        String sql = "INSERT INTO usuario (nombreusuario, password) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getPassword());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar usuario: " + e.getMessage());
        }
        return false;
    }

    // BUSCAR POR ID
    public User buscar(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        User u = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new User(
                        rs.getInt("id"),
                        rs.getString("nombreusuario"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar usuario: " + e.getMessage());
        }

        return u;
    }

    // ACTUALIZAR USUARIO
    public boolean actualizar(User u) {
        String sql = "UPDATE usuario SET nombreusuario=?, password=? WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar usuario: " + e.getMessage());
        }

        return false;
    }

    // ELIMINAR USUARIO
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar usuario: " + e.getMessage());
        }

        return false;
    }
}
