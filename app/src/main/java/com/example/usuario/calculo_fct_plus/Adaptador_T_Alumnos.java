package com.example.usuario.calculo_fct_plus;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Usuario on 01/12/2015.
 */
public class Adaptador_T_Alumnos
{

    private static final String DATABASE_NAME = "noticias_plus";
    private static final String DATABASE_TABLE = "generic";

    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOMBRE = "Nombre";
    public static final String KEY_APELLIDOS = "Apellidos";
    public static final String KEY_TELEFONO = "telefono";
    public static final String KEY_FINIC="Finic";
    public static final String KEY_FFIN="Ffin";
    public static final String KEY_HORASPD="HorasPd";
    public static final String KEY_NUMDIAS="NumDias";
    public static final String KEY_HORASTFCT="HorasTfct";

    private static  String TABLE_CREATE =
            "create table " + DATABASE_TABLE +
                    "(" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_NOMBRE + " text not null, "
                    + KEY_APELLIDOS + " text not null, "
                    + KEY_TELEFONO + " text not null,"
                    + KEY_FINIC + " text not null, "
                    + KEY_FFIN + " text not null, "
                    + KEY_HORASPD + " text not null, "
                    + KEY_HORASTFCT + " text not null"+
                    ");";

        public Adaptador_T_Alumnos(ArrayList Params)
        {

        }

        public void insertar(ArrayList Params)
        {}

        public void actualizar(ArrayList Params)
        {}

        public ArrayList mostrar()
        {}

        public void borrar(int id)
        {}

    //clase para crear la base de datos SQLite
    private static class BaseDatosHelper extends SQLiteOpenHelper
    {
        BaseDatosHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                //ejecuta la sentencia SQL de creación de la BD
                db.execSQL(getDatabaseCreate());
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Actualizando base de datos de la versión " + oldVersion
                    + " a "
                    + newVersion + ", borraremos todos los datos");
            //elimina tabla de la BD
            db.execSQL("DROP TABLE IF EXISTS " + getDatabaseTable());
            //crea la nueva BD
            onCreate(db);
        }
    }

}
