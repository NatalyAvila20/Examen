package com.platinum.cta;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            listarClientes(request, response);
            return;
        }

        switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("crearCliente.jsp").forward(request, response);
                break;

            case "editar":
                cargarCliente(request, response);
                break;

            case "eliminar":
                eliminarCliente(request, response);
                break;

            default:
                listarClientes(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "insertar":
                insertarCliente(request, response);
                break;

            case "actualizar":
                actualizarCliente(request, response);
                break;
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> lista = new ArrayList<>();

        try (Connection cn = DBConnection.getConnection()) {
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM cliente ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new String[]{
                        rs.getString("id"),
                        rs.getString("rut"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                });
            }

            request.setAttribute("listaClientes", lista);
            request.getRequestDispatcher("clientes.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (Connection cn = DBConnection.getConnection()) {

            String sql = "INSERT INTO cliente (rut, nombre, apellido, correo, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, request.getParameter("rut"));
            ps.setString(2, request.getParameter("nombre"));
            ps.setString(3, request.getParameter("apellido"));
            ps.setString(4, request.getParameter("correo"));
            ps.setString(5, request.getParameter("telefono"));
            ps.setString(6, request.getParameter("direccion"));

            ps.executeUpdate();
            response.sendRedirect("clientes");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void cargarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection cn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(request.getParameter("id")));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("rut", rs.getString("rut"));
                request.setAttribute("nombre", rs.getString("nombre"));
                request.setAttribute("apellido", rs.getString("apellido"));
                request.setAttribute("correo", rs.getString("correo"));
                request.setAttribute("telefono", rs.getString("telefono"));
                request.setAttribute("direccion", rs.getString("direccion"));

                request.getRequestDispatcher("editarCliente.jsp").forward(request, response);
            }

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (Connection cn = DBConnection.getConnection()) {

            String sql = "UPDATE cliente SET rut=?, nombre=?, apellido=?, correo=?, telefono=?, direccion=? WHERE id=?";
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, request.getParameter("rut"));
            ps.setString(2, request.getParameter("nombre"));
            ps.setString(3, request.getParameter("apellido"));
            ps.setString(4, request.getParameter("correo"));
            ps.setString(5, request.getParameter("telefono"));
            ps.setString(6, request.getParameter("direccion"));
            ps.setInt(7, Integer.parseInt(request.getParameter("id")));

            ps.executeUpdate();
            response.sendRedirect("clientes");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (Connection cn = DBConnection.getConnection()) {

            PreparedStatement ps = cn.prepareStatement("DELETE FROM cliente WHERE id = ?");
            ps.setInt(1, Integer.parseInt(request.getParameter("id")));
            ps.executeUpdate();

            response.sendRedirect("clientes");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
