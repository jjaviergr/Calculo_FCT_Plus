package com.example.usuario.calculo_fct_plus;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatosEmpresaActivity extends MainActivity {

    private EditText edNombreE,edNombreR,edApellidR,edEmail,edTelfR,edDireccionE,edDireccionWe;
    private long idEmpresa;
    private GestionadorBD BD;
    private Cursor c;
    private Button btActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos__empresa_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        edNombreE=(EditText)findViewById(R.id.edNombreEmpresa);
        edNombreR=(EditText)findViewById(R.id.edNombreC);
        edApellidR=(EditText)findViewById(R.id.edApellidosC);
        edEmail=(EditText)findViewById(R.id.edEmail);
        edTelfR=(EditText)findViewById(R.id.edTelefonoC);
        edDireccionE=(EditText)findViewById(R.id.edDireccionEmpresa);
        edDireccionWe=(EditText)findViewById(R.id.edWebEmpresa);

        btActualizar=(Button)findViewById(R.id.btActualizar);

        try {
            BD = new GestionadorBD(this);
        }
        catch(Exception ex)
        {
            Toast.makeText(this, "Error al crear la BD "+ex,Toast.LENGTH_SHORT).show();
        }
        try {
            //abre la BD

            BD.open();
        }
        catch(Exception ex)
        {
            Toast.makeText(this, "Error al abrir la BD "+ex,Toast.LENGTH_SHORT).show();
        }

        try
        {
            c = BD.getTodaslasEmpresas();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error al recuperar las empresas "+ex,Toast.LENGTH_SHORT).show();
        }

        //actualizar_interface(c);

        /*//inserta un nuevo contacto
        db.insertarContacto(nombre, tipo, tlf);
        //cierra conexión
        db.close();*/
    }

    public void actualizar_interface(Cursor c)
    {
        edNombreE.setText(c.getString(1));
        edNombreR.setText(c.getString(2));
        edApellidR.setText(c.getString(3));
        edEmail.setText( c.getString(4));
        edTelfR.setText(c.getString(5));
        edDireccionE.setText(c.getString(6));
        edDireccionWe.setText(c.getString(7));
    }

    public void btSiguienteOnClick(View v)
    {
        if (!c.isClosed() && !c.isLast()) {
            c.moveToNext();
            actualizar_interface(c);
        }
        else
        {
            Toast.makeText(this, "No hay mas registros despues que este",Toast.LENGTH_SHORT).show();
        }

    }

    public void btAnteriorOnClick(View v)
    {
        if (!c.isClosed() && (!c.isFirst() || !c.isBeforeFirst())) {
            c.moveToPrevious();
            actualizar_interface(c);
        }
        else
        {
            Toast.makeText(this, "No hay mas registros antes que este",Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar_interface()
    {
        edNombreE.setText("");
        edNombreR.setText("");
        edApellidR.setText("");
        edEmail.setText("");
        edTelfR.setText("");
        edDireccionE.setText("");
        edDireccionWe.setText("");
    }

    public void btNuevaEmpresa(View v)
    {
        limpiar_interface();
        btActualizar.setText("Guardar");

    }

    public void OnClickBtActualizar(View v)
    {
        Empresa e=new Empresa( edNombreE.getText().toString(),edNombreR.getText().toString(),
                edApellidR.getText().toString(),edEmail.getText().toString(),
                edTelfR.getText().toString(),edDireccionE.getText().toString(),
                edDireccionWe.getText().toString());
        if (btActualizar.getText().toString().equalsIgnoreCase("Guardar"))
        {
            //para guardar
            try
            {
               BD.insertarEmpresa(e);
               Toast.makeText(this, "Empresa guardada con exito",Toast.LENGTH_SHORT).show();
               btActualizar.setText("Actualizar");
            }
            catch(Exception ex)
            {
                Toast.makeText(this, "Ocurrio un error al guardar empresas "+ex,Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            //para actualizar.
            String cadena=c.getString(0);
            e.setNumero(Long.parseLong(cadena));
            try
            {
                BD.actualizarEmpresa(e);
                Toast.makeText(this, "Empresa actualizada con exito",Toast.LENGTH_SHORT).show();
            }
            catch(Exception ex)
            {
                Toast.makeText(this, "Ocurrio un error al actualizar "+ex,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        BD.close();
    }

}
