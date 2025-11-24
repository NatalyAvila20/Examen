package com.platinum.cta;

public class User {
    private int id;
    private String nombreUsuario;
    private String password;

    public User() {}

    public User(int id, String nombreUsuario, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public User(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
