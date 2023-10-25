package io.github.k3ssdev.loginformsqliteandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

// Actividad para mostrar los registros de la tabla SQLite
public class LogActivity extends AppCompatActivity {
    private ListView listView_apr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // Obtiene una referencia al ListView en la vista
        listView_apr = findViewById(R.id.listView);

        // Inicia la tarea asincrónica para consultar tabla SQLite
        new ConsultarLogin().execute();
    }

    public void exit(View view) {
        // Crea un Intent para volver al MainActivity
        Intent intent_apr = new Intent(this, MainActivity.class);
        startActivity(intent_apr);
        finish(); // Cierra la actividad actual
    }

    private class ConsultarLogin extends AsyncTask<Void, Void, List<Login>> {
        @Override
        protected List<Login> doInBackground(Void... voids) {
            // Obtiene los registros de SQLite llamando al método consultarRegistros
            SQLiteHandler sqLiteHandler_apr = new SQLiteHandler(LogActivity.this);
            List<Login> registros_apr = sqLiteHandler_apr.consultarRegistros();

            return registros_apr;
        }

        @Override
    protected void onPostExecute(List<Login> registros) {
            if (registros != null) {
                // Crea un adaptador personalizado para tu lista de usuarios
                LoginAdapter adapter_apr = new LoginAdapter(LogActivity.this, registros);
                // Asigna el adaptador al ListView
                listView_apr.setAdapter(adapter_apr);
            }
        }
    }
}