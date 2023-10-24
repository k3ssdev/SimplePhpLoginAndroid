package io.github.k3ssdev.loginformsqliteandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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

    private class ConsultarLogin extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            // Obtiene los registros de SQLite llamando al método consultarRegistros
            SQLiteHandler sqLiteHandler = new SQLiteHandler(LogActivity.this);
            Cursor cursor = sqLiteHandler.consultarRegistros();

            List<String> registros = new ArrayList<>();

            while (cursor.moveToNext()) {
                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
                String contrasena = cursor.getString(cursor.getColumnIndex("contrasena"));

                // Aquí puedes construir el formato de registro que desees
                String registro = "Timestamp: " + timestamp + ", Usuario: " + usuario + ", Contraseña: " + contrasena;
                registros.add(registro);
            }

            cursor.close();
            sqLiteHandler.cerrarBaseDeDatos();

            return registros;
        }

        @Override
        protected void onPostExecute(List<String> registros) {
            if (registros != null) {
                // Crea un adaptador personalizado para tu lista de registros
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LogActivity.this, android.R.layout.simple_list_item_1, registros);

                // Asigna el adaptador al ListView
                listView_apr.setAdapter(adapter);
            }
        }
    }
}
