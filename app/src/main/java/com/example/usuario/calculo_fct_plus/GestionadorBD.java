package com.example.usuario.calculo_fct_plus;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 10/12/2015.
 */
public class GestionadorBD
{
    public static final String KEY_ALUMNOS_ROWID = "id";
    public static final String KEY_ALUMNOS_NOMBRE = "nombre";
    public static final String KEY_ALUMNOS_APELLIDOS="apellidos";
    public static final String KEY_ALUMNOS_TELEFONO="telefono";
    public static final String KEY_ALUMNOS_CORREO="correo";
    public static final String KEY_ALUMNOS_YEARINICIOFCT="YearInicioFct";
    public static final String KEY_ALUMNOS_FINIC="Finic";
    public static final String KEY_ALUMNOS_FFIN="Ffin";
    public static final String KEY_ALUMNOS_HORASPD="HorasPd";
    public static final String KEY_ALUMNOS_NUMDIAS="NumDias";
    public static final String KEY_ALUMNOS_HorasTFCT="Tfct";

    public static final String KEY_EMPRESAS_ROWID = "id";
    public static final String KEY_EMPRESAS_NOMBRE_EMPRESA = "nombreE";
    public static final String KEY_EMPRESAS_NOMBRE_RESPONSABLE = "nombreR";
    public static final String KEY_EMPRESAS_APELLIDOS="apellidos";
    public static final String KEY_EMPRESAS_EMAIL="email";
    public static final String KEY_EMPRESAS_TELEFONO = "telefono";
    public static final String KEY_EMPRESAS_DIRECCION="direccion";
    public static final String KEY_EMPRESAS_WEB="web";

    private static final String TAG = "AdaptadorBD";

    private static final String DATABASE_NAME = "dbCalculoFCT";
    private static final String DATABASE_TABLE_EMPRESAS = "empresas";
    private static final String DATABASE_TABLE_ALUMNOS = "alumnos";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE_EMPRESAS+
                    " ("+KEY_EMPRESAS_ROWID+" integer primary key autoincrement, "
                    +KEY_EMPRESAS_NOMBRE_EMPRESA+" text , "
                    +KEY_EMPRESAS_NOMBRE_RESPONSABLE+" text , "
                    +KEY_EMPRESAS_APELLIDOS+" text , "
                    +KEY_EMPRESAS_EMAIL+" text , "
                    +KEY_EMPRESAS_TELEFONO+" text , "
                    +KEY_EMPRESAS_DIRECCION+" text , "
                    +KEY_EMPRESAS_WEB+" text);" +
                    "" +
                    "create table "+DATABASE_TABLE_ALUMNOS+"" +
                    "("+KEY_ALUMNOS_ROWID+" integer primary key autoincrement, "
                    +KEY_ALUMNOS_NOMBRE+" text , "
                    +KEY_ALUMNOS_APELLIDOS+" text , "
                    +KEY_ALUMNOS_TELEFONO+" text , "
                    +KEY_ALUMNOS_CORREO+" text , "
                    +KEY_ALUMNOS_YEARINICIOFCT+" text , "
                    +KEY_ALUMNOS_FINIC+" text , "
                    +KEY_ALUMNOS_FFIN+" text , "
                    +KEY_ALUMNOS_HORASPD+" text , "
                    +KEY_ALUMNOS_NUMDIAS+" text , "
                    +KEY_ALUMNOS_HorasTFCT+" text )";
                    

    private final Context context;
    private BaseDatosHelper BDHelper;
    private SQLiteDatabase bsSql;
    private String[] todasColumnas_alumnos =new String[] {KEY_ALUMNOS_ROWID,KEY_ALUMNOS_NOMBRE,
            KEY_ALUMNOS_APELLIDOS,KEY_ALUMNOS_TELEFONO,KEY_ALUMNOS_CORREO,KEY_ALUMNOS_YEARINICIOFCT,
            KEY_ALUMNOS_FINIC,KEY_ALUMNOS_FFIN,KEY_ALUMNOS_HORASPD,KEY_ALUMNOS_NUMDIAS,
            KEY_ALUMNOS_HorasTFCT};

