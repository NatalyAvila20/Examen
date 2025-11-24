package com.platinum.cta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    UsuarioDAO dao = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                request.setAttribute("usuarios", dao.listar());
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                break;

            case "crear":
                request.getRequestDispatcher("crearUsuario.jsp").forward(request, response);
                break;

            case "editar":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                User u = dao.buscar(idEdit);
                request.setAttribute("usuario", u);
                request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                break;

            case "eliminar":
                int idDel = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idDel);
                response.sendRedirect("UsuarioServlet?accion=listar");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {

            case "guardar":
                String nombre = request.getParameter("nombreUsuario");
                String pass = request.getParameter("password");

                User nuevo = new User(nombre, pass);
                dao.registrar(nuevo);

                response.sendRedirect("UsuarioServlet?accion=listar");
                break;

            case "actualizar":
                int id = Integer.parseInt(request.getParameter("id"));
                String nombreEdit = request.getParameter("nombreUsuario");
                String passEdit = request.getParameter("password");

                User up = new User(id, nombreEdit, passEdit);
                dao.actualizar(up);

                response.sendRedirect("UsuarioServlet?accion=listar");
                break;
        }
    }
}
