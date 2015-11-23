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

public class DatosAlumnosActivity extends AppCompatActivity {

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
    public void OnClickCalcular(View v)
    {
        float uno=0;
        float dos=0;
        try
        {
            //Toast.makeText(getApplicationContext(),t1.getText().toString(), Toast.LENGTH_SHORT).show();
            uno=Float.parseFloat(tHtFCT.getText().toString());
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(), "El número de horas de la FCT no es valido", Toast.LENGTH_SHORT).show();

        }

        try
        {
            dos=Float.parseFloat(tHpd.getText().toString());
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(),"El número de horas de la jornada laboral no es un numero valido ", Toast.LENGTH_SHORT).show();

        }


        // ¿Existe  un limite de horas para la FCT????????????????? Si es asi falta poner una restricción.


        if (dos>8)
        {
            Toast.makeText(getApplicationContext(),"El número de horas debe ser <=8", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (dos==0)
            {
                Toast.makeText(getApplicationContext(),"Trabajar gratis deberia ser ilegal", Toast.LENGTH_SHORT).show();
            }
            else
            {
                bundle.putString("nombre", tNombre.getText().toString());
                bundle.putDouble("numero", uno / dos);
                Intent intent = new Intent(this, CalculoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }

        //Toast.makeText(getApplicationContext(),"El número de días es : "+uno/dos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_blank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==R.id.opcion1) {

            String nombre=tNombre.getText().toString();
            char primero=nombre.toCharArray()[0];
            nombre=String.valueOf(primero).toUpperCase()+nombre.substring(1,nombre.length());
            Toast.makeText(this, nombre, Toast.LENGTH_LONG).show();

        }

        if (id==R.id.opcion2) {

            Toast.makeText(this,"Pedazo Empresa S.L.U",Toast.LENGTH_LONG).show();

        }
        try {
            if (id == R.id.opcion3) {
                dos=Double.parseDouble(tHpd.getText().toString());
                uno=Double.parseDouble(tHtFCT.getText().toString());
                if (dos!=0)
                {
                    double tres = uno / dos;
                    Toast.makeText(this, "Tu FCT va a ser de "+Double.toString(tres)+ " días", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "Por favor, mete los datos de las horas(las jornadas de 0 horas no se permiten.)", Toast.LENGTH_LONG).show();
                }

            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Por favor, mete los datos de las horas(las jornadas de 0 horas no se permiten.)", Toast.LENGTH_LONG).show();
        }
        if (id==R.id.opcion4) {

            Toast.makeText(this, "Hecho por José J. García Corporation S.L" ,Toast.LENGTH_LONG).show();

        }


        return super.onOptionsItemSelected(item);

    }

    public void onClickSalir(View v)
    {
        finish();
    }

}
