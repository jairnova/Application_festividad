package com.example.application_festividad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Admin_Sqlite extends SQLiteOpenHelper {

    private static final String name ="Festividad";
    private static  final int version = 1;
    Context contexto;
    private static  final String tabla_evento = "create table evento" +
            "(id_festividad int Primary key," +
            "nombre_evento varchar not null," +
            "habitos_locales varchar not null," +
            "modales varchar not null," +
            "fecha_historica date not null," +
            "lugar varchar not null," +
            "fecha_inicio date not null," +
            "fecha_finalizacion date not null," +
            "lugar_celebran varchar not null);";

    public Admin_Sqlite(Context context) {
        super(context, name, null, version);
        contexto=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_evento);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Insertar_bd (String tabla, String registro){

        SQLiteDatabase bd = getWritableDatabase();

        if (bd != null) {
            bd.execSQL("Insert into " + tabla + " values " + registro);
            bd.close();
        }
    }

    public String [][] Buscar_bd (String consulta) {

        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery(consulta, null);
        String[][] datos = null;

        if(cursor.moveToFirst()){
            datos = new String[cursor.getCount()][cursor.getColumnNames().length];

            for (int i = 0; i < cursor.getCount(); i++) {
                for(int j = 0; j < cursor.getColumnNames().length; j++){
                    datos[i][j] = cursor.getString(j);
                }
                cursor.moveToNext();
            }
        }
        bd.close();
        return datos;
    }

    public int modificar_bd (String tabla, ContentValues modificaciones, String id, String[] args){

        SQLiteDatabase bd = getWritableDatabase();
        int dato_mod = bd.update(tabla,modificaciones,id,args);
        bd.close();
        return dato_mod;
    }

    public int eliminar(String tabla, String id_consulta, String[] args){

        SQLiteDatabase bd = getWritableDatabase();
        int dato_eli = bd.delete(tabla, id_consulta, args);
        bd.close();
        return dato_eli;
    }

}
