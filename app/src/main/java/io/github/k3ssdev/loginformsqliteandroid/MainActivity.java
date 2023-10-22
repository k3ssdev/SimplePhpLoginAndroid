package io.github.k3ssdev.loginformsqliteandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "io.github.k3ssdev.loginformsqliteandroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String usuario = editTextUsername.getText().toString();
            String contrasena = editTextPassword.getText().toString();

            // Llama a la AsyncTask para validar el usuario con estos datos y mostrar el resultado en un Toast
            ValidarUsuarioTask validarUsuarioTask = new ValidarUsuarioTask();
            validarUsuarioTask.execute(usuario, contrasena);



            //Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            //startActivity(intent);

        });
    }

    private class ValidarUsuarioTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(@NonNull String... params) {
            String usuario = params[0];
            String contrasena = params[1];
            String urlString = "http://192.168.1.227/validacuenta.php";

            String resultado = null;

            try {
                // Crear la conexión HTTP
                URL url = new URL(urlString);
                URL direccion = url;
                HttpURLConnection conexion = (HttpURLConnection) direccion.openConnection();
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
                // Analizar la respuesta XML
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(respuesta.toString())));
                Element rootElement = doc.getDocumentElement();
                String estado = rootElement.getElementsByTagName("estado").item(0).getTextContent();

                resultado = estado;

                // mostrar en logcat, pon un tag para filtrar
                Log.d("CHECK", "doInBackground: " + respuesta.toString());




            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            // Muestra un Toast con el resultado de la validación
            Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_SHORT).show();
        }
    }


}
