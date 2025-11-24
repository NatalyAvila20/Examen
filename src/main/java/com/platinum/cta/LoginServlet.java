package com.platinum.cta;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rut = request.getParameter("rut");

        try (Connection cn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM ejecutivo WHERE rutejecutivo = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, rut);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                request.setAttribute("nombre", rs.getString("nombre"));
                request.setAttribute("departamento", rs.getString("departamento"));

                // REDIRIGIMOS A HOME
                request.getRequestDispatcher("home.jsp").forward(request, response);

            } else {
                response.getWriter().println("<h3>Credenciales incorrectas</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
