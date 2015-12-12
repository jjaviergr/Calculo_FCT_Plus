package com.example.usuario.calculo_fct_plus;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DatosAlumnosActivity extends MainActivity {

    private EditText tNombre,tApellidos,tTelefono,tCorreo,tYearinicfct,tFinic,tFfin,tHpd,tndias,tHtFCT;

    private double uno=0,dos=0;
    private Bundle bundle;

    private long id;
    private GestionadorBD BD;
    private Cursor c;

    private Button BtGrabar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
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

        BtGrabar=(Button)findViewById(R.id.btGrabar);
        

    
        bundle=new Bundle(); //Esto es para el calculo de horas

        try
        {
            BD = new GestionadorBD(this);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Ocurrio un error al crear la BD "+e,Toast.LENGTH_SHORT).show();
        }


        try
        {
            BD.open();
        }
        catch (SQLException e)
        {
            Toast.makeText(this, "Ocurrio un error al abrir la BD "+e,Toast.LENGTH_SHORT).show();
        }

        try
        {
            c = BD.getTodoslosAlumnos();
            if (c.getCount()>0)
            {
                c.moveToFirst();
                actualizar_interface(c);
                BtGrabar.setText("ACTUALIZAR");
            }
            else
            {
                BtGrabar.setText("GUARDAR");
                //Toast.makeText(this, "Sin registros ",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Ocurrio un error al recuperar a los alumnos "+e,Toast.LENGTH_SHORT).show();
        }


    }

    public void OnClickNuevoAlumno(View v)
    {
        tNombre.setText("");
        tApellidos.setText("");
        tTelefono.setText("");
        tCorreo.setText("");
        tYearinicfct.setText("");
        tFinic.setText("");
        tFfin.setText("");
        tHpd.setText("");
        tndias.setText("");
        tHtFCT.setText("");

        BtGrabar.setText("GUARDAR");
    }

    public void actualizar_interface(Cursor c)
    {
        tNombre.setText(c.getString(1));
        tApellidos.setText(c.getString(2));
        tTelefono.setText(c.getString(3));
        tCorreo.setText(c.getString(4));
        tYearinicfct.setText(c.getString(5));
        tFinic.setText(c.getString(6));
        tFfin.setText(c.getString(7));
        tHpd.setText(c.getString(8));
        tndias.setText(c.getString(9));
        tHtFCT.setText(c.getString(10));


    }

    public void btAlumnoSiguienteOnClick(View v)
    {
        try
        {
            if (!c.isLast())
            {
                c.moveToNext();
                actualizar_interface(c);

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No hay mas registros despues que este",Toast.LENGTH_SHORT).show();

        }

    }

    public void OnAlumnoClickGuardar(View v)
    {
        Alumno a=new Alumno(tNombre.getText().toString(),tApellidos.getText().toString(),
                tTelefono.getText().toString(),tCorreo.getText().toString(),
                tYearinicfct.getText().toString(),tFinic.getText().toString(),
                tFfin.getText().toString(),tHpd.getText().toString(),tndias.getText().toString(),
                tHtFCT.getText().toString());

        if (BtGrabar.getText().toString().equalsIgnoreCase("Guardar"))
        {
            //para guardar
            try
            {
                BD.insertarAlumno(a);
                Toast.makeText(this, "Alumno guardado con exito",Toast.LENGTH_SHORT).show();
                BtGrabar.setText("Actualizar");
            }
            catch(Exception ex)
            {
                Toast.makeText(this, "Ocurrio un error al guardar Alumno "+ex,Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            //para actualizar.
            try
            {
                a.setNumero(c.getLong(0));
                BD.actualizarAlumno(a);
                Toast.makeText(this, "Alumno actualizado con exito",Toast.LENGTH_SHORT).show();
            }
            catch(Exception ex)
            {
                Toast.makeText(this, "Ocurrio un error al actualizar Alumno"+ex,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btAlumnoAnteriorOnClick(View v)
    {
        try
        {
            if (!c.isFirst())
            {
                c.moveToPrevious();
                actualizar_interface(c);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No hay mas registros antes que este",Toast.LENGTH_SHORT).show();
        }

    }


    public void limpiar_interface()
    {
        tNombre.setText("");
        tApellidos.setText("");
        tTelefono.setText("");
        tCorreo.setText("");
        tYearinicfct.setText("");
        tFinic.setText("");
        tFfin.setText("");
        tHpd.setText("");
        tndias.setText("");
        tHtFCT.setText("");
    }

    public void btNuevaEmpresa(View v)
    {
        limpiar_interface();
        BtGrabar.setText("Guardar");

    }



    @Override
    public void onDestroy()
    {
        try
        {
            c.close();
            BD.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            super.onDestroy();
        }

    }

    public void onClickAlumnoCalcular(View v)
    {

        ArrayList<String> lista_Datos=new ArrayList<String>();

        lista_Datos.add(tNombre.getText().toString());
        lista_Datos.add(tApellidos.getText().toString());
        lista_Datos.add(tHtFCT.getText().toString());
        lista_Datos.add(tHpd.getText().toString());

        Intent i=new Intent(this,CalculoActivity.class);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("datos", lista_Datos);

        i.putExtras(bundle);

        startActivity(i);

    }

    @Override
    public void onPause()
    {
        finish();
        super.onPause();
    }

    public void onAlumnoClickSalir(View v)
    {
        finish();
    }
    /*

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

*/
        //Toast.makeText(getApplicationContext(),"El número de días es : "+uno/dos, Toast.LENGTH_SHORT).show();







}
