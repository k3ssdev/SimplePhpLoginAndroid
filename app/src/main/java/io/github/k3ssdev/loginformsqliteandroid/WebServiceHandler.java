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
    private Activity activity;

    public WebServiceHandler(Activity activity) {
        this.activity = activity;
    }


    public class ValidarUsuario extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String usuario = params[0];
            String contrasena = params[1];
            String urlString = "http://192.168.1.227/validacuenta.php";

            String resultado = null;

            try {
                // Crear la conexión HTTP
                URL url = new URL(urlString);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setRequestMethod("POST");
                conexion.setDoOutput(true);

                // Crear los datos del formulario
                String datos = "usuario=" + URLEncoder.encode(usuario, "UTF-8") + "&contrasena=" + URLEncoder.encode(contrasena, "UTF-8");

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
                resultado = respuesta.toString();

                // Parsear la respuesta XML
                Document document = XMLParser.convertStringToXMLDocument(resultado);

                // Obtener el contenido del elemento "estado"
                NodeList estadoNodes = document.getElementsByTagName("estado");
                if (estadoNodes.getLength() > 0) {
                    Node estadoNode = estadoNodes.item(0);
                    String estado = estadoNode.getTextContent();

                    // Haz algo con el valor del estado
                    resultado = estado;
                } else {
                    // Manejar el caso en el que no se pueda encontrar el elemento "estado"
                    System.out.println("No se encontró el elemento 'estado'.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultado;
        }

        @Override
        protected void onPostExecute(String resultado) {
            if (resultado != null) {
                // Verifica el resultado y realiza las acciones necesarias
                if (resultado.equals("ok")) {
                    // El resultado es "ok", abre la segunda actividad
                    Intent intent = new Intent(activity, ActividadExitosa.class);
                    activity.startActivity(intent);
                } else if (resultado.equals("ko")) {
                    // El resultado es "ko", realiza otra acción
                    Toast.makeText(activity, "Usuario/Contraseña incorrectos", Toast.LENGTH_SHORT).show();

                }
            } else {
                // El resultado es null, hubo un error en la petición
                Toast.makeText(activity, "Error en la petición", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public List<Usuario> consultarUsuarios() {
        String urlString = "http://192.168.1.227/consultarusuarios.php";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            // Crear la conexión HTTP
            URL url = new URL(urlString);
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

                    String nombreUsuario = usuarioElement.getElementsByTagName("nombreUsuario").item(0).getTextContent();
                    String contrasena = usuarioElement.getElementsByTagName("contrasena").item(0).getTextContent();
                    String fechaNacimiento = usuarioElement.getElementsByTagName("fecha_nacimiento").item(0).getTextContent();

                    Usuario usuario = new Usuario(nombreUsuario, contrasena, fechaNacimiento);
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }


}

