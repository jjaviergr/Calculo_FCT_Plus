package com.example.usuario.calculo_fct_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    public void btSalirOnClick(View v)
    {
        finish();
    }

    public void btEmpresaOnclick(View v) {
        Intent intent = new Intent(this, DatosEmpresaActivity.class);
        startActivity(intent);
    }

    public void btAlumnosOnclick(View v) {
        Intent intent = new Intent(this, DatosAlumnosActivity.class);
        startActivity(intent);
    }

    public void btCalculosOnclick(View v) {
//        Intent intent = new Intent(this, CalculoActivity.class);
//        startActivity(intent);
    }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    if (id==R.id.opcion1) {

       /* String nombre=tNombre.getText().toString();
        char primero=nombre.toCharArray()[0];
        nombre=String.valueOf(primero).toUpperCase()+nombre.substring(1,nombre.length());*/
        Toast.makeText(this, "uno", Toast.LENGTH_LONG).show();

    }

    if (id==R.id.opcion2) {

        Toast.makeText(this,"Pedazo Empresa S.L.U",Toast.LENGTH_LONG).show();

    }

        if (id == R.id.opcion3) {
            Toast.makeText(this, "3", Toast.LENGTH_LONG).show();

        }

    if (id==R.id.opcion4) {

        Toast.makeText(this, "Hecho por José J. García Corporation S.L" ,Toast.LENGTH_LONG).show();

    }


    return super.onOptionsItemSelected(item);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_blank, menu);
        return true;
    }

    public void onClickSalir(View v)
    {
        finish();
    }
}
