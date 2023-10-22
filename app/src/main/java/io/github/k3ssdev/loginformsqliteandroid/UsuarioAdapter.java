package io.github.k3ssdev.loginformsqliteandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import android.view.LayoutInflater;

public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    private List<Usuario> usuarios;

    public UsuarioAdapter(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_usuario, null);
        }

        // Obtén las referencias a tus elementos de diseño en item_usuario.xml
        TextView nombreUsuarioTextView = convertView.findViewById(R.id.nombreUsuarioTextView);
        TextView contrasenaTextView = convertView.findViewById(R.id.contrasenaTextView);
        TextView fechaNacimientoTextView = convertView.findViewById(R.id.fechaNacimientoTextView);

        // Obtén el usuario en la posición actual
        Usuario usuario = usuarios.get(position);

        // Establece los datos en tus elementos de diseño
        nombreUsuarioTextView.setText(usuario.getNombreUsuario());
        contrasenaTextView.setText(usuario.getContrasena());
        fechaNacimientoTextView.setText(usuario.getFechaNacimiento());

        return convertView;
    }
}