    private String[] todasColumnas_empresas =new String[] {KEY_EMPRESAS_ROWID,
            KEY_EMPRESAS_NOMBRE_EMPRESA,KEY_EMPRESAS_NOMBRE_RESPONSABLE,KEY_EMPRESAS_APELLIDOS,
            KEY_EMPRESAS_EMAIL,KEY_EMPRESAS_TELEFONO,KEY_EMPRESAS_DIRECCION,
            KEY_EMPRESAS_WEB};

    public GestionadorBD(Context ctx)
    {
        this.context = ctx;
        BDHelper = new BaseDatosHelper(context);
    }

    //--- abre una conexión a la BD para lectura/escritura
    public GestionadorBD open() throws SQLException{
        bsSql = BDHelper.getWritableDatabase();
        return this;
    }

    //---cierra la base de datos---
    public void close(){
        BDHelper.close();
    }

    //inserta una fila en la BD a partir de un objeto Empresa
    public long insertarEmpresa(Empresa emp){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMPRESAS_NOMBRE_EMPRESA, emp.getNombreE());
        initialValues.put(KEY_EMPRESAS_NOMBRE_RESPONSABLE, emp.getNombreE());
        initialValues.put(KEY_EMPRESAS_APELLIDOS, emp.getApellidos());
        initialValues.put(KEY_EMPRESAS_EMAIL, emp.getEmail());
        initialValues.put(KEY_EMPRESAS_TELEFONO,emp.getTelefono());
        initialValues.put(KEY_EMPRESAS_DIRECCION,emp.getDireccion());
        initialValues.put(KEY_EMPRESAS_WEB,emp.getWeb());


