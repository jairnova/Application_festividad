package com.example.application_festividad;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView id_festividad, nombre, habitos, modales, fecha_historica, lugar, fecha_inicio, fecha_finalizacion, lugar_celebran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_festividad = (TextView) findViewById(R.id.id);
        nombre = (TextView) findViewById(R.id.nombre);
        habitos = (TextView) findViewById(R.id.habitos);
        modales = (TextView) findViewById(R.id.modales);
        fecha_historica = (TextView) findViewById(R.id.fecha_historica);
        lugar = (TextView) findViewById(R.id.lugar);
        fecha_inicio = (TextView) findViewById(R.id.fecha_inicio);
        fecha_finalizacion = (TextView) findViewById(R.id.fecha_finalizacion);
        lugar_celebran = (TextView) findViewById(R.id.lugar_celebra);
    }

    public void registrar(View view){

        String id = id_festividad.getText().toString();
        String nom = nombre.getText().toString();
        String hab = habitos.getText().toString();
        String mod = modales.getText().toString();
        String fec_his = fecha_historica.getText().toString();
        String lug = lugar.getText().toString();
        String fec_ini = fecha_inicio.getText().toString();
        String fec_fin = fecha_finalizacion.getText().toString();
        String lug_cel = lugar_celebran.getText().toString();

        if(nom.equals("") || hab.equals("") || mod.equals("") || fec_his.equals("")
                || lug.equals("") || fec_ini.equals("") || fec_fin.equals("") || lug_cel.equals("")) {

            Toast toas = Toast.makeText(getApplicationContext(), "No pueden quedar los campos vacíos'", Toast.LENGTH_SHORT);
            toas.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
            toas.show();
        } else{

            if(buscar_festividad(id) != true) {

                final Admin_Sqlite ad = new Admin_Sqlite(this);

                String tabla = "evento";
                String cuerpo_re = "('" + id + "','" + nom + "','" + hab + "','" + mod + "','"
                                            + fec_his + "','" + lug + "','" + fec_ini + "','" + fec_fin + "','" + lug_cel + "')";

                ad.Insertar_bd(tabla, cuerpo_re);

                Toast toas = Toast.makeText(getApplicationContext(), "El paciente fue registrado exitosamente ", Toast.LENGTH_SHORT);
                toas.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                toas.show();

                Limpiar();
            }else{

                Toast toas = Toast.makeText(getApplicationContext(), "La festividad con identificación: " + id + " ya existe", Toast.LENGTH_SHORT);
                toas.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                toas.show();
            }
        }

    }

    public void buscar(View view){

        String id = id_festividad.getText().toString();
        buscar_festividad(id);
    }
    public void modificar(View view){

        String id = id_festividad.getText().toString();
        String nom = nombre.getText().toString();
        String hab = habitos.getText().toString();
        String mod = modales.getText().toString();
        String fec_his = fecha_historica.getText().toString();
        String lug = lugar.getText().toString();
        String fec_ini = fecha_inicio.getText().toString();
        String fec_fin = fecha_finalizacion.getText().toString();
        String lug_cel = lugar_celebran.getText().toString();

        if(nom.equals("") || hab.equals("") || mod.equals("") || fec_his.equals("")
                || lug.equals("") || fec_ini.equals("") || fec_fin.equals("") || lug_cel.equals("")) {

            Toast toas = Toast.makeText(getApplicationContext(), "No pueden quedar los campos vacíos'", Toast.LENGTH_SHORT);
            toas.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
            toas.show();
        } else {

            if (buscar_festividad(id) != true) {

                final Admin_Sqlite ad = new Admin_Sqlite(this);

                ContentValues modificaciones = new ContentValues();
                modificaciones.put("nombre_evento", nom);
                modificaciones.put("habitos_locales", hab);
                modificaciones.put("modales", mod);
                modificaciones.put("fecha_historica", fec_his);
                modificaciones.put("lugar", lug);
                modificaciones.put("fecha_inicio", fec_ini);
                modificaciones.put("fecha_finalizacion", fec_fin);
                modificaciones.put("lugar_celebran", lug_cel);

                String[] args = new String[]{id};
                int con = ad.modificar_bd("evento", modificaciones, "id_festividad=?", args);
                if (con != 0) {

                    Toast toas = Toast.makeText(getApplicationContext(), "Se realizaron los cambios en la festividad con exito", Toast.LENGTH_SHORT);
                    toas.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                    toas.show();

                    Limpiar();
                } else {

                    Toast toas = Toast.makeText(getApplicationContext(), "La festividad con identificación: " + id + " ya existe", Toast.LENGTH_SHORT);
                    toas.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                    toas.show();
                }
            }
        }

    }
    public void eliminar(View view){

        String id = id_festividad.getText().toString();
    }

    public boolean buscar_festividad (String id){

        final Admin_Sqlite ad = new Admin_Sqlite(this);
        String consulta = "Select * from evento where id_festividad = "+id;
        String [][] datos = ad.Buscar_bd(consulta);

        if(datos != null) {
            for(int i =0; i<datos.length; i++){

                id_festividad.setText(datos[i][0]);
                nombre.setText(datos[i][1]);
                habitos.setText(datos[i][2]);
                modales.setText(datos[i][3]);
                fecha_historica.setText(datos[i][4]);
                lugar.setText(datos[i][5]);
                fecha_inicio.setText(datos[i][6]);
                fecha_finalizacion.setText(datos[i][7]);
                lugar_celebran.setText(datos[i][8]);
            }
            return true;
        }else{
            return false;
        }
    }

    public void Limpiar(){

        id_festividad.setText("");
        nombre.setText("");
        habitos.setText("");
        modales.setText("");
        fecha_historica.setText("");
        lugar.setText("");
        fecha_inicio.setText("");
        fecha_finalizacion.setText("");
        lugar_celebran.setText("");
    }
}
