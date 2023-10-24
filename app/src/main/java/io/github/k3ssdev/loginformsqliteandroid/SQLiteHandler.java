package io.github.k3ssdev.loginformsqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Timestamp;
import java.util.Date;

public class SQLiteHandler {

    // Constantes para la base de datos
    private static final String databaseName_apr = "AppLog.db";
    private static final String tableName_apr = "login";

    private SQLiteDatabase db;

    // Constructor
    public SQLiteHandler(Context context) {
        // Abre o crea la base de datos
        db = context.openOrCreateDatabase(databaseName_apr, Context.MODE_PRIVATE, null);

        // Crea la tabla si no existe
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName_apr + " (id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp TIMESTAMP, usuario TEXT, contrasena TEXT)";
        db.execSQL(sql);
    }

    // Inserta un registro en la tabla con la marca de tiempo actual
    public void insertarRegistro(String usuario, String contrasena) {
        // Obtener la marca de tiempo actual
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        // Crea el registro
        ContentValues values = new ContentValues();
        values.put("timestamp", timestamp.toString());
        values.put("usuario", usuario);
        values.put("contrasena", contrasena);

        // Inserta el registro en la tabla
        db.insert(tableName_apr, null, values);
    }

    // Consulta los registros de la tabla
    public Cursor consultarRegistros() {
        // Consulta los registros
        String[] columnas = {"id", "timestamp", "usuario", "contrasena"};
        return db.query(tableName_apr, columnas, null, null, null, null, "timestamp DESC");
    }

    // Elimina todos los registros de la tabla
    public void eliminarRegistros() {
        // Elimina los registros
        db.delete(tableName_apr, null, null);
    }

    // Cierra la base de datos
    public void cerrarBaseDeDatos() {
        db.close();
    }
}
