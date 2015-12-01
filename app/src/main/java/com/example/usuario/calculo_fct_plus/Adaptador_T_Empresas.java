package com.example.usuario.calculo_fct_plus;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Usuario on 01/12/2015.
 */
public class Adaptador_T_Empresas extends AdaptadorBD{




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
