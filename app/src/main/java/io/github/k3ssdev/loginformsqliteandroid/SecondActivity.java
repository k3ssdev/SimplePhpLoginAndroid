package io.github.k3ssdev.loginformsqliteandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private ListView listView_apr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        // Obtiene una referencia al ListView en la vista
        listView_apr = findViewById(R.id.listView);

        // Inicia la tarea asincrónica para consultar usuarios
        new ConsultarUsuariosTask().execute();
    }

    public void exit(View view) {
        // Crea un Intent para volver al MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }

    private class ConsultarUsuariosTask extends AsyncTask<Void, Void, List<User>> {
        @Override
        protected List<User> doInBackground(Void... voids) {
            // Realiza tu consulta SQL aquí y crea una lista de usuarios
            List<User> usuarios_apr = new ArrayList<>();

            // Obtiene los datos de la base de datos
            WebServiceHandler webServiceHandler = new WebServiceHandler(SecondActivity.this);
            usuarios_apr = webServiceHandler.consultarUsuarios();

            return usuarios_apr;
        }

        @Override
        protected void onPostExecute(List<User> usuarios) {
            if (usuarios != null) {
                // Crea un adaptador personalizado para tu lista de usuarios
                UserAdapter adapter = new UserAdapter(SecondActivity.this, usuarios);

                // Asigna el adaptador al ListView
                listView_apr.setAdapter(adapter);
            }
        }
    }
}
