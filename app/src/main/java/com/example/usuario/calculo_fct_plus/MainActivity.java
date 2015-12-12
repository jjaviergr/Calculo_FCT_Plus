package com.example.usuario.calculo_fct_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void btPreferencesOnClick(View v)
    {

    }

    public void btSalirOnClick(View v) {
        finish();
    }

    public void btEmpresaOnclick(View v)
    {
        Intent intent = new Intent(this, DatosEmpresaActivity.class);
        startActivity(intent);
    }

    public void btAlumnosOnclick(View v)
    {
        Intent intent = new Intent(this, DatosAlumnosActivity.class);
        startActivity(intent);
    }

    public void btRelaciona_Alumno_Empresa(View v)
    {
        Intent intent = new Intent(this, RelacionaAlumnosActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        Intent i;

        if (id==R.id.opcion1) // Acceder a gestión de empresas
        {
            i = new Intent(this, DatosEmpresaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
        }

        if (id==R.id.opcion2) // Acceder a gestión de alumnos
        {
            i = new Intent(this, DatosAlumnosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
        }

        if (id == R.id.opcion3) // Acceder a relacionar alumnos con empresas
        {
            i = new Intent(this, RelacionaAlumnosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);;
            startActivity(i);
        }

        if (id==R.id.opcion4) // Salir del programa
        {
          finish();
        }

        if (id==R.id.opcion5) // Credito al Autor.
        {
            Toast.makeText(this, "Hecho por José Javier García Romero" ,Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_blank, menu);
        return true;
    }
}
