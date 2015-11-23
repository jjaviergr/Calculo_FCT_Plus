package com.example.usuario.calculo_fct_plus;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class DatosEmpresaActivity extends DatosAlumnosActivity {

    private EditText edNombreE,edNombreR,edApellidR,edEmail,edTelfR,edDireccionE,edDireccionWe;

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
    }

    public void onclickSalir(View v)
    {
        finish();
    }

}
