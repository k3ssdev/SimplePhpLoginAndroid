package io.github.k3ssdev.loginformsqliteandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LoginAdapter extends BaseAdapter {
    // Contexto de la aplicación
    private final Context context_apr;
    // Lista de usuarios
    private final List<Login> rows_apr;

    // Constructor
    public LoginAdapter(Context context, List<Login> rows) {
        this.context_apr = context;
        this.rows_apr = rows;
    }

    // Devuelve la cantidad de elementos en la lista
    @Override
    public int getCount() {
        return rows_apr.size();
    }

    // Obtiene un elemento de la lista en una posición específica
    @Override
    public Object getItem(int position) {
        return rows_apr.get(position);
    }

    // Obtiene el ID de un elemento en una posición específica
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Obtiene la vista que muestra un elemento de la lista
    @SuppressLint("InflateParams") // No se usa el padre de la vista, por eso se pasa null
    @Override
    public View getView(int position, View convertView_apr, ViewGroup parent) {
        if (convertView_apr == null) {
            // Infla la vista si es nula, crea una instancia de una vista a partir del diseño XML
            LayoutInflater inflater_apr = (LayoutInflater) context_apr.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Obtiene la vista del elemento de la lista
            convertView_apr = inflater_apr.inflate(R.layout.item_usuario, null);
        }

        // Obtiene las referencias a los elementos de diseño en item_usuario.xml
        TextView nombreUsuarioTextView_apr = convertView_apr.findViewById(R.id.nombreUsuarioTextView);
        TextView contrasenaTextView_apr = convertView_apr.findViewById(R.id.contrasenaTextView);
        TextView fechaNacimientoTextView_apr = convertView_apr.findViewById(R.id.fechaNacimientoTextView);

        // Obtiene el usuario en la posición actual
        Login login_apr = rows_apr.get(position);

        // Establece los datos en los elementos de diseño
        nombreUsuarioTextView_apr.setText(login_apr.getUsuario());
        contrasenaTextView_apr.setText(login_apr.getContrasena());
        fechaNacimientoTextView_apr.setText(login_apr.getTimestamp());

        return convertView_apr;
    }
}



