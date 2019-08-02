package com.example.application_festividad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        int id = Integer.parseInt(id_festividad.getText().toString());
        String nom = nombre.getText().toString();
        String hab = habitos.getText().toString();
        String mod = modales.getText().toString();
        String fec_his = fecha_historica.getText().toString();
        String lug = lugar.getText().toString();
        String fec_ini = fecha_inicio.getText().toString();
        String fec_fin = fecha_finalizacion.getText().toString();
        String lug_cel = lugar_celebran.getText().toString();
    }
    public void buscar(View view){

    }
    public void modificar(View view){

    }
    public void eliminar(View view){

    }
}
