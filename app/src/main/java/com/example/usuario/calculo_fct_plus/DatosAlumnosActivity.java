package com.example.usuario.calculo_fct_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatosAlumnosActivity extends MainActivity {

    private EditText tNombre,tApellidos,tTelefono,tCorreo,tYearinicfct,tFinic,tFfin,tHpd,tndias,tHtFCT;

    private double uno=0,dos=0;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_alumnos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    tNombre=(EditText)findViewById(R.id.edNombre);
    tApellidos=(EditText)findViewById(R.id.edApellidos);
    tTelefono=(EditText)findViewById(R.id.edTelefono);
    tCorreo=(EditText)findViewById(R.id.email);
    tYearinicfct=(EditText)findViewById(R.id.edYearFCT);
    tFinic=(EditText)findViewById(R.id.edFechaInic);
    tFfin=(EditText)findViewById(R.id.edFechaFin);
    tHpd=(EditText)findViewById(R.id.edHorasDia);
    tndias=(EditText)findViewById(R.id.edDias);
    tHtFCT=(EditText)findViewById(R.id.edHorasTfct);

    bundle=new Bundle();
    }

    public boolean es_float(String cadena)
    {
        boolean es=false;
        try
        {
            Float.parseFloat(cadena.toString());
            es=true;
        }
        catch(Exception e)
        {}


        return(es);
    }


    public void Validar()
    {
        boolean fallo=false;

        if (!es_float(tHtFCT.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "El número de horas de la FCT no es valido", Toast.LENGTH_SHORT).show();
            fallo=true;
        }

        if((!es_float(tHpd.getText().toString()))||(fallo))
        {
            Toast.makeText(getApplicationContext(),"El número de horas de la jornada laboral no es un numero valido ", Toast.LENGTH_SHORT).show();
            fallo=true;
        }

        float horas_fct=Float.parseFloat(tHtFCT.getText().toString());
        float horas_p_d=Float.parseFloat(tHpd.getText().toString());

        if (((horas_p_d<=0) || (horas_p_d>8))||(fallo))
        {
            Toast.makeText(getApplicationContext(),"El número de horas debe ser <=8", Toast.LENGTH_SHORT).show();
            fallo=true;
        }

        if ((horas_fct<0 || horas_fct>2000)||(fallo))
        {
            Toast.makeText(getApplicationContext(),"La FCT no puede durar mas de 2000 horas", Toast.LENGTH_SHORT).show();
            fallo=true;
        }

        if (!fallo)
        {
            bundle.putString("nombre", tNombre.getText().toString());
            bundle.putString("apellidos", tNombre.getText().toString());
            bundle.putString("telefono", tNombre.getText().toString());
            bundle.putString("correo", tNombre.getText().toString());
            bundle.putString("year_inicio_fct", tNombre.getText().toString());
            bundle.putString("fecha_inicio", tNombre.getText().toString());

            bundle.putString("fecha_fin", tNombre.getText().toString());
            bundle.putDouble("horas_por_dia", horas_p_d);
            bundle.putString("n_dias_fct", tNombre.getText().toString());
            bundle.putDouble("horas_totales_fct", horas_fct);

            Intent intent = new Intent(this, CalculoActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }


        //Toast.makeText(getApplicationContext(),"El número de días es : "+uno/dos, Toast.LENGTH_SHORT).show();
    }






}
