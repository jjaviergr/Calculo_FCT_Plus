package com.example.usuario.calculo_fct_plus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CalculoActivity extends MainActivity {
    private TextView tDias,tMensaje;


    private String nombre,apellidos,telefono,correo,year_inicio_fct,fecha_inicio,fecha_fin,n_dias_fct;
    private float horas_por_dia,horas_totales_fct;
    private float n_dias;
    private ArrayList<Object> v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_);

        tDias=(TextView)findViewById(R.id.edNumeroHoras);
        tMensaje=(TextView)findViewById(R.id.edMensaje);

        ArrayList<Object> v=recuperar_datos_calculo();

        mostrar_alumno();

    }


    public void mostrar_alumno()
    {
        float calculo=calcular_resultados();
        tMensaje.setText((String)v.get(0)+" "+(String)v.get(1));

        tDias.setText(calculo+ "días");
    }


    public float calcular_resultados()
    {
        float n_dias=0;
        try
        {
           n_dias=horas_totales_fct/horas_por_dia;
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Division por 0 "+e, Toast.LENGTH_SHORT).show();
        }

        return n_dias;
    }

    /**
     * Método que recupera datos de un intent.
     * @return Devuelve un ArrayList de Objetos.
     */
    public ArrayList recuperar_datos_calculo()
    {
        ArrayList<Object> L=new ArrayList();
        Bundle bundle=null;
        bundle=getIntent().getExtras();
        

        L.add((String)bundle.getString("nombre"));
        L.add((String)bundle.getString("apellidos"));
        L.add((float) bundle.getFloat("horas_por_dia"));
        L.add((float) bundle.getFloat("horas_totales_fct"));

        
        return L;

    }

//// Metodos que utilizan sharedPreferences ....

    /**
     * Método que guarda los datos habituales de los alumnos usando la clase SharedPreferences.
     * @param v
     */
    public void guardar_datos_habituales_alumnos(View v)
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

    /**
     * Método que recupera los datos habituales de los alumnos usando la clase SharedPreferences.
     * @return Devuelve un ArrayList de objetos.
     */
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
    }

}
