package io.github.k3ssdev.loginformsqliteandroid;

// Clase POJO para objeto Login
public class Login {
    private final String usuario_apr;
    private final String contrasena_apr;
    private final String timestamp_apr;

    public Login(String usuario, String contrasena, String timestamp) {
        this.usuario_apr = usuario;
        this.contrasena_apr = contrasena;
        this.timestamp_apr = timestamp;
    }

    public String getUsuario() {
        return usuario_apr;
    }

    public String getContrasena() {
        return contrasena_apr;
    }

    public String getTimestamp() {
        return timestamp_apr;
    }

}