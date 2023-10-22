package io.github.k3ssdev.loginformsqliteandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ActividadExitosa extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_exitosa);

        listView = findViewById(R.id.listView);

        new ConsultarUsuariosTask().execute();
    }

    public void exit(View view) {
        // Crea un Intent para volver al MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }

    private class ConsultarUsuariosTask extends AsyncTask<Void, Void, List<Usuario>> {
        @Override
        protected List<Usuario> doInBackground(Void... voids) {
            // Realiza tu consulta SQL aquí y crea una lista de usuarios
            List<Usuario> usuarios = new ArrayList<>();

            // Obtiene los datos de la base de datos
            WebServiceHandler webServiceHandler = new WebServiceHandler(ActividadExitosa.this);
            usuarios = webServiceHandler.consultarUsuarios();

            return usuarios;
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            if (usuarios != null) {
                // Crea un adaptador personalizado para tu lista de usuarios
                UsuarioAdapter adapter = new UsuarioAdapter(ActividadExitosa.this, usuarios);

                // Asigna el adaptador al ListView
                listView.setAdapter(adapter);
            }
        }
    }
}
