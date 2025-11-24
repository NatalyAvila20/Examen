package com.platinum.cta;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/MovimientoServlet")
public class MovimientoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // si quieres mostrar un formulario o listado desde GET
        req.getRequestDispatcher("movimientos.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rutCliente = request.getParameter("rutCliente");
        String rutDueno = request.getParameter("rutDueno");
        String idCuentaStr = request.getParameter("idCuenta");
        String montoStr = request.getParameter("monto");
        String cuentaDestino = request.getParameter("cuentaDestino");
        String tipoCuenta = request.getParameter("tipoCuenta");

        String mensaje;

        // validaciones simples
        if (rutCliente == null || rutCliente.isEmpty() ||
            idCuentaStr == null || idCuentaStr.isEmpty() ||
            montoStr == null || montoStr.isEmpty() ||
            cuentaDestino == null || cuentaDestino.isEmpty()) {

            mensaje = "Faltan datos obligatorios.";
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("resultadoMovimiento.jsp").forward(request, response);
            return;
        }

        int idCuenta;
        BigDecimal monto;
        try {
            idCuenta = Integer.parseInt(idCuentaStr);
            monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                throw new NumberFormatException("Monto debe ser positivo");
            }
        } catch (NumberFormatException ex) {
            mensaje = "Id de cuenta o monto inválido: " + ex.getMessage();
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("resultadoMovimiento.jsp").forward(request, response);
            return;
        }

        Transaccion t = new Transaccion();
        t.setRutCliente(rutCliente);
        t.setRutDueno(rutDueno);
        t.setIdCuenta(idCuenta);
        t.setMontoTransferencia(monto);
        t.setCuentaTransferencia(cuentaDestino);
        t.setTipoCuenta(tipoCuenta);

        TransaccionDAO dao = new TransaccionDAO();
        boolean ok = dao.registrar(t);

        if (ok) {
            mensaje = "Transferencia registrada correctamente.";
        } else {
            mensaje = "Ocurrió un error al registrar la transferencia. Revisa logs.";
        }

        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("resultadoMovimiento.jsp").forward(request, response);
    }
}
