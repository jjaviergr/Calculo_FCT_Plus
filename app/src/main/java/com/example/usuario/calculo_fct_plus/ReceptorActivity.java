package com.example.usuario.calculo_fct_plus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReceptorActivity extends AppCompatActivity {
    private TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor);

        t1=(TextView)findViewById(R.id.textView3);
        t2=(TextView)findViewById(R.id.textView5);

        Bundle bundle=getIntent().getExtras();


        try
        {

            double n= (double)bundle.getDouble("numero");
            t1.setText("Tu número de días es de "+n);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error con el intent "+e, Toast.LENGTH_SHORT).show();
        }
        String nombre=bundle.getString("nombre");
        char primero=nombre.toCharArray()[0];
        nombre=String.valueOf(primero).toUpperCase()+nombre.substring(1,nombre.length());
         t2.setText("Hola "+nombre);
        //Toast.makeText(getApplicationContext(), "El número de días es " + bundle.getFloat("numero"), Toast.LENGTH_SHORT).show();
        //finish();
    }

    public void OnClickSalir(View v)
    {
        finish();
    }
}
