package com.example.usuario.calculo_fct_plus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CalculoActivity extends DatosAlumnosActivity {
    private TextView tDias,tMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_);

        tDias=(TextView)findViewById(R.id.edNumeroHoras);
        tMensaje=(TextView)findViewById(R.id.edMensaje);

        Bundle bundle=getIntent().getExtras();


        try
        {

            double n= (double)bundle.getDouble("numero");
            tDias.setText("Tu número de días es de "+n);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error con el intent "+e, Toast.LENGTH_SHORT).show();
        }
        String nombre=bundle.getString("nombre");
        char primero=nombre.toCharArray()[0];
        nombre=String.valueOf(primero).toUpperCase()+nombre.substring(1,nombre.length());
         tMensaje.setText("Hola "+nombre);
        //Toast.makeText(getApplicationContext(), "El número de días es " + bundle.getFloat("numero"), Toast.LENGTH_SHORT).show();
        //finish();
    }

    public void OnClickSalir(View v)
    {
        finish();
    }
}
