package com.example.usuario.calculo_fct_plus;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RelacionaAlumnosActivity extends MainActivity {

    private GestionadorBD BD;
    private Cursor es,as;
    
    private EditText edNombreA,edApellidoA,edFechaInic,edFechaFin,edHorasDia,edDias;
    
    private EditText edNombreEmpresa,edNombreC,edApellidosC,edTelefonoC,edEmail;
    private CheckBox chkBuscarEmpresaAlumno;

    private Button btAsociar_Desasociar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaciona_alumnos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edNombreA=(EditText)findViewById(R.id.edNombreA);
        edApellidoA=(EditText)findViewById(R.id.edApellidoA);
        edFechaInic=(EditText)findViewById(R.id.edFechaInic);
        edFechaFin=(EditText)findViewById(R.id.edFechaFin);
        edHorasDia=(EditText)findViewById(R.id.edHorasDia);
        edDias=(EditText)findViewById(R.id.edDias);

        edNombreEmpresa=(EditText)findViewById( R.id.edNombreEmpresa);
        edNombreC=(EditText)findViewById(R.id.edNombreC);
        edApellidosC=(EditText)findViewById(R.id.edApellidosC);
        edTelefonoC=(EditText)findViewById(R.id.edTelefonoC);
        edEmail=(EditText)findViewById(R.id.edEmail);

        chkBuscarEmpresaAlumno=(CheckBox)findViewById(R.id.chkBuscarEmpresaAlumno);
        btAsociar_Desasociar=(Button)findViewById(R.id.btAsociarDesasociar);
                
        try
        {
            BD = new GestionadorBD(this);
            BD.open();


            try
            {
                as = BD.getTodoslosAlumnos();
                if (as.getCount()>0)
                {
                    as.moveToFirst();
                    actualizar_solo_alumnos(as);
                }
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Ocurrio un error al recuperar los alumnos "+e,Toast.LENGTH_SHORT).show();
            }

            try
            {
                es = BD.getTodaslasEmpresas();
                if (es.getCount() > 0)
                {
                    es.moveToFirst();
                    actualizar_solo_empresa(es);
                }
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Ocurrio un error al recuperar las empresas "+e,Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Ocurrio un error al abrir la BD "+e,Toast.LENGTH_SHORT).show();
        }
    }

    public void chkBuscarEmpresaAlumnoOnClick(View v)
    {
        if (chkBuscarEmpresaAlumno.isChecked())
        {
            btAsociar_Desasociar.setText("DESASOCIAR");
        }
        else
        {
            btAsociar_Desasociar.setText("ASOCIAR");
        }
    }

    public void btAsociarOnClick(View v)
    {
        if (btAsociar_Desasociar.getText().toString().equalsIgnoreCase("ASOCIAR"))
        {
            Alumno alumno = BD.cursorToAlumno(as);
            alumno.setKey_empresa(es.getLong(0));
            boolean exito=false;
            exito=BD.actualizarAlumno(alumno);
            if (exito)
               Toast.makeText(this, "Alumno asociado con exito ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Alumno no asociado por fallo de la aplicacion ", Toast.LENGTH_SHORT).show();
        }

        if (btAsociar_Desasociar.getText().toString().equalsIgnoreCase("DESASOCIAR"))
        {
            Alumno alumno = BD.cursorToAlumno(as);
            alumno.setKey_empresa(-1);
            boolean exito=false;
            exito=BD.actualizarAlumno(alumno);
            if (exito) {
                Toast.makeText(this, "Alumno desasociado con exito ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Alumno no desasociado por fallo de la aplicacion ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void actualizar_interface(Cursor as,Cursor es)
    {
        if (as.getCount()>0)
        {
            edNombreA.setText(as.getString(1));
            edApellidoA.setText(as.getString(2));
            edFechaInic.setText(as.getString(3));
            edFechaFin.setText(as.getString(4));
            edHorasDia.setText(as.getString(5));
            edDias.setText(as.getString(6));
        }
        else
        {
            Toast.makeText(this, "Sin alumnos",Toast.LENGTH_SHORT).show();
        }
        if (es.getCount()>0)
        {
            edNombreEmpresa.setText(es.getString(1));
            edNombreC.setText(es.getString(2));
            edApellidosC.setText(es.getString(3));
            edTelefonoC.setText(es.getString(4));
            edEmail .setText(es.getString(5));
        }
        else
        {
            Toast.makeText(this, "Sin empresa",Toast.LENGTH_SHORT).show();
        }
    }

    public void btRSiguienteOnClick(View v)
    {
        Cursor empresa=null;
        if (chkBuscarEmpresaAlumno.isChecked())
        {
            if (!as.isLast())
            {
                //Toast.makeText(this, as.getLong(11)+"",Toast.LENGTH_SHORT).show();
                as.moveToNext();
                if (as.getLong(10)!=-1)
                {

                    empresa = BD.recupera_empresa_alumno(as.getLong(11));
                    //Toast.makeText(this, empresa.getLong(0)+"|"+empresa.getString(1)+"|"+empresa.getString(2)+"|"+empresa.getString(3)+"|"+empresa.getString(4)+"|"+empresa.getString(5)+"|"+empresa.getString(6)+"|"+empresa.getString(7)+"|"+empresa.getString(8)+"|"+empresa.getString(9)+"|"+empresa.getString(10)+"|"+empresa.getLong(11),Toast.LENGTH_SHORT).show();
                    if (empresa.getCount() > 0)
                    {
                        actualizar_interface(as, empresa);
                        btAsociar_Desasociar.setText("DESASOCIAR");
                    }
                }
                //Toast.makeText(this, "KEY recuperada "+as.getLong(11)+"numero empresas "+empresa.getCount(), Toast.LENGTH_SHORT).show();
                if (as.getLong(11)==-1 || !(empresa.getCount() > 0))
                {
                    actualizar_solo_alumnos(as);
                    Toast.makeText(this, "Alumno no asociado a empresa", Toast.LENGTH_SHORT).show();
                    btAsociar_Desasociar.setText("ASOCIAR");
                }
            }
            else
            {
                Toast.makeText(this, "No hay mas alumnos posteriores a este",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            if (!as.isLast())
            {
                as.moveToNext();
                actualizar_solo_alumnos(as);
                btAsociar_Desasociar.setText("ASOCIAR");
            }
            else
            {
                Toast.makeText(this, "No hay mas alumnos posterioes a este ",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void actualizar_solo_empresa(Cursor c)
    {
        if (c.getCount()>0) {
            edNombreEmpresa.setText(c.getString(1));
            edNombreC.setText(c.getString(2));
            edApellidosC.setText(c.getString(3));
            edTelefonoC.setText(c.getString(4));
            edEmail.setText(c.getString(5));
        }
        else
        {
            Toast.makeText(this, "No hay empresa",Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizar_solo_alumnos(Cursor c)
    {//Toast.makeText(this, "lanzado",Toast.LENGTH_SHORT).show();
        if (c.getCount()>0)
        {
            edNombreA.setText(c.getString(1));
            edApellidoA.setText(c.getString(2));
            edFechaInic.setText(c.getString(6));
            edFechaFin.setText(c.getString(7));
            edHorasDia.setText(c.getString(8));
            edDias.setText(c.getString(9));
        }
        else
        {
            Toast.makeText(this, "No hay Alumnos",Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar_empresa()
    {
        edNombreEmpresa.setText("");
        edNombreC.setText("");
        edApellidosC.setText("");
        edTelefonoC.setText("");
        edEmail.setText("");
    }
    
    public void btRAanteriorOnClick(View v)
    {

            if (chkBuscarEmpresaAlumno.isChecked())
            {
                if (!as.isFirst())
                {
                    //recupero la empresa
                    as.moveToPrevious();
                    es = BD.recupera_empresa_alumno(as.getLong(11));
                    if (es.getCount()>0) //si hay empresas las imprimo alumno y empresa.
                    {
                        actualizar_interface(as, es);
                        btAsociar_Desasociar.setText("DESASOCIAR");
                    }
                    else
                    {  //no tengo empresas. solo imprimo a los alumnos
                        actualizar_solo_alumnos(as);
                        btAsociar_Desasociar.setText("ASOCIAR");
                        limpiar_empresa();
                        Toast.makeText(this, "No hay Empresa asociada a este alumno",Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else
            {   //no quiere ver empresas . Solo muestro alumnos.
                if (!as.isFirst())
                {
                    as.moveToPrevious();
                    actualizar_solo_alumnos(as);
                    btAsociar_Desasociar.setText("ASOCIAR");
                }
            }

            //Toast.makeText(this, "No hay mas registros antes que este"+ e ,Toast.LENGTH_SHORT).show();

    }

    public void btREAnteriorOnClick(View v)
    {
        try
        {
            if (chkBuscarEmpresaAlumno.isChecked())
            {
                if (!es.isFirst())
                {
                    es.moveToPrevious();
                    as = BD.recupera_alumnos_empresa(es.getLong(0));
                    actualizar_interface(as, es);
                }
                else
                {
                    Toast.makeText(this, "No hay mas registros antes que este",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                if (!es.isFirst())
                {
                    es.moveToPrevious();
                    actualizar_solo_empresa(es);
                }
                else
                {
                    Toast.makeText(this, "No hay mas registros antes que este",Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No hay mas registros antes que este",Toast.LENGTH_SHORT).show();
        }
    }


    public void btREPosteriorOnClick(View v)
    {
        try
        {
            if (chkBuscarEmpresaAlumno.isChecked())
            {
                if (!es.isLast())
                {
                    es.moveToNext();
                    as = BD.recupera_alumnos_empresa(as.getLong(0));
                    actualizar_interface(as, es);
                }
            }
            else
            {
                if (!es.isLast())
                {
                    es.moveToNext();
                    actualizar_solo_empresa(es);
                }
                else
                {
                    Toast.makeText(this, "No hay mas registros despues que este",Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No hay mas registros despues que este",Toast.LENGTH_SHORT).show();

        }
    }





    @Override
    public void onPause()
    {
        finish();
        super.onPause();
    }

    @Override
    public void onDestroy()
    {

        try
        {
            es.close();
            as.close();
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

}
