package com.platinum.cta;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaccion {

    private int id;
    private String rutCliente;
    private String rutDueno;
    private int idCuenta;
    private BigDecimal montoTransferencia;
    private String cuentaTransferencia;
    private String tipoCuenta;
    private Timestamp fecha;

    public Transaccion() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRutCliente() { return rutCliente; }
    public void setRutCliente(String rutCliente) { this.rutCliente = rutCliente; }

    public String getRutDueno() { return rutDueno; }
    public void setRutDueno(String rutDueno) { this.rutDueno = rutDueno; }

    public int getIdCuenta() { return idCuenta; }
    public void setIdCuenta(int idCuenta) { this.idCuenta = idCuenta; }

    public BigDecimal getMontoTransferencia() { return montoTransferencia; }
    public void setMontoTransferencia(BigDecimal montoTransferencia) { this.montoTransferencia = montoTransferencia; }

    public String getCuentaTransferencia() { return cuentaTransferencia; }
    public void setCuentaTransferencia(String cuentaTransferencia) { this.cuentaTransferencia = cuentaTransferencia; }

    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }

    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
}
