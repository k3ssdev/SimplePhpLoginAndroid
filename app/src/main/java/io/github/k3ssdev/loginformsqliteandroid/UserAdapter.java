package io.github.k3ssdev.loginformsqliteandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import android.view.LayoutInflater;

public class UserAdapter extends BaseAdapter {
    // Contexto de la aplicación
    private final Context context_apr;
    // Lista de usuarios
    private final List<User> usuarios_apr;

    // Constructor
    public UserAdapter(Context context, List<User> usuarios) {
        this.context_apr = context;
        this.usuarios_apr = usuarios;
    }

    // Devuelve la cantidad de elementos en la lista
    @Override
    public int getCount() {
        return usuarios_apr.size();
    }

    // Obtiene un elemento de la lista en una posición específica
    @Override
    public Object getItem(int position) {
        return usuarios_apr.get(position);
    }

    // Obtiene el ID de un elemento en una posición específica
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Obtiene la vista que muestra un elemento de la lista
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Infla la vista si es nula
            LayoutInflater inflater = (LayoutInflater) context_apr.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_usuario, null);
        }

        // Obtiene las referencias a los elementos de diseño en item_usuario.xml
        TextView nombreUsuarioTextView_apr = convertView.findViewById(R.id.nombreUsuarioTextView);
        TextView contrasenaTextView_apr = convertView.findViewById(R.id.contrasenaTextView);
        TextView fechaNacimientoTextView_apr = convertView.findViewById(R.id.fechaNacimientoTextView);

        // Obtiene el usuario en la posición actual
        User usuario = usuarios_apr.get(position);

        // Establece los datos en los elementos de diseño
        nombreUsuarioTextView_apr.setText(usuario.getNombreUsuario());
        contrasenaTextView_apr.setText(usuario.getContrasena());
        fechaNacimientoTextView_apr.setText(usuario.getFechaNacimiento());

        return convertView;
    }
}
