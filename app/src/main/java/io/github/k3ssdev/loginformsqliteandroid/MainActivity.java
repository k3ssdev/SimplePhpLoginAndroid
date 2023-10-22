package io.github.k3ssdev.loginformsqliteandroid;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "io.github.k3ssdev.loginformsqliteandroid.MESSAGE";
    //private WebServiceHandler webServiceHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(v -> {
            String usuario = editTextUsername.getText().toString();
            String contrasena = editTextPassword.getText().toString();

            // Crear una instancia de WebServiceHandler
            WebServiceHandler webServiceHandler = new WebServiceHandler(this);

            // Llamar a la tarea ValidarUsuario con execute
            webServiceHandler.new ValidarUsuario().execute(usuario, contrasena);

        });

    }
}
