package io.github.k3ssdev.loginformsqliteandroid;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

            // Crea una instancia de WebServiceHandler
            WebServiceHandler webServiceHandler = new WebServiceHandler(this);

            // Llama a la tarea ValidarUsuario con execute
            webServiceHandler.new ValidarUsuario().execute(usuario, contrasena);
        });
    }
}
