package com.example.usuario.calculo_fct_plus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class GestionPrefenciasActivity extends MainActivity {

    private EditText uno,dos,tres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_prefencias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tres=(EditText)findViewById(R.id.htfct);
        dos=(EditText)findViewById(R.id.hpd);
        uno=(EditText)findViewById(R.id.edy);


    }

    //botón, mostrar valores de preferencias
    public void onClickDisplay(View view){
        //obtenemos una instancia SharedPreferences, utilizando el formato <NombrePaquete>_preferences
        //MODE_PRIVATE: el archivo de preferencia solo lo puede abrir la aplicación que lo creó
       // SharedPreferences appPrefs=getPreferences(MODE_PRIVATE);
        SharedPreferences appPrefs = getDefaultSharedPreferences(getApplicationContext());

        uno.setText(appPrefs.getString("year_inicio", ""));
        dos.setText(appPrefs.getString("horas_dia", ""));
        tres.setText(appPrefs.getString("horas_totales_fct", ""));

    }

    //Botón, modificar valores de preferencias
    public void onClickModify(View view)
    {
        //SharedPreferences appPrefs=getPreferences(MODE_PRIVATE);
        SharedPreferences appPrefs = getDefaultSharedPreferences(getApplicationContext());
        //obtiene un objeto Editor mediante edit()
        SharedPreferences.Editor prefsEditor = appPrefs.edit();
        //cambia el valor de cadena mediante putString()
        prefsEditor.putString("year_inicio", uno.getText().toString());
        prefsEditor.putString("horas_dia", dos.getText().toString());
        prefsEditor.putString("horas_totales_fct", tres.getText().toString());



        //confirma los cambios
        prefsEditor.commit();

    }


    @Override
    public void onPause()
    {
        finish();
        super.onPause();
    }


}
