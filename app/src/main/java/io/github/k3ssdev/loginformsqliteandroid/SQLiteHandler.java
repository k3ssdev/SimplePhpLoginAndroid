package io.github.k3ssdev.loginformsqliteandroid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLiteHandler {

    // Constantes para la base de datos
    private static final String databaseName_apr = "AppLog.db";
    private static final String tableName_apr = "login";
    private final SQLiteDatabase db_apr;

    // Constructor
    public SQLiteHandler(Context context) {
        // Abre o crea la base de datos
        db_apr = context.openOrCreateDatabase(databaseName_apr, Context.MODE_PRIVATE, null);

        // Crea la tabla si no existe
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName_apr + " (id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp TIMESTAMP, usuario TEXT, contrasena TEXT)";
        db_apr.execSQL(sql);
    }

    // Inserta un registro en la tabla con la marca de tiempo actual
    public void insertarRegistro(String usuario, String contrasena) {
        // Obtener la marca de tiempo actual
        Date date_apr = new Date();
        Timestamp timestamp = new Timestamp(date_apr.getTime());

        // Crea el registro
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp.toString());
        values.put("usuario", usuario);
        values.put("contrasena", contrasena);

        // Inserta el registro en la tabla
        db_apr.insert(tableName_apr, null, values);
    }

    // Consulta los registros de la tabla
    public List<Login> consultarRegistros() {
        // Consulta los registros
        List<Login> registros_apr = new ArrayList<>();
        Cursor cursor = db_apr.query(tableName_apr, null, null, null, null, null, "timestamp DESC");

        // Recorre los registros
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
            @SuppressLint("Range") String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
            @SuppressLint("Range") String contrasena = cursor.getString(cursor.getColumnIndex("contrasena"));

            // Crea un objeto Login y lo añade a la lista
            Login registro = new Login(timestamp, usuario, contrasena);
            registros_apr.add(registro);
        }

        // Cierra el cursor
        cursor.close();

        // Devuelve la lista de registros
        return registros_apr;
    }

    /** @noinspection unused*/ // Elimina todos los registros de la tabla
    public void eliminarRegistros() {
        // Elimina los registros
        db_apr.delete(tableName_apr, null, null);
    }

    /** @noinspection unused*/ // Cierra la base de datos
    public void cerrarBaseDeDatos() {
        db_apr.close();
    }
}
