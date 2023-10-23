package io.github.k3ssdev.loginformsqliteandroid;

public class User {
    private final String nombreUsuario;
    private final String contrasena;
    private final String fechaNacimiento;

    // Constructor de la clase User
    public User(String nombreUsuario, String contrasena, String fechaNacimiento) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Método para obtener el nombre de usuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Método para obtener la contraseña
    public String getContrasena() {
        return contrasena;
    }

    // Método para obtener la fecha de nacimiento
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}
