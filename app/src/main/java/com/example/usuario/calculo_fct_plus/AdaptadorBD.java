package com.example.usuario.calculo_fct_plus;

/**
 * Created by Usuario on 01/12/2015.
 */

import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public abstract class AdaptadorBD {

    private static final String DATABASE_NAME = "noticias_plus";
    private static  String DATABASE_TABLE = "generic";

    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOMBRE = "Nombre";
    public static final String KEY_APELLIDOS = "Apellidos";
    public static final String KEY_TELEFONO = "telefono";

    private static final String TAG = "AdaptadorBD";



    private static  String DATABASE_CREATE =
            "create table " + getDatabaseTable() +
                    "(" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_NOMBRE + " text not null, "
                    + KEY_APELLIDOS + " text not null, "
                    + KEY_TELEFONO + " text not null);";


    private BaseDatosHelper BDHelper;
    private SQLiteDatabase bsSql;

    private String[] todasColumnas = new String[]{KEY_ROWID, KEY_NOMBRE, KEY_APELLIDOS, KEY_TELEFONO};


    /*//---constructor---
    public AdaptadorBD(Context ctx)
    {
        this.context = ctx;
        BDHelper = new BaseDatosHelper(context);
    }
*/
    public static String getDatabaseTable() {
        return DATABASE_TABLE;
    }

    public static void setDatabaseTable(String databaseTable) {
        DATABASE_TABLE = databaseTable;
    }

    public static String getDatabaseCreate() {
        return DATABASE_CREATE;
    }

    public static void setDatabaseCreate(String databaseCreate) {
        DATABASE_CREATE = databaseCreate;
    }

    //--- abre una conexión a la BD para lectura/escritura
    public AdaptadorBD open() throws SQLException
    {
        bsSql = BDHelper.getWritableDatabase();
        return this;
    }

    //---cierra la base de datos---
    public void close()
    {
        BDHelper.close();
    }

    //-- INSERTAR
    //inserta una fila en la BD a partir de los datos de un contacto
    public long insertarContacto(String nombre, String tipo, String telefono)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOMBRE, nombre);
        initialValues.put(KEY_TIPO, tipo);
        initialValues.put(KEY_TELEFONO, telefono);
        //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
        return bsSql.insert(getDatabaseTable(), null, initialValues);
    }

    //inserta una fila en la BD a partir de un objeto Contactos
    public long insertarContacto(Contactos contacto)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOMBRE, contacto.getNombre());
        initialValues.put(KEY_TIPO, contacto.getTipo());
        initialValues.put(KEY_TELEFONO, contacto.getTipo());
        //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
        return bsSql.insert(getDatabaseTable(), null, initialValues);
    }

    //-- ELIMINAR
    //elimina el contacto identificado por numero
    public boolean borrarContacto(long numero)
    {
        //manda una sentencia DELETE a la BD para eliminar la fila identificada por numero
        return bsSql.delete(getDatabaseTable(), KEY_ROWID + "=" + numero, null) > 0;
    }

    //--CONSULTAR
    //consulta a la BD para obtener todos los contactos
    public Cursor getTodosContactos()
    {
        return bsSql.query(getDatabaseTable(), getTodasColumnas(), null, null, null, null, null);
    }

    //consulta de un contacto por 'numero' (clave primaria)
    public Cursor getContacto(long numero) throws SQLException
    {
        Cursor mCursor = bsSql.query(true, getDatabaseTable(), getTodasColumnas(),
                KEY_ROWID + "=" + numero, null, null, null, null, null);
        //si hay datos devueltos, apunta al principio
        if (mCursor != null) mCursor.moveToFirst();
        return mCursor;
    }

    //-- MODIFICAR
    //actualiza los datos del contacto identificado por numero, con los nuevos valores
    //pasados como parámetros
    public boolean actualizarContacto(int numero, String nombre, String tipo, String telefono)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NOMBRE, nombre);
        args.put(KEY_TIPO, tipo);
        args.put(KEY_TELEFONO, telefono);
        //manda una sentencia UPDATE a la BD para modificar el contacto identifiado por numero
        return bsSql.update(getDatabaseTable(), args, KEY_ROWID + "=" + numero, null) > 0;
    }

    //actualiza los datos de un contacto concreto
    public boolean actualizarContacto(Contactos contacto)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NOMBRE, contacto.getNombre());
        args.put(KEY_TIPO, contacto.getTipo());
        args.put(KEY_TELEFONO, contacto.getTelefono());
        return bsSql.update(getDatabaseTable(), args, KEY_ROWID + "=" + contacto.getNumero(), null) > 0;
    }

    //-- OTROS MÉTODOS
    //Devuelve cadena con los datos de un contacto
    public String mostrarContacto(long numero)
    {
        String cadena = null;
        //obtiene objeto Cursor con el contacto consultado
        Cursor c = getContacto(numero);
        //si existe, se posiciona al principio
        if (c.moveToFirst()) {
            //compone la cadena con todos los datos del contacto
            cadena =
                    "NÚMERO: " + c.getString(0) + "\n" +
                            "NOMBRE: " + c.getString(1) + "\n" +
                            "TIPO: " + c.getString(2) + "\n" +
                            "TELEFONO: " + c.getString(3);
        }
        return cadena;
    }

    //Devuelve cadena con los datos de un contacto (la fila de un Cursor)
    public String mostrarContacto(Cursor c)
    {
        String cadena = null;

        cadena =
                "NÚMERO: " + c.getString(0) + "\n" +
                        "NOMBRE: " + c.getString(1) + "\n" +
                        "TIPO: " + c.getString(2) + "\n" +
                        "TELEFONO: " + c.getString(3);

        return cadena;
    }

    //Obtiene una lista de contactos través de un objeto Cursor
    public List<Contactos> getAllContactos()
    {
        //Lista de contactos
        List<Contactos> listaContactos = new ArrayList<Contactos>();
        //objeto cursor que se llena con el resultado de la consulta que obtiene todos los contactos
        Cursor cursor = this.getTodosContactos();
        //se posiciona al principio del cursor
        cursor.moveToFirst();
        //mientras hay datos en el cursor
        while (!cursor.isAfterLast()) {
            //genera un contacto
            Contactos comment = cursorToContactos(cursor);
            //añade un contacto a la lista
            listaContactos.add(comment);
            //avanza al siguiente
            cursor.moveToNext();
        }
        cursor.close();
        return listaContactos;
    }

    //genera un contacto a partir de un objeto Cursor
    private Contactos cursorToContactos(Cursor cursor)
    {
        Contactos contacto = new Contactos();
        contacto.setNumero(cursor.getLong(0));
        contacto.setNombre(cursor.getString(1));
        contacto.setTipo(cursor.getString(2));
        contacto.setTelefono(cursor.getString(3));

        return contacto;
    }

    public String[] getTodasColumnas() {
        return todasColumnas;
    }

    public void setTodasColumnas(String[] todasColumnas) {
        this.todasColumnas = todasColumnas;
    }

//**** CLASE PRIVADA subclase SQLiteOpenHelper***/

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
