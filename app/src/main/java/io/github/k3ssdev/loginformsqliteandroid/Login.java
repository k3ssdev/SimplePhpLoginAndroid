package io.github.k3ssdev.loginformsqliteandroid;

public class Login {
private final String usuario;
    private final String contrasena;
    private final String timestamp;

    public Login(String usuario, String contrasena, String timestamp) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.timestamp = timestamp;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTimestamp() {
        return timestamp;
    }

}