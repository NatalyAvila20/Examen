package com.platinum.cta;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/cuentas")
public class CuentaCorrienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            listar(request, response);
            return;
        }

        switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
                break;

            case "editar":
                cargar(request, response);
                break;

            case "eliminar":
                eliminar(request, response);
                break;

            default:
                listar(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "insertar":
                insertar(request, response);
                break;

            case "actualizar":
                actualizar(request, response);
                break;
        }
    }

    // LISTAR
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> lista = new ArrayList<>();

        try (Connection cn = DBConnection.getConnection()) {

            PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM CtaCorrient ORDER BY idCuenta"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("idCuenta"),
                    rs.getString("RutCliente"),
                    rs.getString("monto"),
                    rs.getString("ejecutivo")
                });
            }

            request.setAttribute("listaCuentas", lista);
            request.getRequestDispatcher("cuentas.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    // INSERTAR
    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (Connection cn = DBConnection.getConnection()) {

            PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO CtaCorrient (RutCliente, monto, ejecutivo) VALUES (?, ?, ?)"
            );

            ps.setString(1, request.getParameter("rut"));
            ps.setString(2, request.getParameter("monto"));
            ps.setString(3, request.getParameter("ejecutivo"));

            ps.executeUpdate();
            response.sendRedirect("cuentas");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    // CARGAR PARA EDITAR
    private void cargar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection cn = DBConnection.getConnection()) {

            PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM CtaCorrient WHERE idCuenta = ?"
            );

            ps.setInt(1, Integer.parseInt(request.getParameter("id")));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("idCuenta", rs.getInt("idCuenta"));
                request.setAttribute("rut", rs.getString("RutCliente"));
                request.setAttribute("monto", rs.getString("monto"));
                request.setAttribute("ejecutivo", rs.getString("ejecutivo"));

                request.getRequestDispatcher("editarCuenta.jsp").forward(request, response);
            }

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    // ACTUALIZAR
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (Connection cn = DBConnection.getConnection()) {

            PreparedStatement ps = cn.prepareStatement(
                "UPDATE CtaCorrient SET RutCliente=?, monto=?, ejecutivo=? WHERE idCuenta=?"
            );

            ps.setString(1, request.getParameter("rut"));
            ps.setString(2, request.getParameter("monto"));
            ps.setString(3, request.getParameter("ejecutivo"));
            ps.setInt(4, Integer.parseInt(request.getParameter("idCuenta")));

            ps.executeUpdate();
            response.sendRedirect("cuentas");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    // ELIMINAR
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (Connection cn = DBConnection.getConnection()) {

            PreparedStatement ps = cn.prepareStatement(
                "DELETE FROM CtaCorrient WHERE idCuenta=?"
            );

            ps.setInt(1, Integer.parseInt(request.getParameter("id")));
            ps.executeUpdate();

            response.sendRedirect("cuentas");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
