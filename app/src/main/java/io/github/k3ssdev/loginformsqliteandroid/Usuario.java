package io.github.k3ssdev.loginformsqliteandroid;

public class Usuario {
    private final String nombreUsuario;
    private final String contrasena;
    private final String fechaNacimiento;

    public Usuario(String nombreUsuario, String contrasena, String fechaNacimiento) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}
