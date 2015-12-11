package com.example.usuario.calculo_fct_plus;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CalculoActivity extends MainActivity {
    private TextView tDias,tMensaje;
    private Button btSalir;
    private ArrayList<String> d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tDias=(TextView)findViewById(R.id.tvDias);
        tMensaje=(TextView)findViewById(R.id.tvMensaje);
        btSalir=(Button)findViewById(R.id.btSalir);


        recuperar_datos_calculo();

        mostrar_alumno();

    }


    private void mostrar_alumno()
    {
        float horas_totales_fct,horas_por_dia,n_dias;

        try
        {
            horas_totales_fct=Float.parseFloat(d.get(2).toString());
            horas_por_dia=Float.parseFloat(d.get(3).toString());
            try
            {
                n_dias=horas_totales_fct/horas_por_dia;
                tMensaje.setText("El número de días de "+d.get(0).toString()+" "+d.get(1).toString()+ " es de " );


                tDias.setText(n_dias+"");
            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(), "Error de division por 0 "+e, Toast.LENGTH_SHORT).show();
            }
        }
        catch (NumberFormatException e)
        {
            tMensaje.setText("Los datos de horas no son validos "+e);
        }
    }

    public void OnClickCalculo(View v)
    {
        finish();
    }


    /**
     * Método que recupera datos de un intent.
     * @return Devuelve un ArrayList de datos cadena.
     */
    private void recuperar_datos_calculo()
    {

        Bundle bundle=getIntent().getExtras();

        d=bundle.getStringArrayList("datos");
    }

    @Override
    public void onPause()
    {
        finish();
        super.onPause();
    }

//// Metodos que utilizan sharedPreferences ....

    /**
     * Método que guarda los datos habituales de los alumnos usando la clase SharedPreferences.
     * @param v
     */
   /* public void guardar_datos_habituales_alumnos(View v)
    {
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();

        editor.putString("year_inicio_fct", year_inicio_fct);
        editor.putString("fecha_inicio", fecha_inicio);
        editor.putString("fecha_fin", fecha_fin);
        editor.putString("n_dias_fct", n_dias_fct);
        editor.putFloat("horas_por_dia", horas_por_dia);
        editor.putFloat("horas_totales_fct", horas_totales_fct);

        editor.commit();


    }

    *//**
     * Método que recupera los datos habituales de los alumnos usando la clase SharedPreferences.
     * @return Devuelve un ArrayList de objetos.
     *//*
    public ArrayList<Object> recuperar_datos_habituales_alumnos()
    {
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);

        ArrayList<Object> L=new ArrayList();

        L.add((String)prefe.getString("year_inicio_fct",""));
        L.add((String)prefe.getString("fecha_inicio",""));
        L.add((String)prefe.getString("fecha_fin",""));
        L.add((String)prefe.getString("n_dias_fct",""));

        L.add((float)prefe.getFloat("horas_por_dia",0));
        L.add((float)prefe.getFloat("horas_totales_fct",0));

        return L;
    }*/

}
