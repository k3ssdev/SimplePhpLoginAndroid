package io.github.k3ssdev.loginformsqliteandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public class WebServiceHandler {
    private Activity activity_apr;

    public WebServiceHandler(Activity activity) {
        this.activity_apr = activity;
    }

    SQLiteHandler sqLiteHandler = new SQLiteHandler(activity_apr);

    public class ValidarUsuario extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            String usuario_apr = params[0];
            String contrasena_apr = params[1];
            String urlString_apr = "http://192.168.1.227/validacuenta.php";

            String resultado_apr = null;

            try {
                // Crear la conexión HTTP
                URL url = new URL(urlString_apr);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setRequestMethod("POST");
                conexion.setDoOutput(true);

                // Crear los datos del formulario
                String datos = "usuario=" + URLEncoder.encode(usuario_apr, "UTF-8") + "&contrasena=" + URLEncoder.encode(contrasena_apr, "UTF-8");

                // Escribir los datos en el cuerpo de la petición
                OutputStream outputStream = conexion.getOutputStream();
                outputStream.write(datos.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();

                // Leer la respuesta del servidor
                InputStream entrada = conexion.getInputStream();
                BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea);
                }

                // Cerrar la conexión HTTP
                entrada.close();
                conexion.disconnect();

                // Procesar la respuesta del servidor
                resultado_apr = respuesta.toString();

                // Parsear la respuesta XML
                Document document = XMLParser.convertStringToXMLDocument(resultado_apr);

                // Obtener el contenido del elemento "estado"
                NodeList estadoNodes = document.getElementsByTagName("estado");
                if (estadoNodes.getLength() > 0) {
                    Node estadoNode = estadoNodes.item(0);
                    String estado_apr = estadoNode.getTextContent();

                    // Asignar el resultado a la variable resultado_apr
                    resultado_apr = estado_apr;
                } else {
                    // Manejar el caso en el que no se pueda encontrar el elemento "estado"
                    resultado_apr = null;
                }

                // Crear un array para guardar el resultado, el usuario y la contraseña
                String[] resultadoYDatos = new String[3];
                resultadoYDatos[0] = resultado_apr; // Resultado de la validación
                resultadoYDatos[1] = usuario_apr;    // Nombre de usuario
                resultadoYDatos[2] = contrasena_apr;  // Contraseña

                return resultadoYDatos; // Devuelve el array

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null; // Devuelve null si hay un error
        }

        @Override
        protected void onPostExecute(String[] resultadoYDatos) {
            if (resultadoYDatos != null) {
                String resultado = resultadoYDatos[0]; // Resultado de la validación
                String usuario_apr = resultadoYDatos[1]; // Nombre de usuario
                String contrasena_apr = resultadoYDatos[2]; // Contraseña

                // Verifica el resultado y realiza las acciones necesarias
                if (resultado.equals("ok")) {
                    // El resultado es "ok", abre la segunda actividad
                    Intent intent = new Intent(activity_apr, SecondActivity.class);
                    activity_apr.startActivity(intent);
                } else if (resultado.equals("ko")) {
                    // El resultado es "ko", realiza otra acción
                    Toast.makeText(activity_apr, "Usuario/Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    SQLiteHandler sqLiteHandler = new SQLiteHandler(activity_apr);

                    // Insertar registro en la base de datos
                    sqLiteHandler.insertarRegistro(usuario_apr, contrasena_apr);
                }
            } else {
                // El resultado es null, hubo un error en la petición
                Toast.makeText(activity_apr, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public List<User> consultarUsuarios() {
        String urlString_apr = "http://192.168.1.227/consultarusuarios.php";
        List<User> usuarios_apr = new ArrayList<>();

        try {
            // Crear la conexión HTTP
            URL url = new URL(urlString_apr);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            // Leer la respuesta del servidor
            InputStream entrada = conexion.getInputStream();
            BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
            StringBuilder respuesta = new StringBuilder();
            String linea;

            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }

            // Cerrar la conexión HTTP
            entrada.close();
            conexion.disconnect();

            // Procesar la respuesta XML
            String xmlString = respuesta.toString();
            Document document = XMLParser.convertStringToXMLDocument(xmlString);
            NodeList usuarioNodes = document.getElementsByTagName("usuario");

            for (int i = 0; i < usuarioNodes.getLength(); i++) {
                Node usuarioNode = usuarioNodes.item(i);
                if (usuarioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element usuarioElement = (Element) usuarioNode;

                    String nombreUsuario_apr = usuarioElement.getElementsByTagName("nombreUsuario").item(0).getTextContent();
                    String contrasena_apr = usuarioElement.getElementsByTagName("contrasena").item(0).getTextContent();
                    String fechaNacimiento_apr = usuarioElement.getElementsByTagName("fecha_nacimiento").item(0).getTextContent();

                    User usuario = new User(nombreUsuario_apr, contrasena_apr, fechaNacimiento_apr);
                    usuarios_apr.add(usuario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios_apr;
    }
}