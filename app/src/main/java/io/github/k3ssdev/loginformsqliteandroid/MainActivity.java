package io.github.k3ssdev.loginformsqliteandroid;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Constante para el mensaje extra
    //public static final String EXTRA_MESSAGE = "io.github.k3ssdev.loginformsqliteandroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtiene referencias a los elementos de la interfaz de usuario
        EditText editTextUsername_apr = findViewById(R.id.editTextUsername);
        EditText editTextPassword_apr = findViewById(R.id.editTextPassword);
        Button buttonLogin_apr = findViewById(R.id.buttonLogin);

        // Configura un escuchador de clic para el botón de inicio de sesión
        buttonLogin_apr.setOnClickListener(v -> {
            String usuario = editTextUsername_apr.getText().toString();
            String contrasena = editTextPassword_apr.getText().toString();

            // Valida que usuario y contraseña no estén vacíos
            if (usuario.isEmpty() || contrasena.isEmpty()) {
                // Comprueba que campo esté vacío y muestra un mensaje de error
                if (usuario.isEmpty()) {
                    editTextUsername_apr.setError("El usuario no puede estar vacío");
                }
                if (contrasena.isEmpty()) {
                    editTextPassword_apr.setError("La contraseña no puede estar vacía");
                }
                // Muestra un mensaje toast
                Toast.makeText(this, "¡Login incorrecto!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Valida que usuario solo use letras y números
            if (!usuario.matches("[A-Za-z0-9]+")) {
                // Muestra un mensaje de error
                editTextUsername_apr.setError("El usuario solo puede contener letras y números");
                // Muestra un mensaje toast
                Toast.makeText(this, "¡Login incorrecto!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Valida que usuario tenga entre 4 y 8 caracteres
            if (contrasena.length() < 4 || contrasena.length() > 8) {
                // Muestra un mensaje de error
                editTextPassword_apr.setError("La contraseña debe tener entre 4 y 8 caracteres");
                // Muestra un mensaje toast
                Toast.makeText(this, "¡Login incorrecto!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crea una instancia de WebServiceHandler
            WebServiceHandler webServiceHandler = new WebServiceHandler(this);

            // Llama a la tarea ValidarUsuario con execute
            webServiceHandler.new ValidarUsuario().execute(usuario, contrasena);
        });
    }
}