        //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
        return bsSql.insert(DATABASE_TABLE_EMPRESAS, null, initialValues);
    }

    //inserta una fila en la BD a partir de un objeto Alumno
    public long insertarAlumno(Alumno Al){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ALUMNOS_NOMBRE, Al.getNombre());
        initialValues.put(KEY_ALUMNOS_APELLIDOS, Al.getApellidos());
        initialValues.put(KEY_ALUMNOS_TELEFONO, Al.getTelefono());
        initialValues.put(KEY_ALUMNOS_CORREO,Al.getCorreo());
        initialValues.put(KEY_ALUMNOS_YEARINICIOFCT,Al.getYearInicioFct());
        initialValues.put(KEY_ALUMNOS_FINIC,Al.getFinic());
        initialValues.put(KEY_ALUMNOS_FFIN,Al.getFfin());
        initialValues.put(KEY_ALUMNOS_HORASPD,Al.getHorasPd());
        initialValues.put(KEY_ALUMNOS_NUMDIAS,Al.getNumDias());
        initialValues.put(KEY_ALUMNOS_HorasTFCT,Al.getHorasTfct());
        //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
        return bsSql.insert(DATABASE_TABLE_ALUMNOS, null, initialValues);
    }

    //-- ELIMINAR
    //elimina la empresa identificada por numero
    public boolean borrarEmpresa(long numero){
        //manda una sentencia DELETE a la BD para eliminar la fila identificada por numero
        return bsSql.delete(DATABASE_TABLE_EMPRESAS, KEY_EMPRESAS_ROWID + "=" + numero, null) > 0;
    }

    //-- ELIMINAR
    //elimina el alumno identificado por numero
    public boolean borrarContacto(long numero){
        //manda una sentencia DELETE a la BD para eliminar la fila identificada por numero
        return bsSql.delete(DATABASE_TABLE_ALUMNOS, KEY_ALUMNOS_ROWID + "=" + numero, null) > 0;
    }

    //--CONSULTAR
    //consulta a la BD para obtener todas las empresas
    public Cursor getTodaslasEmpresas() {
        return bsSql.query(DATABASE_TABLE_EMPRESAS, todasColumnas_empresas,null,null,null,null,null);
    }

    //consulta a una empresa por 'numero' (clave primaria)
    public Cursor getEmpresa(long numero) throws SQLException{
        Cursor mCursor = bsSql.query(true, DATABASE_TABLE_EMPRESAS, todasColumnas_empresas,
                KEY_EMPRESAS_ROWID + "=" + numero,null,null,null,null,null);
        //si hay datos devueltos, apunta al principio
        if (mCursor != null)  mCursor.moveToFirst();
        return mCursor;
    }

    //--CONSULTAR
    //consulta a la BD para obtener todos los alumnos
    public Cursor getTodoslosAlumnos() {
        return bsSql.query(DATABASE_TABLE_ALUMNOS, todasColumnas_alumnos,null,null,null,null,null);
    }

    //consulta de un contacto por 'numero' (clave primaria)
    public Cursor getAlumno(long numero) throws SQLException{
        Cursor mCursor = bsSql.query(true, DATABASE_TABLE_ALUMNOS, todasColumnas_alumnos,
                KEY_ALUMNOS_ROWID + "=" + numero,null,null,null,null,null);
        //si hay datos devueltos, apunta al principio
        if (mCursor != null)  mCursor.moveToFirst();
        return mCursor;
    }

    //-- MODIFICAR
    //actualiza los datos de una Empresa en concreto
    public boolean actualizarEmpresa(Empresa emp){
        ContentValues args = new ContentValues();
        args.put(KEY_EMPRESAS_NOMBRE_EMPRESA, emp.getNombreE());
        args.put(KEY_EMPRESAS_NOMBRE_RESPONSABLE, emp.getNombreR());
        args.put(KEY_EMPRESAS_APELLIDOS, emp.getApellidos());
        args.put(KEY_EMPRESAS_EMAIL,emp.getEmail());
        args.put(KEY_EMPRESAS_TELEFONO,emp.getTelefono());
        args.put(KEY_EMPRESAS_DIRECCION,emp.getDireccion());
        args.put(KEY_EMPRESAS_WEB,emp.getWeb());
        return bsSql.update(DATABASE_TABLE_EMPRESAS, args,KEY_EMPRESAS_ROWID + "=" + emp.getNumero(), null) > 0;
    }
    //actualiza los datos de un Alumno en concreto
    public boolean actualizarContacto(Alumno a){
        ContentValues args = new ContentValues();
        args.put(KEY_ALUMNOS_NOMBRE, a.getNombre());
        args.put(KEY_ALUMNOS_APELLIDOS, a.getApellidos());
        args.put(KEY_ALUMNOS_TELEFONO, a.getTelefono());
        args.put(KEY_ALUMNOS_CORREO,a.getCorreo());
        args.put(KEY_ALUMNOS_YEARINICIOFCT,a.getYearInicioFct());
        args.put(KEY_ALUMNOS_FINIC,a.getFinic());
        args.put(KEY_ALUMNOS_FFIN,a.getFfin());
        args.put(KEY_ALUMNOS_HORASPD,a.getHorasPd());
        args.put(KEY_ALUMNOS_NUMDIAS,a.getNumDias());
        args.put(KEY_ALUMNOS_HorasTFCT,a.getHorasTfct());
        return bsSql.update(DATABASE_TABLE_ALUMNOS, args,KEY_ALUMNOS_ROWID + "=" + a.getNumero(), null) > 0;
    }


    //-- OTROS MÉTODOS
    //Devuelve cadena con los datos de una Empresa (la fila de un Cursor)
    public String mostrarEmpresa(Cursor c){
        String cadena=null;

        cadena=
        "Nombre Empresa: " + c.getString(0) + "\n" +
        "Nombre Responsable: " + c.getString(1) + "\n" +
        "Apellidos Responsable: " + c.getString(2) + "\n" +
        "e-mail: " + c.getString(3)+"\n" +
        "Telefono: " + c.getString(4)+"\n" +
        "Direccion: " + c.getString(5)+"\n" +
        "Web: " + c.getString(6)+"\n";
        return cadena;
    }
    //Devuelve cadena con los datos de un Alumno (la fila de un Cursor)
    public String mostrarAlumno(Cursor c){
        String cadena=null;

        cadena=
        "Nombre: " + c.getString(0) + "\n" +
        "Apellidos: " + c.getString(1) + "\n" +
        "Telefono: " + c.getString(2) + "\n" +
        "Correo: " + c.getString(3)+ "\n" +
        "Año inicio FCT: " + c.getString(4) + "\n" +
        "Fecha Inicio: " + c.getString(5) + "\n" +
        "Fecha Fin: " + c.getString(6) + "\n" +
        "Horas por día: " + c.getString(7) + "\n" +
        "Horas FCT: " + c.getString(8) + "\n";
        return cadena;
    }


    //Obtiene una lista de Empresas a través de un objeto Cursor
    public List<Empresa> getAllEmpresas() {
        //Lista de empresas
        List<Empresa> listaEmpresas = new ArrayList<Empresa>();
        //objeto cursor que se llena con el resultado de la consulta que obtiene todos las empresas
        Cursor cursor = this.getTodaslasEmpresas();
        //se posiciona al principio del cursor
        cursor.moveToFirst();
        //mientras hay datos en el cursor
        while (!cursor.isAfterLast()) {
            //genera una empresa
            Empresa comment = cursorToEmpresa(cursor);
            //añade un contacto a la lista
            listaEmpresas.add(comment);
            //avanza al siguiente
            cursor.moveToNext();
        }
        cursor.close();
        return listaEmpresas;
    }

    //Obtiene una lista de Alumnos a través de un objeto Cursor
    public List<Alumno> getAllContactos() {
        //Lista de contactos
        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        //objeto cursor que se llena con el resultado de la consulta que obtiene todos los contactos
        Cursor cursor = this.getTodoslosAlumnos();
        //se posiciona al principio del cursor
        cursor.moveToFirst();
        //mientras hay datos en el cursor
        while (!cursor.isAfterLast()) {
            //genera un contacto
            Alumno comment = cursorToAlumno(cursor);
            //añade un contacto a la lista
            listaAlumnos.add(comment);
            //avanza al siguiente
            cursor.moveToNext();
        }
        cursor.close();
        return listaAlumnos;
    }

    //genera una Empresa a partir de un objeto Cursor
    public Empresa cursorToEmpresa(Cursor cursor) {
        Empresa e = new Empresa();
        e.setNumero(cursor.getLong(0));
        e.setNombreE(cursor.getString(1));
        e.setNombreR(cursor.getString(2));
        e.setApellidos(cursor.getString(3));
        e.setEmail(cursor.getString(4));
        e.setTelefono(cursor.getString(5));
        e.setDireccion(cursor.getString(6));
        e.setWeb(cursor.getString(7));

        return e;
    }

    //genera un contacto a partir de un objeto Cursor
    public Alumno cursorToAlumno(Cursor cursor) {
        Alumno a = new Alumno();
        a.setNumero(cursor.getLong(0));
        a.setNombre(cursor.getString(1));
        a.setApellidos(cursor.getString(2));
        a.setTelefono(cursor.getString(3));
        a.setCorreo(cursor.getString(4));
        a.setYearInicioFct(cursor.getString(5));
        a.setFinic(cursor.getString(6));
        a.setFfin(cursor.getString(7));
        a.setHorasPd(cursor.getString(8));
        a.setNumDias(cursor.getString(9));
        a.setHorasTfct(cursor.getString(10));

        return a;
    }


    //**** CLASE PRIVADA subclase SQLiteOpenHelper***/

    //clase para crear la base de datos SQLite
    private static class BaseDatosHelper extends SQLiteOpenHelper
    {
        BaseDatosHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)	{
            try{
                //ejecuta la sentencia SQL de creación de la BD
                db.execSQL(DATABASE_CREATE);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
            Log.w(TAG, "Actualizando base de datos de la versión " + oldVersion
                    + " a "
                    + newVersion + ", borraremos todos los datos");
            //elimina tabla de la BD
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ALUMNOS+";DROP TABLE IF EXISTS " + DATABASE_TABLE_EMPRESAS);
            //crea la nueva BD
            onCreate(db);
        }
    }



    }




